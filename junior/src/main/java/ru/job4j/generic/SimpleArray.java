package ru.job4j.generic;

import java.util.Iterator;

/**
 * SimpleArray класс
 *
 * @author maksimspiridonov
 */
public class SimpleArray<T> implements Iterable {
    private Object[] objects;
    private int index, size;

    /**
     * Конструктор
     *
     * @param size - размер
     */
    public SimpleArray(int size) {
        this.size = size;
        this.objects = new Object[size];
    }

    /**
     * Добавить элемент
     *
     * @param model - данные
     */
    public void add(T model) {
        if (index < size) {
            this.objects[index++] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Изменить элемент по индексу
     *
     * @param index - индекс
     * @param model - данные
     */
    public void set(int index, T model) {
        if (index < size) {
            this.objects[index] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Удалить элемент по индексу
     *
     * @param index - индекс
     */
    public void delete(int index) {
        System.arraycopy(objects, index+1, objects, index, size - index - 1);
    }

    /**
     * Получить элемент имассива по индексу
     *
     * @param position - индекс
     *
     * @return Элемент по индексу
     */
    public <T> T get(int position) {

        return (T) this.objects[position];
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
         Iterator<T> it = new Iterator<T>() {

             private int currentIndex = 0;

             @Override
             public boolean hasNext() {
                 return currentIndex < size && objects[currentIndex] != null;
             }

             @Override
             public T next() {
                 return (T) objects[currentIndex++];
             }
         };
         return it;
    }
}
