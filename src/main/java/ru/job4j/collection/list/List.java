package ru.job4j.collection.list;

/**
 * 2. Создать контейнер на базе связанного списка [#159]
 */
public interface List<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
