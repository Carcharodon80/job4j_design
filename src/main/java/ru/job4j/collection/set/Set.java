package ru.job4j.collection.set;

/**
 * 1. Реализовать коллекцию Set на массиве [#996]
 */
public interface Set<T> extends Iterable<T> {
    boolean add(T value);
    boolean contains(T value);
}
