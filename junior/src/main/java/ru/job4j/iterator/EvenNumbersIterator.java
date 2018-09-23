package ru.job4j.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {

    private int[] array;
    private int index = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    /**
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if(array.length > index){
            int nextIndex = index;
            while(nextIndex < array.length)
            {
                if(array[nextIndex++]%2==0) return true;
            }
        }
        return false;
    }

    /**
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() throws NoSuchElementException{
        int i = array[index++];
        while (i % 2 != 0 && array.length > index) {
            i = array[index++];
        }
        return i;
    }
}
