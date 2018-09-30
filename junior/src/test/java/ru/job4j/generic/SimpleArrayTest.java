package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private SimpleArray<String> array;

    @Before
    public void setUp() throws Exception {
        array = new SimpleArray<String>(5);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.add("Four");
    }

    @Test
    public void addTest() {
        assertThat(array.get(0), is("One"));
        assertThat(array.get(1), is("Two"));
        assertThat(array.get(2), is("Three"));
        assertThat(array.get(3), is("Four"));
    }

    @Test
    public void setTest() {
        array.set(1, "Two+");
        array.set(2, "Three+");
        assertThat(array.get(1), is("Two+"));
        assertThat(array.get(2), is("Three+"));
    }

    @Test
    public void deleteTest() {
        array.delete(2);
        assertThat(array.get(2), is("Four"));
    }

}