package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl;
        if (out.size() == 0) {
            while (in.size() != 0) {
                out.push(in.poll());
            }
            rsl = out.poll();
        } else {
            rsl = out.poll();
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }
}
