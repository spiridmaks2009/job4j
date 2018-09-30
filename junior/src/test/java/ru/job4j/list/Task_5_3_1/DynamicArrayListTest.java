package ru.job4j.list.Task_5_3_1;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class DynamicArrayListTest {

    DynamicArrayList<Integer> container;

    @Before
    public void setUp() throws Exception {
        container = new DynamicArrayList<Integer>(new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    public void whenAddThenGetResultSix() {
        container.add(6);
        assertThat(container.get(5), is(6));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenCollectionChangedResultConcurrentModificationException() {
        Iterator<Integer> it = container.iterator();
        assertThat(it.next(), is(1));
        container.add(7);
        it.next();
    }
}