package ru.job4j.list.Task_5_3_3;

import ru.job4j.list.Task_5_3_2.DynamicLinkedList;

/**
 * Simplestack класс
 *
 * @param <T>
 *
 * @author maksimspiridonov
 */
public class SimpleStack<T>{

    /**
     * Контейнер для хранения данных
     */
    DynamicLinkedList<T> container;

    /**
     * Конструктор
     */
    public SimpleStack() {
        this.container = new DynamicLinkedList<T>();
    }

    /**
     * Добавить элемент в стек
     *
     * @param value
     */
    public void push (T value) {
        container.add(value);
    }

    /**
     * Взять верхний элемент из стека
     *
     * @return верхний элемент
     */
    public T poll() {
        return container.delete();
    }
}
