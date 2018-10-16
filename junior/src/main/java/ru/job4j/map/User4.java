package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author maksimspiridonov
 */
public class User4 {
    private String name;
    private int children;
    private Calendar birthday;

    public User4(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User4 user4 = (User4) o;
        return children == user4.children &&
                Objects.equals(name, user4.name) &&
                Objects.equals(birthday, user4.birthday);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 37 * result + children;
        result = 37 * result + + birthday.hashCode();
        return result;
    }
}
