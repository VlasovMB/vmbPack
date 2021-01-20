package testquickresto.menu.items;


import testquickresto.menu.builder.Item;
import testquickresto.executor.CommandExecutor;
import testquickresto.executor.CommandEnum;

/**
 * The type Unpack.
 */
public class Unpack implements Item {

    @Override
    public String displayedName() {
        return CommandEnum.UNPACK.name();
    }

    @Override
    public void perform() throws Exception {
        CommandExecutor.execute(CommandEnum.UNPACK);
    }
}
