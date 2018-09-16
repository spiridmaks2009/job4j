package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrayIteratorTest {

    @Test
    public void hasNext() {
        int[][] outerArray = new int[][]{{1,2},{3,4}};
        ArrayIterator iterator = new ArrayIterator(outerArray);
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        boolean result = iterator.hasNext();
        assertThat(result,is(true));
    }

    @Test
    public void next() {
    }
}