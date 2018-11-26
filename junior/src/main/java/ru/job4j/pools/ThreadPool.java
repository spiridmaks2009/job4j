package ru.job4j.pools;

import ru.job4j.wait.notify.notifyall.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * ThreadPool
 *
 * @author maksimspiridonov
 */
public class ThreadPool {

    /**
     * Список нитей
     */
    private final List<Thread> threads = new LinkedList<>();

    /**
     * Блокирующая очередь
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(12);

    public ThreadPool() {

        /**
         * Размер очереди (количество ядер)
         */
        int size = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < size; index++) {
            threads.add(new Worker(tasks, index));
        }
    }

    /**
     * Добавить задачу в очередь
     *
     * @param job
     * @throws InterruptedException
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * Завершить нить
     */
    public void shutdown() {
        System.out.println("Shutdown");
        for (Thread worker : threads) {
            worker.interrupt();
            System.out.println(String.format("%s is stops.", worker.getName()));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Worker - нить
     */
    private class Worker extends Thread {
        private final SimpleBlockingQueue<Runnable> t;

        public Worker(SimpleBlockingQueue<Runnable> tasks, int index) {
            this.t = tasks;
            this.start();
            System.out.println(String.format("Thread %s is started", index));
        }

        @Override
        public void run() {
            Runnable task;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    task = t.poll();
                    task.run();
                } catch (InterruptedException e) {
                    System.out.println(String.format("%s is interrupted.", Thread.currentThread().getName()));
                }
            }
        }
    }
}
