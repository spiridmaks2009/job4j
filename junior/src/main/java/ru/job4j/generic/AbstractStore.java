package ru.job4j.generic;

import java.util.Iterator;

/**
 * AbstractStore class
 *
 * @author maksimspiridonov
 */
public abstract class AbstractStore implements Store {

    /**
     * Массив данных
     */
    private SimpleArray<Base> array;

    /**
     * Конструктор
     *
     * @param array - массив данных
     */
    public AbstractStore(SimpleArray<Base> array) {
        this.array = array;
    }

    /**
     * Добавить данные в массив
     *
     * @param model - данные
     */
    @Override
    public void add(Base model) {
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
    public boolean replace(String id, Base model) {
        Iterator<Base> it = array.iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId() == id) {
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
        Iterator<Base> it = array.iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId() == id) {
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
    public Base findById(String id) {
        Iterator<Base> it = array.iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                return array.get(index);
            }
            index++;
        }
        return null;
    }
}
