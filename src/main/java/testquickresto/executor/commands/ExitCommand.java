package testquickresto.executor.commands;

import testquickresto.menu.UtilConsole;

/**
 * The type Exit command.
 */
public class ExitCommand implements Command {
    @Override
    public void execute() throws Exception{
        UtilConsole.CONSOLE_IO.outputLine("До свидания");
    }
}
