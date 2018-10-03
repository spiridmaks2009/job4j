package ru.job4j.list.Task_5_3_1;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DinamicArrayList
 *
 * @param <E>
 *
 * @author maksimspiridonov
 */
public class DynamicArrayList<E> implements Iterable<E> {

    /**
     * Внутренний массив для хранения элементов
     */
    private Object[] container;

    /**
     * Счетчик модификации коллекции
     */
    private int modCount;

    /**
     * Индекс элемента контейнера
     */
    private int index;

    /**
     * Размер коллекции
     */
    private int size;

    /**
     * Конструктор
     *
     * @param container - массив данных
     */
    public DynamicArrayList(Object[] container) {
        this.container = container;
        size = container.length;
        modCount++;
        index = size;
    }

    /**
     * Добавить элемент в коллекцию
     *
     * @param value - значение элемента
     */
    public void add(E value) {
        if (index == size) {
            addSize();
        }
        container[index++] = value;
        modCount++;
    }

    /**
     * Увеличить размер контейнера
     */
    public void addSize() {
        Object[] temp = new Object[size * 2];
        System.arraycopy(this.container,0, temp, 0, size);
        this.container = temp;
    }

    /**
     * Получить элемент по индексу
     *
     * @param index - индекс
     * @return значение элемента
     */
    public E get(int index) {
        return (E) container[index];
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int position;

            /**
             * Код модификации коллекции
             */
            private int expectedModCount = modCount;

            /**
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return container[position] != null && position < size;
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
                return (E) container[position++];
            }
        };
    }
}
