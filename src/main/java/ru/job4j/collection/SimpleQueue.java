package ru.job4j.collection;

/**
 * 5. Очередь на двух стеках [#160]
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * удаляем самый ранний из добавленных элементов из out,
     * если out пустой - перед удалением перемещаем элементы из in в out, при этом переворачиваем
     * @return - удаляемый элемент
     */
    public T poll() {
        T rsl;
        if (out.size() == 0) {
            while (in.size() != 0) {
                out.push(in.pop());
            }
        }
        rsl = out.pop();
        return rsl;
    }

    /**
     * добавляем элемент в in
     *
     * @param value
     */
    public void push(T value) {
        in.push(value);
    }
}
