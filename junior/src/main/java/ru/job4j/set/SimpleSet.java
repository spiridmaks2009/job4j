package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E> implements Iterable<E> {

    /**
     * Внутренний контейнер
     */
    private E[] container;

    /**
     * Индекс
     */
    private int index;

    /**
     * Контейнер
     *
     * @param container - контейнер
     */
    public SimpleSet(E[] container) {
        this.container = container;
    }

    /**
     * Добавить элемент
     *
     * @param e - элемент
     */
    public void add(E e){
        if(e != null && !isExist(e)) {
            container[index++] = e;
        }
    }

    /**
     * Проверить существует ли элемент в контейнере
     *
     * @param e - элемент
     * @return {@code true} if element exist in container
     */
    public boolean isExist(E e) {
        for(E value : container) {
            if (e.equals(value)) {
                return true;
            }
        }
        return false;
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
             * Позиция итератора
             */
            private int position;

            /**
             * Проверить есть ли следующий элемент
             *
             * @return {@code true} if has next
             */
            @Override
            public boolean hasNext() {
                return container[index] != null && position < index;
            }

            /**
             * Следующий элемент
             *
             * @return элемент
             */
            @Override
            public E next() {
                return container[position++];
            }
        };
    }
}
