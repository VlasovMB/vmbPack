package testquickresto.menu.items;

import testquickresto.menu.builder.Item;
import testquickresto.executor.commands.ExitCommand;
import testquickresto.executor.CommandEnum;

/**
 * The type Exit.
 */
public class Exit implements Item {
    @Override
    public String displayedName() {
        return CommandEnum.EXIT.name();
    }

    @Override
    public void perform() throws Exception {
        new ExitCommand().execute();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
