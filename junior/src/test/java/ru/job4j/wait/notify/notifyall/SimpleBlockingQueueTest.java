package ru.job4j.wait.notify.notifyall;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.util.Arrays.asList;
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
                            Thread.sleep(1);
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

        Assert.assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}