package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User2Test {

    @Test
    public void whenCreateTwoUsersThenAddUsersInMap() {
        User2 first = new User2("Ivan", 3, new GregorianCalendar(1977, 5, 15));
        User2 second = new User2("Ivan", 3, new GregorianCalendar(1977, 5, 15));

        Map<User2, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");

        System.out.println(map);
    }
}