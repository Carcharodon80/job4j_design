package ru.job4j.it;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 5.1.4. FlatMap для Iterator<Iterator> [#152]
 * @param <T>
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * сначала перебираем итераторы, чтобы найти не пустой
     * @return - true, если текущий итератор еще имеет неотданные значения
     */
    @Override
    public boolean hasNext() {
        boolean rsl = false;
        while (data.hasNext() && !cursor.hasNext()) {
            cursor = data.next();
        }
        if (cursor.hasNext()) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
