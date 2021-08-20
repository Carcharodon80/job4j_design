package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

/**
 * 0.2. FileInputStream [#4898]
 * Читает числа из файла, проверяет на четность и выводит на консоль
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] numbers = Arrays.stream(text.toString().split(System.lineSeparator())).
                mapToInt(Integer::parseInt).
                toArray();
        for (int number : numbers) {
            if (number % 2 == 0) {
                System.out.println(number + " - четное число");
            } else {
                System.out.println(number + " - нечетное число");
            }
        }

    }
}
