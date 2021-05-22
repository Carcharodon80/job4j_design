package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.2. Создать итератор четные числа [#150]
 */
public class EvenIt implements Iterator<Integer> {
    private int point = 0;
    private final int[] data;

    public EvenIt(int[] data) {
        this.data = data;
    }

    /**
     * @return true - если дальше есть четное число
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = point; i < data.length; i++) {
            if (isEven(data[i])) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    /**
     * перебираем оставшиеся числа, пока не найдем четное, возвращаем его (и увеличиваем указатель на 1)
     *
     * @return четное число
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = 0;
        while (point < data.length) {
            if (isEven(data[point])) {
                rsl = data[point++];
                break;
            } else {
                point++;
            }
        }
        return rsl;
    }


    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
