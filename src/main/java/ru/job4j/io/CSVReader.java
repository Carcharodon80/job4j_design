package ru.job4j.io;

import java.io.*;
import java.util.*;

/**
 * 7. Scanner [#504791]
 */
public class CSVReader {
    private static String delimiter;
    private static String dataIn;
    private static String dataOut;
    private static String filter;

    /**
     * метод читает строки из файла, убирает ненужные поля из строк, и отправляет на вывод
     * @param argsName аргументы
     */
    public static void handle(ArgsName argsName) {
        dataIn = argsName.get("path");
        dataOut = argsName.get("out");
        delimiter = argsName.get("delimiter");
        filter = argsName.get("filter");
        isValidateArguments(argsName);
        List<String> finalContent = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(dataIn))) {
            List<Integer> indexes = new ArrayList<>();
            if (scanner.hasNextLine()) {
                String caption = scanner.nextLine();
                indexes = parseCaptionToIndexes(caption, filter);
                String lineForWrite = modifyLine(caption, indexes);
                finalContent.add(lineForWrite);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String lineForWrite = modifyLine(line, indexes);
                finalContent.add(lineForWrite);
            }
            toWrite(finalContent, dataOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод получает индексы нужных полей из первой строки файла
     * @param caption - первая строка файла
     * @param filter  - названия нужных полей
     * @return индексы полей
     */
    private static List<Integer> parseCaptionToIndexes(String caption, String filter) {
        List<Integer> indexes = new ArrayList<>();
        String[] array = caption.split(delimiter);
        List<String> listFilter = Arrays.asList(filter.split(","));
        for (int i = 0; i < array.length; i++) {
            if (listFilter.contains(array[i])) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    /**
     * Метод убирает из строки ненужные поля
     * @param line    - строка файла
     * @param indexes - индексы полей, которые оставляем
     * @return строка без ненужных полей
     */
    private static String modifyLine(String line, List<Integer> indexes) {
        StringBuilder modifiedLine = new StringBuilder();
        String[] allWords = line.split(";");
        Iterator<Integer> iterator = indexes.iterator();
        while (iterator.hasNext()) {
            modifiedLine.append(allWords[iterator.next()]);
            if (iterator.hasNext()) {
                modifiedLine.append(";");
            }
        }
        return modifiedLine.toString();
    }

    /**
     * метод выводит строки в файл или на консоль
     * @param finalContent - строки с нужными полями
     * @param dataOut      - способ вывода, если stdout - на консоль, иначе в указанный файл
     */
    private static void toWrite(List<String> finalContent, String dataOut) {
        try (PrintWriter out = new PrintWriter(dataOut)) {
            for (String word : finalContent) {
                StringBuilder stringBuilder = new StringBuilder().append(word);
                if ("stdout".equals(dataOut)) {
                    System.out.println(stringBuilder);
                } else {
                    out.write(stringBuilder.toString());
                    out.write(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод проверяет валидность аргументов
     * @param argsName аргументы
     */
    private static void isValidateArguments(ArgsName argsName) {
        if (argsName.getValues().size() < 4) {
            throw new IllegalArgumentException("Недостаточно аргументов.");
        } else if (dataIn == null || dataOut == null || delimiter == null || filter == null) {
            throw new IllegalArgumentException("Указаны неверные аргументы");
        } else if (!new File(dataIn).isFile()) {
            throw new IllegalArgumentException("Указанного файла-источника не существует.");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
