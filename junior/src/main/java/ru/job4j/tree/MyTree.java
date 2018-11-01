package ru.job4j.tree;

import java.util.*;

/**
 * класс MyTree
 *
 * @param <E>
 *
 * @author maksimspiridonov
 */
public class MyTree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Корень дерева
     */
    private Node<E> root;

    /**
     * Конструктор
     *
     * @param root
     */
    public MyTree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     *
     * @param parent parent.
     * @param child  child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean isAdded = false;
        if (!findBy(child).isPresent()) {
            Optional<Node<E>> result = findBy(parent);
            isAdded = result.isPresent();
            if (isAdded) {
                result.get().add(new Node<>(child));
            }
        }
        return isAdded;
    }

    /**
     * Поиск элемента
     *
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Определить бинарное дерево или нет
     *
     * @return
     */
    public boolean isBinary() {
        boolean isBinary = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            List<Node<E>> childs = el.leaves();
            if (childs.size() > 2) {
                isBinary = false;
                break;
            }
            for (Node<E> child : childs) {
                data.offer(child);
            }
        }
        return isBinary;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
                private Queue<Node<E>> data = new LinkedList<>();
                private Node<E> root;

                @Override
                public boolean hasNext() {
                    return (!data.isEmpty() || root != null);
                }

                @Override
                public E next() {
                    if(data.isEmpty()) {
                        data.offer(root);
                    }
                    Node<E> el;
                    if (!hasNext()) {
                        throw new IndexOutOfBoundsException();
                    } else {
                        el = data.poll();
                        for (Node<E> child : el.leaves()) {
                            data.offer(child);
                        }
                    }
                    return el.getValue();
                }
            };
    }
}
