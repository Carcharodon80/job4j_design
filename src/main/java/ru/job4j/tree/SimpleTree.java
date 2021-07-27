package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * добавляем узел со значением child в существующий узел со значением parent
     * если узел parent не существует или узел child уже существует - возвращаем false
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent() && findBy(child).isEmpty()) {
            Node<E> existedParent = findBy(parent).get();
            existedParent.children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * ищет узел с переданным значением
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = i -> i.value.equals(value);
        return findByPredicate(predicate);
    }

    /**
     * проверяет бинарное ли дерево
     * @return false если не бинарное
     */
    public boolean isBinary() {
        Predicate<Node<E>> predicate = i -> i.children.size() > 2;
        return findByPredicate(predicate).isEmpty();
    }

    /**
     * обходит дерево горизонтально
     * каждый узел проверяет на переданное условие
     * если узел соответствует условию - возвращает этот узел (в Optional)
     * если ни один узел не подходит под условие - возвращает Optional.empty()
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (condition.test(node))  {
                rsl = Optional.of(node);
                break;
            }
            queue.addAll(node.children);
        }
        return rsl;
    }
}
