package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

/**
 * 5.2.1. Реализовать SimpleArray<T> [#156]
 * @param <T>
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int size = 0;

    public SimpleArray(int count) {
        this.data = new Object[count];
    }

    public void add(T model) {
        if (size == data.length) {
            System.out.println("Невозможно добавить элемент");
        } else {
            data[size++] = model;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        Object[] tempData = new Object[data.length];
        System.arraycopy(data, 0, tempData, 0, index);
        System.arraycopy(data, index + 1, tempData, index, size - index - 1);
        data = tempData;
        size--;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointIterator = 0;

            @Override
            public boolean hasNext() {
                return pointIterator < size;
            }

            @Override
            public T next() {
                return (T) data[pointIterator++];
            }
        };
    }
}
