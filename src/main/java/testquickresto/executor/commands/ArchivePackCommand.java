package testquickresto.executor.commands;

import testquickresto.archiver.ArchiveEntryWriter;
import testquickresto.menu.UtilConsole;
import testquickresto.util.Constants;
import testquickresto.util.Parsers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * The type Archive pack command.
 */
public class ArchivePackCommand implements Command {
    @Override
    public void execute() throws Exception {
        String rawString = UtilConsole.CONSOLE_IO.inputString("Введите имена файлов или директории для архивации:");
        pack(Paths.get(Constants.FILE_ARCHIVE), Parsers.parseStrToSetPaths(rawString)); //Файл архива можно передать как параметр
    }

        private void pack(Path archive, Set<Path> paths) throws IOException {
        try (ArchiveEntryWriter archiveWriter = new ArchiveEntryWriter(archive)) {
            archiveWriter.addEntriesToArchive(paths);
        }
    }
}
