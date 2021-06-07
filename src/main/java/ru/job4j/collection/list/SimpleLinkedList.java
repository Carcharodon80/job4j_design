package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 2. Создать контейнер на базе связанного списка [#159]
 */
public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;

    @Override
    public void add(E value) {
        if (size == 0) {
            first = new Node<>(value, null, null);
            last = first;
        } else {
            last.next = new Node<>(value, last, null);
            last = last.next;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E rsl;
        if (index == 0) {
            rsl = first.item;
        } else if (index == size - 1) {
               rsl = last.item;
        } else {
            Node<E> currentNode = first;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            rsl = currentNode.item;
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> iteratorPoint = first;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return iteratorPoint != null;
            }

            @Override
            public E next() {
                E rsl;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                rsl = iteratorPoint.item;
                iteratorPoint = iteratorPoint.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
