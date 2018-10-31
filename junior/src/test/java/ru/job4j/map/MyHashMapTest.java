package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MyHashMapTest {
    MyHashMap<String, String> map;

    @Before
    public void setUp() {
        map = new MyHashMap<>();
    }

   @Test
    public void whenAddNonEqualsThenTrue() {
        String first = "Maksim";
        String second = "Ivan";
        assertThat(map.insert(first, "first"), is(true));
        assertThat(map.insert(second, "second"), is(true));
    }

    @Test
    public void whenGetByKeyAndItExistedThenGetIt() {
        String first = "Maksim";
        String second = "Ivan";
        map.insert(first, "first");
        map.insert(second, "second");
        assertThat(map.get(second), is("second"));
        map.get("Ivan");
    }

    @Test
    public void whenGetNotExistedThanNull() {
        assertNull(map.get("Vlad"));
    }

    @Test
    public void resizeTest() {
        MyHashMap<String, String> map = new MyHashMap<>();
        assertThat(map.capacity(), is(16));
        for (int index = 1; index < 20; index++) {
            map.insert("key" + index + "that", "inserted" + index);
        }
        assertThat(map.capacity(), is(32));
    }

    @Test
    public void whenDeleteExistedThanTrue() {
        map = new MyHashMap<>();
        String first = "Maksim";
        String second = "Ivan";
        map.insert(first, "first");
        map.insert(second, "second");
        assertThat(map.delete(second), is(true));
    }

    @Test
    public void whenDeleteNotExistedThenFalse() {
        assertFalse(map.delete("Jack"));
    }

    @Test
    public void whenIterateThanTrue() {
        String first = "Maksim";
        String second = "Igor";
        map.insert(first, "first");
        map.insert(second, "second");
        Iterator itr = map.iterator();
        assertThat(itr.hasNext(), is(true));
        System.out.println(itr.next());
        assertThat(itr.hasNext(), is(true));
        System.out.println(itr.next());
        assertThat(itr.hasNext(), is(false));
    }

}