package ru.job4j.map;

import java.util.Iterator;

/**
 * MyHashMap class
 *
 * @author maksimspiridonov
 */
public class MyHashMap<K, V> implements Iterable{
    private Object[] keys = new Object[10];
    private Object[] values = new Object[10];

    /**
     * Добавить элемент в HashMap
     *
     * @param key - ключ
     * @param value - значение
     */
    public boolean insert(K key, V value) {
        try {
            int arrayIndex = key.hashCode();
            if (needNewSize(arrayIndex, this.keys) || needNewSize(arrayIndex, this.values)) {
                AddSizeForKeys(arrayIndex);
                AddSizeForValues(arrayIndex);
            }
            if (isKeyExist(key)) {
                keys[arrayIndex] = key;
                values[arrayIndex] = value;
            } else return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверка, существует ли ключ
     *
     * @param key
     */
    private boolean isKeyExist(K key) {
        int valueIndex = key.hashCode();
        return (keys[valueIndex] != null && keys[valueIndex].equals(key)) ? false : true;
    }

    /**
     * Check size.
     * @return result of ckeck.
     */
    private boolean needNewSize(int arrayIndex, Object[] objects) {
        return arrayIndex > objects.length - 1;
    }

    /**
     * Увеличить размер контейнера keys.
     */
    private void AddSizeForKeys(int newSize) {
        Object[] newObject = new Object[newSize + 2];
        System.arraycopy(this.keys, 0, newObject, 0, this.keys.length);
        this.keys = newObject;
    }

    /**
     * Увеличить размер контейнера values.
     */
    private void AddSizeForValues(int newSize) {
        Object[] newObject = new Object[newSize + 2];
        System.arraycopy(this.values, 0, newObject, 0, this.values.length);
        this.values = newObject;
    }

    /**
     * Получить значение по ключу
     *
     * @param key
     */
    public V get(K key) {
        return (V) values[key.hashCode()];
    }

    /**
     * Удалить по ключу
     *
     * @param key
     */
    public boolean delete(K key) {
        try {
            keys[key.hashCode()] = null;
            values[key.hashCode()] = null;
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                while (values[currentIndex] == null && currentIndex < values.length - 1) {
                    currentIndex++;
                }
                return values[currentIndex++] != null ? true : false;
            }

            @Override
            public V next() {
                while (values[currentIndex] == null && currentIndex < values.length - 1) {
                    currentIndex++;
                }
                return (V) values[currentIndex++];
            }
        };
    }
}
