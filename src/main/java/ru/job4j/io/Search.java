package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы. [#106929]
 * 5. Валидация параметров запуска. [#246865]
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (isValid(args)) {
            Path start = Path.of(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static boolean isValid(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Укажите начальную папку поиска и расширение нужных файлов");
        }
        return true;
    }
}
