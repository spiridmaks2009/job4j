package ru.job4j.generic;

import java.util.Iterator;

/**
 * AbstractStore class
 *
 * @author maksimspiridonov
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {

    /**
     * Массив данных
     */
    private SimpleArray<E> array;

    /**
     * Добавить данные в массив
     *
     * @param model - данные
     */
    @Override
    public void add(E model) {
        array.add(model);
    }

    /**
     * Изменить элемент
     *
     * @param id - id
     * @param model - данные
     * @return если изменил, то true
     */
    @Override
    public boolean replace(String id, E model) {
        Iterator<Base> it = array.iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                array.set(index, model);
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Удалить элемент
     *
     * @param id - id
     * @return если удалил, то true
     */
    @Override
    public boolean delete(String id) {
        Iterator<E> it = array.iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                array.delete(index);
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Поиск по id
     *
     * @param id - id
     * @return найденный элемент Base
     */
    @Override
    public E findById(String id) {
        Iterator<E> it = array.iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                return array.get(index);
            }
            index++;
        }
        return null;
    }
}
