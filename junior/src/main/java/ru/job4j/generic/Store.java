package ru.job4j.generic;

/**
 * интерфейс Store
 *
 * @author maksimspiridonov
 */
public interface Store<T extends Base> {

    /**
     * Добавить элемент
     *
     * @param model - данные
     */
    void add(T model);

    /**
     * Изменить элемент
     *
     * @param id - id элемента
     * @param model - данные
     * @return true если изменение выполнено
     */
    boolean replace(String id, T model);

    /**
     * Удалить элемент
     *
     * @param id - id элемента
     * @return true если удаление успешно
     */
    boolean delete(String id);

    /**
     * Поиск по id
     *
     * @param id - id элемента
     * @return элемент по id
     */
    T findById(String id);
}
