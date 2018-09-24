package ru.job4j.generic;

public class UserStore implements Store {
    private SimpleArray<User> userArray;

    public UserStore(SimpleArray<User> userArray) {
        this.userArray = userArray;
    }

    @Override
    public void add(Base model) {

    }

    @Override
    public boolean replace(String id, Base model) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Base findById(String id) {
        return null;
    }
}
