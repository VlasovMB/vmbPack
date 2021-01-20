package testquickresto.archiver;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import testquickresto.exceptions.WrongArchiveFileException;
import testquickresto.menu.UtilConsole;


import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;

/**
 * The type Archive entry reader.
 */
public class ArchiveEntryReader implements AutoCloseable{

    private final DataInputStream inputStream;
    private ArchiveEntry currentEntry;
    private Path unpackDirectory;


    /**
     * Instantiates a new Archive entry reader.
     *
     * @param archiveFile the archive file
     * @throws IOException the io exception
     */
    public ArchiveEntryReader(Path archiveFile) throws IOException {
        inputStream = new DataInputStream(Files.newInputStream(archiveFile));
        currentEntry = null;
    }

    /**
     * Extract to directory.
     *
     * @param directory the directory
     * @throws IOException               the io exception
     * @throws WrongArchiveFileException the wrong archive file exception
     */
    public void extractToDirectory(Path directory) throws IOException, WrongArchiveFileException {
        unpackDirectory = directory;
        if (!ArchiveUtil.checkIsArchive(inputStream.readInt())) throw new WrongArchiveFileException();

        for (ArchiveEntry entry = nextEntry(); entry != null; entry = nextEntry()) {
            Path targetPath = Paths.get(directory.toString(), entry.toString());
            extractFile(targetPath, entry);
        }
    }

    private void extractFile(Path targetPath, ArchiveEntry entry) throws IOException {
        ensureParentDirExists(targetPath);

        UtilConsole.CONSOLE_IO.outputLine("Извлечение: "+ unpackDirectory + "\t|\t" + entry);

        if (entry.isDirectory()) {
            Files.createDirectories(targetPath);
        } else {
            try {
                Files.copy(entry.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (DirectoryNotEmptyException exception) {
                UtilConsole.CONSOLE_IO.outputLine(exception);
            }
            BasicFileAttributeView fileAttributes = Files.getFileAttributeView(targetPath, BasicFileAttributeView.class);
            fileAttributes.setTimes(
                    FileTime.fromMillis(entry.getLastModifiedTime()),
                    null,
                    FileTime.fromMillis(entry.getCreationTime())
            );
        }
    }

    private void ensureParentDirExists(Path targetPath) throws IOException {
        Path parentDirectory = targetPath.getParent();
        Files.createDirectories(parentDirectory);
    }

    private ArchiveEntry nextEntry() throws IOException {
        if (currentEntry != null && !currentEntry.isDirectory()) {
            currentEntry.getInputStream().close();
        }
        ArchiveEntry nextEntry = readEntry();
        if (nextEntry == null) {
            return null;
        }
        currentEntry = nextEntry;
        return currentEntry;
    }

    private ArchiveEntry readEntry() throws IOException {
        try {
            ArchiveEntry entry = new ArchiveEntry();
            entry.setName(inputStream.readUTF());
            entry.setCreationTime(inputStream.readLong());
            entry.setLastModifiedTime(inputStream.readLong());
            entry.setDirectory(inputStream.readBoolean());

            if (entry.isDirectory()){
                entry.setInputStream(null);
            }
            else {
                entry.setInputStream(getCompressorInputStream(new ArchiveInputStream(inputStream)));
            }

            return entry;
        } catch (EOFException e) {
            return null;
        }
    }

    private InputStream getCompressorInputStream(ArchiveInputStream archiveInputStream) throws IOException {
        return new BZip2CompressorInputStream(archiveInputStream);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
