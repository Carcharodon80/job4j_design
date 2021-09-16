package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * 4.2. Поиск дубликатов [#315066]
 * Из файла генерирует fileProperty и кладет их в мапу.
 * Если такой fileProperty уже есть - на консоль оба файла (который уже в мапе и текущий)
 */
public class DuplicateVisitor extends SimpleFileVisitor<Path> {
    private static Map<FileProperty, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (map.containsKey(fileProperty)) {
            System.out.println(map.get(fileProperty).toAbsolutePath());
            System.out.println(file.toAbsolutePath());
            System.out.println();
        } else {
            map.put(fileProperty, file);
        }
        return FileVisitResult.CONTINUE;
    }
}
