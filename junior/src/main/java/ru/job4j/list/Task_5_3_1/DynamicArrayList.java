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
    Object[] container;

    /**
     * Счетчик модификации коллекции
     */
    int modCount;


    /**
     * Размер коллекции
     */
    int size;

    /**
     * Конструктор
     *
     * @param container - массив данных
     */
    public DynamicArrayList(Object[] container) {
        this.container = container;
        size = container.length;
        modCount++;
    }

    /**
     * Добавить элемент в коллекцию
     *
     * @param value - значение элемента
     */
    public void add(E value) {
        Object[] temp = new Object[size + 1];
        System.arraycopy(container, 0, temp, 0, size);
        temp[size] = value;
        this.container = temp;
        modCount++;
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

            /**
             * Внутренний итератор коллекции
             */
            Iterator<E> it = (Iterator<E>) Arrays.asList(container).iterator();


            /**
             * Код модификации коллекции
             */
            int expectedModCount = modCount;

            /**
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
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
                return it.next();
            }
        };
    }
}
