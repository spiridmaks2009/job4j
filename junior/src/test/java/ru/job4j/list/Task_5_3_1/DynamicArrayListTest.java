package ru.job4j.list.Task_5_3_1;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class DynamicArrayListTest {

    private DynamicArrayList<Integer> container;

    @Before
    public void setUp() throws Exception {
        container = new DynamicArrayList<Integer>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
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