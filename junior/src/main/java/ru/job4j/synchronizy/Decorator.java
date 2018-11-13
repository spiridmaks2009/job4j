package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.Task_5_3_1.DynamicArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * class Decorator
 *
 * @param <E>
 *
 * @author maksimspiridonov
 */
@ThreadSafe
abstract class Decorator<E> extends DynamicArrayList<E> {

    @GuardedBy("this")
    protected DynamicArrayList<E> array;

    public Decorator(DynamicArrayList<E> array) {
        this.array = array;
    }

}

