package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User4Test {

    @Test
    public void whenCreateTwoUsersThenAddUsersInMap() {
        User4 first = new User4("Ivan", 3, new GregorianCalendar(1977, 5, 15));
        User4 second = new User4("Ivan", 3, new GregorianCalendar(1977, 5, 15));

        Map<User4, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");

        System.out.println(map);
    }
}