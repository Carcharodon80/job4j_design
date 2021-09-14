package ru.job4j.io;

import java.io.*;

/**
 * 2. Анализ доступности сервера. [#859]
 * Берет статус работы сервера из логов (source) и записывает время когда сервер не работал (в target)
 */
public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean serverWork = true;
            String timeline = "";
            String line;
            while (in.ready()) {
                line = in.readLine();
                if (serverWork) {
                    if (line.startsWith("400") || line.startsWith("500")) {
                        timeline = timeline + getTime(line) + ";";
                        serverWork = false;
                    }
                } else {
                    if (line.startsWith("200") || line.startsWith("300")) {
                        timeline = timeline + getTime(line) + System.lineSeparator();
                        serverWork = true;
                    }
                }
                try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
                    out.write(timeline);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTime(String line) {
        String[] array = line.split(" ");
        return array[1];
    }
}
