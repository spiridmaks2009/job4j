package ru.job4j.list.Task_5_3_0;

public class SimpleArrayList<E> {

    private int size = 0;
    private Node<E> first;

    public void add(E date) {
        Node<E> newLink = new Node<E>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    public E delete() {
        if(size > 0) {
            E date = first.date;
            first = first.next;
            size--;
            return date;
        }
        return null;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    public int getSize() {
        return this.size;
    }

    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
