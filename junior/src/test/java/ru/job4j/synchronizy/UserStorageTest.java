package ru.job4j.synchronizy;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAdd2ThenTransfer() {
        UserStorage stoge = new UserStorage();

        stoge.add(new User(1, 100));
        stoge.add(new User(2, 200));

        stoge.transfer(1, 2, 50);

    }

}