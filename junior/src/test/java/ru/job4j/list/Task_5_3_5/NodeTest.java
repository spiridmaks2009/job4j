package ru.job4j.list.Task_5_3_5;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NodeTest {

   @Test
    public void whenHasBoolean() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        assertThat(Node.hasCycle(first), is(true));

       first.next = two;
       two.next = third;
       third.next = four;
       four.next = two;

       assertThat(Node.hasCycle(first), is(true));
    }
}