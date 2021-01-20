package testquickresto.menu;


import testquickresto.menu.builder.ConsoleInputOutput;
import testquickresto.menu.builder.Item;
import testquickresto.menu.items.Exit;
import testquickresto.menu.items.Pack;
import testquickresto.menu.items.Unpack;


/**
 * The type Util console.
 * Utilities console
 */
public class UtilConsole {
    private UtilConsole() {
    }

    /**
     * The constant CONSOLE_IO.
     */
    public static final ConsoleInputOutput CONSOLE_IO = new ConsoleInputOutput();

    /**
     * The constant itemsMenu.
     * List of menu items
     */
    public static final Item[] itemsMenu = {
            new Pack(),
            new Unpack(),
            new Exit()
    };

}
