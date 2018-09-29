package ru.job4j.generic;

public class UserStore extends AbstractStore {
    private SimpleArray<User> userArray;

    public UserStore(SimpleArray<Base> array) {
        super(array);
    }
}
