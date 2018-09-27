package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayIterator
 *
 * @author maksimspiridonov
 */
public class ArrayIterator implements Iterator {

    /**
     * Содержит исходный массив
     */
    private int[][] outerArray;

    /**
     * Содержит индекс внешнего массива
     */
    private int outerIndex;

    /**
     * Содержит индекс внутреннего массива
     */
    private int innerIndex;

    /**
     * Конструктор
     *
     * @param outerArray
     */
    public ArrayIterator(int[][] outerArray) {
        this.outerArray = outerArray;
    }

    /**
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {

        boolean flag = true;
        if (!(outerIndex < outerArray.length && innerIndex < outerArray[outerIndex].length)) {
            flag = false;
        }
        return flag;
    }

    /**
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int[] innerArray = outerArray[outerIndex];
        int number = 0;
        if (innerIndex < innerArray.length - 1) {
            number  = innerArray[innerIndex++];
        } else if (innerIndex == innerArray.length - 1) {
            number = innerArray[innerIndex];
            outerIndex++;
            innerIndex = 0;
        }
        return number;
    }

}
