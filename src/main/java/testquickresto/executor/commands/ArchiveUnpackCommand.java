package testquickresto.executor.commands;


import testquickresto.archiver.ArchiveEntryReader;
import testquickresto.exceptions.WrongArchiveFileException;
import testquickresto.menu.UtilConsole;
import testquickresto.util.Constants;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Archive unpack command.
 */
public class ArchiveUnpackCommand implements Command {
    @Override
    public void execute() throws Exception {
        UtilConsole.CONSOLE_IO.inputString("Файла архива по умолчанию: "+ Constants.FILE_ARCHIVE
        + "\nПуть распаковки в корне: " + Constants.DIR_UNPACK + "\n\t Нажмите ENTER, чтобы продолжить");
        unpack(Paths.get(Constants.FILE_ARCHIVE), Paths.get(Constants.DIR_UNPACK)); //можно передать как параметры
    }

    private static void unpack(Path archive, Path directory) throws IOException, WrongArchiveFileException {
        try (ArchiveEntryReader archiveReader = new ArchiveEntryReader(archive)) {
            archiveReader.extractToDirectory(directory);
        }

    }
}
