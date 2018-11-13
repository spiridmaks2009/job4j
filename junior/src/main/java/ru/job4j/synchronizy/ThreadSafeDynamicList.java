package ru.job4j.synchronizy;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.Task_5_3_1.DynamicArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * class ThreadSafeDynamicList
 *
 * @param <E>
 *
 * @author maksimspiridonov
 */
@ThreadSafe
public class ThreadSafeDynamicList<E> extends Decorator<E> {

    public ThreadSafeDynamicList(DynamicArrayList<E> array) {
        super(array);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.array).iterator();
    }

    private Iterable<E> copy(Iterable<E> array) {
        Collection<E> list = new ArrayList<>();
        for(Object item : this.container) {
            list.add((E) item);
        }
        return list;
    }
}
