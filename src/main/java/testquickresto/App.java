package testquickresto;

import testquickresto.menu.UtilConsole;
import testquickresto.menu.builder.Menu;


/**
 * The type App.
 */
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Menu(UtilConsole.itemsMenu, UtilConsole.CONSOLE_IO).runMenu();
    }

}

