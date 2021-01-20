package testquickresto.menu.builder;

/**
 * The interface Item.
 */
public interface Item {

    /**
     * Displayed name string.
     *
     * @return the string
     */
    String displayedName();

    /**
     * Perform.
     *
     * @throws Exception the exception
     */
    void perform() throws Exception;

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    default boolean isExit() {
		return false;
	}
}
