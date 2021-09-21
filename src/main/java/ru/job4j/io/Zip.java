package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 5.2. Архивировать проект [#861]
 */
public class Zip {
    private static ArgsName argsName;

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                ZipEntry zipEntry = new ZipEntry(path.toString());
                zip.putNextEntry(zipEntry);
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        isValid(args);
        List<Path> files = Search.search(Path.of(argsName.get("d")),
                p -> !p.toFile().getName().endsWith("." + argsName.get("e")));
        packFiles(files, new File(argsName.get("o")));

    }

    private static void isValid(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Укажите архивируемую папку,"
                    + " расширения файлов, которые не надо архивировать"
                    + " и имя полученного архива");
        }
        argsName = ArgsName.of(args);
        if (argsName.get("d") == null) {
            throw new IllegalArgumentException("Не указана архивируемая папка");
        } else if (argsName.get("o") == null) {
            throw new IllegalArgumentException("Не указано имя конечного архива");
        } else if (argsName.get("e") == null) {
            throw new IllegalArgumentException("Не указано расширение файлов, не включаемых в архив");
        }
        if (!new File(argsName.get("d")).isDirectory()) {
            throw new IllegalArgumentException("Неверное имя архивируемой папки");
        }
    }
}
