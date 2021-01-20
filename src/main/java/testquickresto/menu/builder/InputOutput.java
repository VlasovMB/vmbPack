package testquickresto.menu.builder;

import java.util.function.Function;

/**
 * The interface Input output.
 */
public interface InputOutput {

    /**
     * Input string string.
     *
     * @param prompt the prompt
     * @return the string
     */
    String inputString(String prompt);

    /**
     * Output.
     *
     * @param obj the obj
     */
    void output(Object obj);

    /**
     * Output line.
     *
     * @param obj the obj
     */
    default void outputLine(Object obj) {
        output(obj.toString() + "\n");
    }

    /**
     * Input object r.
     *
     * @param <R>         the type parameter
     * @param prompt      the prompt
     * @param errorPrompt the error prompt
     * @param mapper      the mapper
     * @return the r
     */
    default <R> R inputObject(String prompt, String errorPrompt, Function<String, R> mapper) {
        while (true) {
            String text = inputString(prompt);
            if (text == null)
                return null;
            R res = mapper.apply(text);
            if (res != null)
                return res;
            outputLine(errorPrompt);
        }
    }

    /**
     * Input integer integer.
     *
     * @param prompt the prompt
     * @return the integer
     */
    default Integer inputInteger(String prompt) {
        return inputObject(prompt, "Это не число", s -> {
            try {
                Integer res = Integer.parseInt(s);
                return res;
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    /**
     * Input integer integer.
     *
     * @param prompt   the prompt
     * @param minValue the min value
     * @param maxValue the max value
     * @return the integer
     */
    default Integer inputInteger(String prompt, Integer minValue, Integer maxValue) {
        return inputObject(prompt, String.format("Это не число в ранге [%d-%d]",
                minValue, maxValue), s -> {
            try {
                Integer res = Integer.parseInt(s);
                return res >= minValue && res <= maxValue ? res : null;
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

}
