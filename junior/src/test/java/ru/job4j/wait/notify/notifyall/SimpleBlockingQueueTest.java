package ru.job4j.wait.notify.notifyall;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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
                try {
                    queue.poll();
                    queue.poll();
                    queue.poll();
                }
                catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        };

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty()  || !Thread.currentThread().isInterrupted()) {
                        try {
//                            Thread.sleep(1);
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}