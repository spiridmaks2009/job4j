package ru.job4j.pools;

public class User {
    private String user;
    private String email;

    public User(String user, String email) {
        this.user = user;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }
}
