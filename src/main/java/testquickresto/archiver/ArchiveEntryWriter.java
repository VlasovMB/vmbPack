package testquickresto.archiver;


import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import testquickresto.menu.UtilConsole;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * The type Archive entry writer.
 */
public class ArchiveEntryWriter implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(ArchiveEntryWriter.class);


    private final DataOutputStream outputStream;
    private final Path outputFile;


    /**
     * Instantiates a new Archive entry writer.
     *
     * @param outputFile the output file
     * @throws IOException the io exception
     */
    public ArchiveEntryWriter(Path outputFile) throws IOException {
        this.outputStream = new DataOutputStream(Files.newOutputStream(
                outputFile,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
        this.outputStream.writeInt(ArchiveUtil.ARCHIVE_KEY);
        this.outputFile = outputFile;

    }

    /**
     * Archive manager.
     *
     * @param path the path
     * @throws IOException the io exception
     */
    public void archiveManager(final Path path) throws IOException {
        boolean isDirectory = Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
        if (isDirectory) {
            boolean isRootDirectory = path.getParent() == null;
            if (isRootDirectory) {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        addEntryToArchive(dir, path, Files.readAttributes(dir, BasicFileAttributes.class));
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        addEntryToArchive(file, path, attrs);
                        return FileVisitResult.CONTINUE;
                    }
                });

            } else {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        addEntryToArchive(dir, path.getParent(), Files.readAttributes(dir, BasicFileAttributes.class));
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                        addEntryToArchive(file, path.getParent(), attrs);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } else {
            addEntryToArchive(path, path.getParent(), Files.readAttributes(path, BasicFileAttributes.class));
        }
    }

    private void addEntryToArchive(Path pathEntry, Path baseDirectory, BasicFileAttributes entryAttributes) throws IOException {
        UtilConsole.CONSOLE_IO.outputLine("Архивация: " + pathEntry + " > " + outputFile);

        outputStream.writeUTF(String.valueOf(baseDirectory.resolve(baseDirectory.relativize(pathEntry))));
        outputStream.writeLong(entryAttributes.creationTime().toMillis());
        outputStream.writeLong(entryAttributes.lastModifiedTime().toMillis());
        outputStream.writeBoolean(entryAttributes.isDirectory());
        if (entryAttributes.isDirectory()) {
            return;
        }
        try (OutputStream contentStream = getCompressorOutputStream(new ArchiveOutputStream(outputStream))) {
            Files.copy(pathEntry, contentStream);
        }

    }

    private OutputStream getCompressorOutputStream(ArchiveOutputStream archiveOutputStream) throws IOException {
        return new BZip2CompressorOutputStream(archiveOutputStream);
    }

    /**
     * Add entries to archive.
     *
     * @param files the files
     */
    public void addEntriesToArchive(Set<Path> files) {
        files.forEach(path ->
        {
            try {
                archiveManager(path);
            } catch (NoSuchFileException noSuchFileException) {
                logger.log(Level.ERROR, "Файл или директория не найдены: " + noSuchFileException.getMessage());
            } catch (IOException e) {
                logger.log(Level.FATAL, "Системная ошибка: " + e.toString());
            }
        });
    }

    @Override
    public void close() throws IOException {
        int sizeOutputStream = outputStream.size();
        outputStream.close();
        if (sizeOutputStream <= 4) {
            Files.delete(outputFile);
            logger.warn("Архив не был создан");
        } else logger.info("Архив был создан: " + outputFile.toString());

    }
}
