package ru.job4j.list.Task_5_3_4;

import ru.job4j.list.Task_5_3_2.DynamicLinkedList;
import ru.job4j.list.Task_5_3_3.SimpleStack;

public class SimpleQueue<T> {

    private SimpleStack<T> mainStack = new SimpleStack<T>();
    private SimpleStack<T> secondStack = new SimpleStack<T>();

    private int size;

    public void push(T value) {
        mainStack.push(value);
        size++;
    }

    public T poll() {
        if (secondStack.getSize() == 0) {
            while (mainStack.getSize() != 0) {
                secondStack.push(mainStack.poll());
            }
        }
        return secondStack.poll();
    }
}
