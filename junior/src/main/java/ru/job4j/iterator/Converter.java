package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter class
 *
 * @author maksimspiridonov
 */
public class Converter implements Iterator {

    /**
     * Итератор итераторов
     */
    private Iterator<Iterator<Integer>> iterator;

    /**
     * Внутренний итератор
     */
    private Iterator<Integer> innerIterator;


    /**
     * @param it
     * @return
     */
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
        if (innerIterator.hasNext()) {
            return true;
        } else if (iterator.hasNext()) {
            return true;
        }
        return false;
    }

    /**
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() {
        if (!innerIterator.hasNext()) {
            innerIterator = iterator.next();
        }
        return innerIterator.next();
    }
}
