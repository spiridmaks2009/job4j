package ru.job4j.generic;

/**
 * UserStore класс
 *
 * @author maksimspiridonov
 */
public class UserStore extends AbstractStore {

    /**
     * массив данных
     */
    private SimpleArray<User> userArray;

    /**
     * Конструктор
     *
     * @param array - массив данных
     */
    public UserStore(SimpleArray<Base> array) {
        super(array);
    }
}
