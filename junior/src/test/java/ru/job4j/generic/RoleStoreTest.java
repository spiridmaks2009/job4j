package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    RoleStore<Role> store;

    @Before
    public void setUp() {
        store = new RoleStore<Role>();
        Role role1 = new Role("1");
        Role role2 = new Role("2");
        Role role3 = new Role("3");

        store.add(role1);
        store.add(role2);
        store.add(role3);
    }

    @Test
    public void whenUpdate() {
        Role role2 = new Role("22");
        store.replace("2", role2);

        assertThat(role2.getId(), is("22"));
    }

    @Test
    public void whenDelete() {
        store.delete("2");
        assertNull(store.findById("2"));
    }

}