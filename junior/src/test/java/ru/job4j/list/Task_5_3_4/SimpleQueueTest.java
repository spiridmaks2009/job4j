package ru.job4j.list.Task_5_3_4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenPushThenPoll() {
        SimpleQueue<String> queue = new SimpleQueue<String>();

        queue.push("1");
        assertThat(queue.poll(), is("1"));
        queue.push("2");
        assertThat(queue.poll(), is("2"));
        queue.push("3");
        queue.push("4");
        queue.push("5");
        assertThat(queue.poll(), is("3"));
        assertThat(queue.poll(), is("4"));
        assertThat(queue.poll(), is("5"));
    }
}