package testquickresto.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.InvalidPathException;
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
    private static final Logger logger = LogManager.getLogger(Parsers.class);
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
                .distinct()
                .map(rawPath ->{
                    Path tempPath = Paths.get("");
                    try {
                        tempPath = Paths.get(rawPath.trim()).normalize();
                    }catch (InvalidPathException e){
                        logger.error("Некорректная ссылка: " + e.getMessage());
                    }
                    return tempPath;
                })
                .filter(path -> !path.toString().isEmpty())
                .collect(Collectors.toSet());
    }
}
