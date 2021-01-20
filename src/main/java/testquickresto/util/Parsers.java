package testquickresto.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Parsers.
 */
public class Parsers {
    private Parsers() {
    }

    /**
     * Parse String to Set<Path>.
     *
     * @param prompt String raw
     * @return Set<Path>
     */
    public static Set<Path> parseStrToSetPaths(String prompt) {
        if (prompt==null) return Collections.emptySet();
        return Arrays
                .stream(prompt.split("\\s+"))
                .map(rawPath -> Paths.get(rawPath.trim()).normalize())
                .filter(path -> !path.toString().isEmpty())
                .collect(Collectors.toSet());
    }
}
