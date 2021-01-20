package testquickresto.menu.items;


import testquickresto.menu.builder.Item;
import testquickresto.executor.CommandExecutor;
import testquickresto.executor.CommandEnum;

/**
 * The type Pack.
 */
public class Pack implements Item {
    @Override
    public String displayedName() {
        return CommandEnum.PACK.name();
    }

    @Override
    public void perform() throws Exception {
        CommandExecutor.execute(CommandEnum.PACK);
    }
}
