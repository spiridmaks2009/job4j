package ru.job4j.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable{
    private Object[] objects;
    private int index = 0, size = 0;

    public SimpleArray(int size) {
        this.size = size;
        this.objects = new Object[size];
    }

    public void add(T model){
        if(index<size)
            this.objects[index++] = model;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, T model) {
        if(index<size)
            this.objects[index] = model;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public void delete(int index) {
        if(index < size)
            this.objects[index] = null;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

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
                 return (T)objects[currentIndex++];
             }
         };
         return it;
    }
}
