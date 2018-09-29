package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter class
 *
 * @author maksimspiridonov
 */
public class Converter {

    /**
     * @param it - Исходный итератор
     * @return Итератор целых чисел
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            /**
             * Внутренний итератор
             */
            Iterator<Integer> innerIterator;

            /**
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                boolean flag = false;
                if (innerIterator == null || !innerIterator.hasNext()) {
                    while (it.hasNext()) {
                        innerIterator = it.next();
                        if (innerIterator.hasNext()) {
                            flag = true;
                            break;
                        }
                    }
                } else if (innerIterator.hasNext()) {
                    flag = true;
                }
                return flag;
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
                if (!innerIterator.hasNext() && it.hasNext()) {
                    innerIterator = it.next();
                }
                return innerIterator.next();
            }
        };
    }
}
