package ru.job4j.io.control;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 2. Поиск файлов по критерию [#783]
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Keys not found");
        } else {
            for (String pair : args) {
                if (pairIsValid(pair)) {
                    String[] keyAndValue = pair.split("=");
                    values.put(keyAndValue[0].substring(1), keyAndValue[1]);
                } else {
                    throw new IllegalArgumentException("Invalid key or value");
                }
            }
        }
    }

    private boolean pairIsValid(String line) {
        boolean result = true;
        if (!line.startsWith("-")
                || line.endsWith("=")
                || (line.charAt(1) == '=')
                || (line.split("=").length != 2)) {
            result = false;
        }
        return result;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        names.argumentsIsValid();
        return names;
    }

    private void argumentsIsValid() {
        if (!values.containsKey("d")) {
            throw new IllegalArgumentException("Не указана директория поиска");
        } else if (!values.containsKey("n")) {
            throw new IllegalArgumentException("Не указано имя файла, маска или регулярное выражение");
        } else if (!values.containsKey("t")) {
            throw new IllegalArgumentException("Не указан тип поиска");
        } else if (!values.containsKey("o")) {
            throw new IllegalArgumentException("Укажите имя выходного файла");
        }
        if (!Paths.get(values.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Папка поиска не существует");
        }
        String type = values.get("t");
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Неверный тип поиска");
        }
    }
}
