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
     * @return true - если дальше есть четное число, и ставим указатель на это число
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = point; i < data.length; i++) {
            if (isEven(data[i])) {
                rsl = true;
                point = i;
                break;
            }
        }
        return rsl;
    }

    /**
     * возвращаем четное число (указатель обновляется при вызове hasNext)
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
