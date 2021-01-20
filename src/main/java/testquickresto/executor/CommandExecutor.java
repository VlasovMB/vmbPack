package testquickresto.executor;


import testquickresto.executor.commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Command executor.
 */
public class CommandExecutor {
    private CommandExecutor() {
    }

    private static final Map<CommandEnum, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put(CommandEnum.PACK, new ArchivePackCommand());
        commandsMap.put(CommandEnum.UNPACK, new ArchiveUnpackCommand());
        commandsMap.put(CommandEnum.EXIT, new ExitCommand());
    }

    /**
     * Execute.
     *
     * @param operation the operation
     * @throws Exception the exception
     */
    public static void execute(CommandEnum operation) throws Exception {
        commandsMap.get(operation).execute();
    }
}