package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    SimpleSet<String> set;

    @Before
    public void setUp() throws Exception {
        set = new SimpleSet<String>(new String[10]);
        set.add("one");
        set.add("two");
        set.add("three");
    }

    @Test
    public void whenIteratorNextResultTwo() {
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("one"));
        assertThat(it.next(), is("two"));
    }

    @Test
    public void whenAddExistElementThenHasNextResultFalse() {
        set.add("two");
        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is("three"));
        assertThat(it.hasNext(), is(false));
    }
}