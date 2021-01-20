package testquickresto.menu.builder;

/**
 * The type Menu.
 */
public class Menu {

	private final Item[] items;
	private final InputOutput inputOutput;

	/**
	 * Instantiates a new Menu.
	 *
	 * @param items       the items menu
	 * @param inputOutput the input output
	 */
	public Menu(Item[] items, InputOutput inputOutput) {
		this.items = items;
		this.inputOutput = inputOutput;
	}

	/**
	 * Run console menu.
	 */
	public void runMenu() {
		inputOutput.output("Программа архиватор, выполнена для тестового задания QuickResto\n");
		while (true) {
			inputOutput.output("\n");
			for (int i = 0; i < items.length; i++) {
				inputOutput.outputLine((i + 1) + ". " + items[i].displayedName());
			}
			Integer selected = inputOutput.inputInteger("Введите номер меню:", 1, items.length);
			if (selected == null) continue;
			try {
				items[selected - 1].perform();
			} catch (Exception e) {
				inputOutput.outputLine(e.toString());
			}
			if (items[selected - 1].isExit())
				return;
		}
	}
}
