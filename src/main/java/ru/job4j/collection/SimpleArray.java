package ru.job4j.collection;

import java.util.*;

/**
 * 1. Динамический список на массиве. [#158]
 * 1. Реализовать коллекцию Set на массиве [#996]
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int size = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length + 10);
        }
        container[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int iteratorPoint = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return iteratorPoint < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[iteratorPoint++];
            }
        };
    }
}
