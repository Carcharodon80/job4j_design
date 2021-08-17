package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * 0.1. FileOutputStream. [#252488]
 * метод main записывает таблицу умножения в файл matrix.txt
 */
public class MatrixFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    out.write((i * j + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
