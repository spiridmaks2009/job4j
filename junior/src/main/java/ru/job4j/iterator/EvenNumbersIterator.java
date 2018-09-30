package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenNumbersIterator class
 *
 * @author maksimspiridonov
 */
public class EvenNumbersIterator implements Iterator {

    /**
     * Исходный массив данных
     */
    private int[] array;

    /**
     * Индекс массива
     */
    private int index;

    /**
     * Конструктор
     *
     * @param array
     */
    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    /**
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (array.length > index) {
            int nextIndex = index;
            while (nextIndex < array.length) {
                if (array[nextIndex++] % 2 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = array[index++];
        while (i % 2 != 0 && array.length > index) {
            i = array[index++];
        }
        return i;
    }
}
