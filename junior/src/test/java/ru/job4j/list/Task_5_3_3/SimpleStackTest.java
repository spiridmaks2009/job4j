package ru.job4j.list.Task_5_3_3;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.Task_5_3_2.DynamicLinkedList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    SimpleStack<Integer> stack;

    @Before
    public void setUp() throws Exception {
        stack = new SimpleStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenPollThreeTimes() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));

    }
}