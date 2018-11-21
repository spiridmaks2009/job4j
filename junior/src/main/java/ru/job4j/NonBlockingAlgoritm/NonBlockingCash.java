package ru.job4j.NonBlockingAlgoritm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * NonBlockingCash
 *
 * @author maksimspiridonov
 */
public class NonBlockingCash {
    ConcurrentHashMap<Integer, Base> map;

    private volatile ConcurrentHashMap<Integer, Base> hashMap = new ConcurrentHashMap<>();

    /**
     * Получить данные по id
     *
     * @param id
     * @return
     */
    public Base get(int id) {
        Base result = hashMap.get(id);
        System.out.println(String.format("Thread %s get %s", Thread.currentThread().getId(), result));
        return result;
    }

    /**
     * Добавить данные в кэш
     *
     * @param model
     */
    public void add(Base model) {
        hashMap.put(model.getId(), model);
        System.out.println(String.format("Thread %s add %s", Thread.currentThread().getId(), model));
    }

    /**
     * Обновить данные
     *
     * @param model
     * @throws OptimisticException
     */
    public void update(final Base model) throws OptimisticException {
        hashMap.computeIfPresent(model.getId(), new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer id, Base base) {
                System.out.println(String.format("Model version: %s, Base version %s", model.getVersion(), base.getVersion()));
                if (base.getVersion() != model.getVersion()) {
                    throw new OptimisticException();
                }
                System.out.println(String.format("Thread %s modify %s", Thread.currentThread().getId(), model));
                model.versionUpdate();
                return model;
            }
        });
    }

    /**
     * Удалить данные из кэша
     *
     * @param model
     */
    public void delete(Base model) {
        hashMap.remove(model.getId());
        System.out.println(String.format("Thread %s removed %s", Thread.currentThread().getId(), model));
    }

}
