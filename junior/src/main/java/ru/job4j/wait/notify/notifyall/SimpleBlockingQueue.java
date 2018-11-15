package ru.job4j.wait.notify.notifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @param <T>
 * @author maksimspiridonov
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    /**
     * Размер очереди
     */
    private int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    /**
     * Добавить в очередь
     *
     * @param value
     */
    public void offer(T value) {
        synchronized (queue) {
            while (queue.size() >= this.size) {
                try {
                    System.out.println("Producer wait");
                    queue.wait();

                } catch (InterruptedException e) {

                }
            }
            queue.add(value);
            System.out.println(String.format("Producer put %s", value));
            queue.notify();
        }
    }

    /**
     * Взять из очереди
     *
     * @return
     */
    public T poll() {
        synchronized (queue) {
            while (queue.size() < 1) {
                try {
                    System.out.println("Consumer wait");
                    queue.wait();
                } catch (InterruptedException e) {

                }
            }
            T value = queue.poll();
            System.out.println(String.format("Consumer get %s", value));
            queue.notify();
            return value;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
