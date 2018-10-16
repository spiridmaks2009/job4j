package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User3Test {

    @Test
    public void whenCreateTwoUsersThenAddUsersInMap() {
        User3 first = new User3("Ivan", 3, new GregorianCalendar(1977, 5, 15));
        User3 second = new User3("Ivan", 3, new GregorianCalendar(1977, 5, 15));

        Map<User3, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");

        System.out.println(map);
    }
}