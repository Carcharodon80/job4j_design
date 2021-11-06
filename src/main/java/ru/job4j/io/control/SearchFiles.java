package ru.job4j.io.control;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * 2. Поиск файлов по критерию [#783]
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 */
public class SearchFiles {
    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Path directory = Paths.get(argsName.get("d"));
        String type = argsName.get("t");
        String name = argsName.get("n");
        String output = argsName.get("o");

        List<Path> paths = new ArrayList<>();
        if ("name".equals(type)) {
            paths = search(directory, p -> p.toFile().getName().equals(name));
        } else if ("mask".equals(type)) {
            String mask = name.replace(".", "[.]").
                    replace("*", ".*").
                    replace("?", ".");
            Pattern pattern = Pattern.compile(mask);
            paths = search(directory, p -> pattern.matcher(p.toFile().getName()).find());
        } else if ("regex".equals(type)) {
            Pattern pattern = Pattern.compile(name);
            paths = search(directory, p -> pattern.matcher(p.toFile().getName()).find());
        }
        writeToFile(paths, output);
    }

    private static List<Path> search(Path root, Predicate<Path> condition) {
        Searcher searcher = new Searcher(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    private static void writeToFile(List<Path> paths, String output) {
        try (FileWriter out = new FileWriter(output)) {
            for (Path path : paths) {
                out.write(path.toString() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
