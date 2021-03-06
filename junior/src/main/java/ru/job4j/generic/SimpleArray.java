package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArray класс
 *
 * @author maksimspiridonov
 */
public class SimpleArray<T> implements Iterable {

    private Object[] objects;
    private int index;
    private int size;

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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.objects[index] = model;
    }

    /**
     * Удалить элемент по индексу
     *
     * @param index - индекс
     */
    public void delete(int index) {
        System.arraycopy(objects, index+1, objects, index, size - index - 1);
        SimpleArray.this.index--;
    }

    /**
     * Получить элемент имассива по индексу
     *
     * @param position - индекс
     *
     * @return Элемент по индексу
     */
    public T get(int position) {

        return (T) this.objects[position];
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
         Iterator<T> it = new Iterator<T>() {

             private int currentIndex = 0;

             @Override
             public boolean hasNext() {
                 return currentIndex < index;
             }

             @Override
             public T next() {
                 if (!hasNext()) {
                     throw new NoSuchElementException();
                 }
                 return (T) objects[currentIndex++];
             }
         };
         return it;
    }
}
