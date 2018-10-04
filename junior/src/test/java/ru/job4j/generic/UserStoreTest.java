package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    UserStore store;

    @Before
    public void setUp() {
        store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");

        store.add(user1);
        store.add(user2);
        store.add(user3);
    }

    @Test
    public void whenUpdate() {
        User user2 = new User("22");
        store.replace("2", user2);

        assertThat(user2.getId(), is("22"));
    }

    @Test
    public void whenDelete() {
        store.delete("2");
        assertNull(store.findById("2"));
    }

}