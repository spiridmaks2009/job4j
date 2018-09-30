package ru.job4j.list.Task_5_3_2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DynamicLinkedList класс
 *
 * @param <E>
 *
 * @author maksimspiridonov
 */
public class DynamicLinkedList<E> implements Iterable<E>{

    /**
     * Первый элемент коллекции
     */
    Node<E> first;

    /**
     * Размер коллекции
     */
    private int size;

    /**
     * Счетчик модификации коллекции
     */
    private int modCount;


    /**
     * Добавить элемент коллекции
     *
     * @param date
     */
    public void add(E date) {
        Node<E> newNode = new Node<E>(date);
        newNode.next = this.first;
        this.first = newNode;
        size++;
        modCount++;
    }

    /**
     * Получить элемент по индексу
     *
     * @param index - индекс
     * @return значение элемента
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /**
             * Элемент итератора
             */
            Node<E> node = first;

            /**
             * Код модификации коллекции
             */
            int expectedModCount = modCount;

            /**
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                if (node.next != null) {
                    return true;
                }
                return false;
            }

            @Override
            /**
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            public E next() {
                if(expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return node.next.date;
            }
        };
    }

    /**
     * Внутренний класс для представления элемента коллекции
     *
     * @param <E>
     */
    private class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
