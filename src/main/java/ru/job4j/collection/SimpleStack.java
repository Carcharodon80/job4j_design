package ru.job4j.collection;

/**
 * 4. Используя контейнер на базе связанного списка создать контейнер Stack [#71474]
 * 5. Очередь на двух стеках [#160]
 */
public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();


    /**
     * удаляем элемент из стэка
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * добавляем элемент в конец стэка
     */
    public void push(T value) {
        linked.add(value);
    }

    /**
     * размер стэка
     */
    public int size() {
        return linked.size();
    }
}
