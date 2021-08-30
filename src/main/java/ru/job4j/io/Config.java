package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 1. Читаем файл конфигурации [#858]
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    /**
     * читает строки из файла, разделенные "=" кладет в мапу values
     */
    public void load() {
        try (BufferedReader out = new BufferedReader(new FileReader(this.path))) {
            while (out.ready()) {
                String string = out.readLine();
                if (string.startsWith("=")) {
                    throw new IllegalArgumentException();
                }
                if (!string.startsWith("#") && (!string.isEmpty())) {
                    String[] keyAndValue = string.split("=");
                    if (!string.endsWith("=")) {
                        values.put(keyAndValue[0], keyAndValue[1]);
                    } else {
                        values.put(keyAndValue[0], null);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
