package ru.job4j.statistic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreTest {
    List<Store.User> previous;
    List<Store.User> current;
    Store store = new Store();

    @Before
    public void setUp() {
        previous = new ArrayList<>();
        previous.add(new Store.User(0, "0"));
        previous.add(new Store.User(1, "1"));
        previous.add(new Store.User(2, "2"));
        previous.add(new Store.User(3, "3"));
        previous.add(new Store.User(4, "4"));
        previous.add(new Store.User(5, "5"));
        current = new ArrayList<>(previous);
    }

    @Test
    public void whenDeleteThenResult() {
        current.remove(4);
        Info result = store.diff(previous, current);
        Info info = new Info(0, 1, 0);
        assertThat(result, is(info));
    }

    @Test
    public void whenAddThenResult() {
        current.add(new Store.User(6, "6"));
        Info result = store.diff(previous, current);
        Info info = new Info(1, 0, 0);
        assertThat(result, is(info));
    }

    @Test
    public void whenChangeThenResult() {
        current.set(1, new Store.User(1, "1+"));
        Info result = store.diff(previous, current);
        Info info = new Info(0, 0, 1);
        assertThat(result, is(info));
    }
}