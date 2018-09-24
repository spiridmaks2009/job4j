package ru.job4j.generic;

public class RoleStore implements Store {
    private SimpleArray<Role> roleArray;

    public RoleStore(SimpleArray<Role> roleArray) {
        this.roleArray = roleArray;
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
