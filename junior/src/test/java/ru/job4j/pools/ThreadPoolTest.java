package ru.job4j.pools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    /**
     * Job - нить
     *
     * @author maksimspiridonov
     */
    public class Job implements Runnable {
        int number = 0;

        public Job(int num) {
            this.number = num;
        }

        @Override
        public void run() {
            System.out.println(String.format("Thread %s doing JOB #%s", Thread.currentThread().getName(), number));
        }

        @Override
        public String toString() {
            return String.format("JOB #%s", number);
        }
    }

    @Test
    public void whenAddTenJobsThenWork() throws InterruptedException {
        ThreadPool pool = new ThreadPool();

        for (int index = 0; index < 1000; index++) {
            pool.work(new Job(index));
        }
        pool.shutdown();

    }

}