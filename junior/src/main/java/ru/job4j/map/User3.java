package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author maksimspiridonov
 */
public class User3 {
    private String name;
    private int children;
    private Calendar birthday;

    public User3(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User3 user3 = (User3) o;
        return children == user3.children &&
                Objects.equals(name, user3.name) &&
                Objects.equals(birthday, user3.birthday);
    }

}
