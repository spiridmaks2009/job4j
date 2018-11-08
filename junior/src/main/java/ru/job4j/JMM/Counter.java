package ru.job4j.JMM;

/**
 * class Counter - счетчик
 *
 * @author maksimspiridonov
 */
public class Counter {
    /**
     * Поле, которое будет увеличиваться в потоках
     */
    private long counter = 0L;

    /**
     * Увеличить счетчик
     */
    public void increaseCounter() {
        counter++;
    }

    /**
     * Получить счетчик
     *
     * @return
     */
    public long getCounter() {
        return counter;
    }
}
