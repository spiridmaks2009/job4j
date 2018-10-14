package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenCreateTwoUsersThenAddUsersInMap() {
        User first = new User("Ivan", 3, new GregorianCalendar(1977, 5, 15));
        User second = new User("Ivan", 3, new GregorianCalendar(1977, 5, 15));

        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");

        System.out.println(map);
    }
}