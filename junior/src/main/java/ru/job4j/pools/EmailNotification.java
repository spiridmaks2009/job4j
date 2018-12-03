package ru.job4j.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EmailNotification
 *
 * @author maksimspiridonov
 */
public class EmailNotification {
    final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * Отправить письмо пользователю
     *
     * @param user
     */
    public void emailTo(User user) {
        final String subject = String.format("Notification %s to email %s", user.getUser(), user.getEmail());
        final String body = String.format("Add a new event to %s", user.getUser());
        send(subject, body, user.getEmail());

        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
            }
        });
    }

    /**
     * Послать
     *
     * @param subject
     * @param body
     * @param email
     */
    public void send(String subject, String body, String email) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(subject);
        System.out.println(body);
        System.out.println(email);
    }

    /**
     * Закрыть пул
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
