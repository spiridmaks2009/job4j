package ru.job4j.list.Task_5_3_0;

/**
 * SimpleArrayList класс
 *
 * @param <E>
 *
 * @author maksimspiridonov
 */
public class SimpleArrayList<E> {

    /**
     * Размер списка
     */
    private int size;

    /**
     * Первый элемент списка
     */
    private Node<E> first;

    /**
     * Добавить элемент
     *
     * @param date - данные
     */
    public void add(E date) {
        Node<E> newLink = new Node<E>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Удалить первый элемент
     *
     * @return date
     */
    public E delete() {
        if(size > 0) {
            E date = first.date;
            first = first.next;
            size--;
            return date;
        }
        return null;
    }

    /**
     * Получить значение элемента
     *
     * @param index - порядковый номер элемента
     *
     * @return date - данные
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Получить размер
     *
     * @return size - размер элемента
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Node - Элемент списка
     *
     * @param <E>
     */
    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
