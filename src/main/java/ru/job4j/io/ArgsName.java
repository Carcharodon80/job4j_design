package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.1. Именованные аргументы [#295518]
 * 5.2. Архивировать проект [#861]
 * 7. Scanner [#504791]
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
                if (isValid(pair)) {
                    String[] keyAndValue = pair.split("=");
                    values.put(keyAndValue[0].substring(1), keyAndValue[1]);
                } else {
                    throw new IllegalArgumentException("Invalid key or value");
                }
            }
        }
    }

    private boolean isValid(String line) {
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
        return names;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
