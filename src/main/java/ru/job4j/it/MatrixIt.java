package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 5.1.1. Итератор для двухмерного массива int[][] [#9539]
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Если в ряду остались значения - возвращаем true, иначе - начинаем перебирать следующие ряды,
     * если находим не пустой - ставим указатель на начало этого ряда и возвращаем true.
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (column < data[row].length) {
            rsl = true;
        } else {
            for (int i = row + 1; i < data.length; i++) {
                if (data[i].length > 0) {
                    row = i;
                    column = 0;
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
