package ru.job4j.list.Task_5_3_4;

import ru.job4j.list.Task_5_3_2.DynamicLinkedList;
import ru.job4j.list.Task_5_3_3.SimpleStack;

/**
 * SimpleQueue - Класс очереди на двух стеках
 *
 * @author maksimspiridonov
 *
 * @param <T>
 */
public class SimpleQueue<T> {

    /**
     * Первый стек
     */
    private SimpleStack<T> mainStack = new SimpleStack<T>();

    /**
     * Второй стек
     */
    private SimpleStack<T> secondStack = new SimpleStack<T>();

    /**
     * Добавить в очередь
     *
     * @param value - значение
     */
    public void push(T value) {
        mainStack.push(value);
    }

    /**
     * Взять первый элемент в очереди
     *
     * @return значение
     */
    public T poll() {
        if (secondStack.getSize() == 0) {
            while (mainStack.getSize() != 0) {
                secondStack.push(mainStack.poll());
            }
        }
        return secondStack.poll();
    }
}
