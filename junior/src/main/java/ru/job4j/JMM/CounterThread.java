package ru.job4j.JMM;

/**
 * class CounterThread - поток, который будет инкременировать счетчик
 *
 * @author maksimspiridonov
 */
public class CounterThread extends Thread {

    /**
     * Счетчик
     */
    private Counter counter;

    /**
     * Конструктор
     *
     * @param counter
     */
    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0; i<1000; i++) {
            counter.increaseCounter();
        }
    }
}
