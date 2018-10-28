package ru.job4j.map;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author anton.
 */
public class MyHashMapTest {
    private MyHashMap<Integer, String> MyHashMap = new MyHashMap<>();
    private Iterator<String> iterator = MyHashMap.iterator();

    @Test
    public void whenAddNewElementAndGetHim() {
        MyHashMap.insert(0, "Ivan");
        String result = MyHashMap.get(0);
        assertThat(result, is("Ivan"));
    }

    @Test
    public void whenAddNewElementAndGetHimUseIterator() {
        MyHashMap.insert(0, "Ivan");
        String result = iterator.next();
        assertThat(result, is("Ivan"));
    }

    @Test
    public void whenDeleteElement() {
        MyHashMap.insert(0, "Ivan");
        Boolean result = MyHashMap.delete(0);
        assertThat(result, is(true));
    }

    @Test
    public void whenAddTwoSameElementAndCheckSecondElementUseIterator() {
        MyHashMap.insert(0, "Ivan");
        MyHashMap.insert(0, "Ivan");
        iterator.next();
        Boolean result = iterator.hasNext();
        assertThat(result, is(false));
    }
}