package ru.job4j.list.Task_5_3_4;

import ru.job4j.list.Task_5_3_2.DynamicLinkedList;
import ru.job4j.list.Task_5_3_3.SimpleStack;

public class SimpleQueue<T> {

    SimpleStack<T> stack;

    SimpleStack<T> endStack;


    public SimpleQueue() {
        this.stack = new SimpleStack<T>();
        this.endStack = new SimpleStack<T>();
    }

    public void push(T value) {
        stack.push(value);
    }
}
