package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * MyHashMap class
 *
 * @author maksimspiridonov
 */
public class MyHashMap<K, V>{
    private MyEntry<K, V>[] table;

    /**
     * The default array capacity value.
     */
    final static int INITIAL_CAPACITY = 16;

    /**
     * Array load factor.
     */
    final static double LOAD_FACTOR = 0.75;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    private int threshold;

    /**
     * Number of elements in the array.
     */
    private int size = 0;

    /**
     * The number of changes in the array for the correct operation of the iterator.
     */
    private int modCount = 0;

    public MyHashMap() {
        table = new MyEntry[INITIAL_CAPACITY];
        reTresh();
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }

    /**
     * Calculates the value of the threshold.
     */
    private void reTresh() {
        threshold = (int) ((double) table.length * LOAD_FACTOR);
    }

    /**
     * Inserts element in the array.
     * @param key of the element.
     * @param value of the element.
     * @return true if inserted or false if cell is busy.
     */
    public boolean insert(K key, V value) {
        boolean valid = false;
        int hash = hash(key);
        int i = (table.length - 1) & hash;
        if (table[i] == null) {
            table[i] = new MyEntry<>(hash, key, value);
            valid = true;
            modCount++;
            if (size++ >= threshold) {
                resize();
            }
        }
        return valid;

    }

    /**
     * Method resize the array if the threshold is exceeded.
     */
    private void resize() {
        MyEntry<K, V>[] oldCont = table;
        int oldCap = oldCont.length;
        MyEntry<K, V>[] newCont = new MyEntry[oldCap << 1];
        int newCap = newCont.length;
        int i;
        table = newCont;
        reTresh();
        for (int index = 0; index < oldCap; index++) {
            if (oldCont[index] != null) {
                i = oldCont[index].hash & (newCap - 1);
                newCont[i] = oldCont[index];
            }
        }
        modCount++;
    }

    /**
     * Returns the value of an element by key.
     * @param key of item.
     * @return value of item.
     */
    public V get(K key) {
        int i = hash(key) & (table.length - 1);
        return table[i] == null ? null : table[i].value;
    }

    /**
     * Removes the element by key.
     * @param key of value to be deleted.
     * @return true if exists and is removed else false.
     */
    public boolean delete(K key) {
        boolean valid = false;
        int i = hash(key) & (table.length - 1);
        if (table[i] != null) {
            table[i] = null;
            size--;
            valid = true;
        }
        return valid;
    }

    /**
     * The method calculates the hash key value.
     * @param key to be hashed.
     * @return value of the hash.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    public Iterator<MyEntry<K, V>> iterator() {
        return new MyHashMap.Itr();
    }

    private class Itr implements Iterator<MyEntry<K, V>> {

        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Counter changes in the container.
         */
        int expectedModCount = modCount;

        /**
         * Index + 1 of last returned element from the table.
         */
        int position = 0;

        @Override
        public boolean hasNext() {
            checkForModifications();
            boolean valid = false;
            if (cursor < size) {
                valid = true;
            }
            return valid;
        }

        @Override
        public MyEntry<K, V> next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            MyEntry<K, V> result = null;
            for (int index = position; index < table.length; index++) {
                if (table[index] != null) {
                    result = table[index];
                    cursor++;
                    position = ++index;
                    break;
                }
            }
            return result;
        }

        /**
         * Checks the container for changes after Iterator initialization.
         * @throws ConcurrentModificationException if modified.
         */
        private void checkForModifications() throws ConcurrentModificationException {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * MyEntry for storage elements.
     * @param <K> type of key.
     * @param <V> type of value
     */
    private final class MyEntry<K, V> {
        final int hash;
        final K key;
        V value;

        public MyEntry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MyEntry<?, ?> MyEntry = (MyEntry<?, ?>) o;

            if (!key.equals(MyEntry.key)) {
                return false;
            }
            return value.equals(MyEntry.value);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }
}
