package ru.job4j.list.Task_5_3_2;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DynamicLinkedListTest {

    DynamicLinkedList<Integer> container;

    @Before
    public void setUp() throws Exception {
        container = new DynamicLinkedList<Integer>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
    }

    @Test
    public void whenAddThenGetResultSix() {
        container.add(6);
        assertThat(container.get(4), is(2));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenCollectionChangedResultConcurrentModificationException() {
        Iterator<Integer> it = container.iterator();
        assertThat(it.next(), is(4));
        container.add(6);
        it.next();
    }

}