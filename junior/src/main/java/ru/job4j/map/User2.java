package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author maksimspiridonov
 */
public class User2 {
    private String name;
    private int children;
    private Calendar birthday;

    public User2(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 37 * result + children;
        result = 37 * result + + birthday.hashCode();
        return result;
    }
}
