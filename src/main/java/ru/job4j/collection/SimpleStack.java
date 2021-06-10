package ru.job4j.collection;

/**
 * 4. Используя контейнер на базе связанного списка создать контейнер Stack [#71474]
 */
public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public T poll() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }

    public int size() {
        return linked.size();
    }
}
