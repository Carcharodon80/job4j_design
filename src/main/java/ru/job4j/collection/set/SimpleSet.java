package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * 1. Реализовать коллекцию Set на массиве [#996]
 */
public class SimpleSet<T> implements Set<T> {
    private final SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!this.contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
