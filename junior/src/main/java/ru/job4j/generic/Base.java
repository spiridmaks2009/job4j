package ru.job4j.generic;

/**
 * Base класс
 *
 * @author maksimspiridonov
 */
public abstract class Base {

    /**
     * id элемента
     */
    private final String id;

    /**
     * Конструктор
     *
     * @param id
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Получить id
     *
     * @return id
     */
    public String getId() {
        return id;
    }
}
