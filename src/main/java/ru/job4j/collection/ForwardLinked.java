package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 3. Удалить head в односвязном списке. [#51424]
 * 5. Очередь на двух стеках [#160]
 * 6. Перевернуть связанный список [#161]
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            return;
        }
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        oldHead.value = null;
        return rsl;
    }

    public T deleteLast() {
        T rsl;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            rsl = head.value;
            head = null;
        } else {
            Node<T> currentNode = head;
            Node<T> previousNode = null;
            while (currentNode.next != null) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            rsl = currentNode.value;
            currentNode.value = null;
            previousNode.next = null;
        }
        return rsl;
    }

    public int size() {
        int rsl;
        if (head == null) {
            rsl = 0;
        } else {
            rsl = 1;
            Node<T> currentNode = head;
            while (currentNode.next != null) {
                rsl++;
                currentNode = currentNode.next;
            }
        }
        return rsl;
    }

    /**
     * переворачивает односвязный список
     * @return false - если не надо переворачивать (size <= 1), иначе true
     */
    public boolean revert() {
        boolean rsl = true;
        if (head == null || head.next == null) {
            rsl = false;
        } else {
            Node<T> currentNode = head;
            Node<T> nextNode;
            Node<T> previousNode = null;
            while (currentNode != null) {
                nextNode = currentNode.next;
                currentNode.next = previousNode;
                previousNode = currentNode;
                currentNode = nextNode;
            }
            head = previousNode;
        }

        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }


}
