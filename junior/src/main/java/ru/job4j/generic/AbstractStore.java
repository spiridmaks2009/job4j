package ru.job4j.generic;

import java.util.Iterator;

public abstract class AbstractStore implements Store {

    private SimpleArray<Base> array;

    public AbstractStore(SimpleArray<Base> array) {
        this.array = array;
    }

    @Override
    public void add(Base model) {
        array.add(model);
    }

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
