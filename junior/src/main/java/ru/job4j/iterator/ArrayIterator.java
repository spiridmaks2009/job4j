package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator implements Iterator {

    private int[][] outerArray;
    private int[] innerArray;

    private int outerIndex = 0, innerIndex = 0;

    public ArrayIterator(int[][] outerArray) {
        if(outerIndex >= outerArray.length) throw new NoSuchElementException();
        this.outerArray = outerArray;
        this.innerArray = outerArray[outerIndex++];
    }

    /**
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {

        if (innerArray.length <= innerIndex) {
            if (outerArray.length > outerIndex) {
                innerArray = outerArray[outerIndex++];
                innerIndex = 0;
            }
        }
        return innerArray.length > innerIndex;
    }

    /**
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() throws NoSuchElementException{
        if (outerIndex >= outerArray.length || innerIndex >= innerArray.length) throw new NoSuchElementException();
        if (!hasNext()) {
            if (outerArray.length > outerIndex) {
                innerArray = outerArray[outerIndex++];
                innerIndex = 0;
            }
        }
        return innerArray[innerIndex++];
    }

}
