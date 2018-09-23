package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter implements Iterator{

    private Iterator<Iterator<Integer>> iterator;
    private Iterator<Integer> innerIterator;


    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.iterator = it;
        this.innerIterator = it.next();
        return this;
    }

    /**
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (innerIterator.hasNext()) return true;
        else if (iterator.hasNext()) return true;
        return false;
    }

    /**
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        if(!innerIterator.hasNext())
            innerIterator = iterator.next();
        return innerIterator.next();
    }
}
