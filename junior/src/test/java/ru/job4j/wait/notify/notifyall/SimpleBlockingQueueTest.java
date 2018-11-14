package ru.job4j.wait.notify.notifyall;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAndConsumerRun() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);

        Thread producer = new Thread() {
            @Override
            public void run() {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
                queue.offer(4);
                queue.offer(5);
            }
        };

        Thread consumer = new Thread() {
            @Override
            public void run() {
                queue.poll();
                queue.poll();
                queue.poll();
            }
        };

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}