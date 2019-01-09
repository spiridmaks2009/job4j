package ru.job4j.xml;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void createNewDatabase() {
        StoreSQL store = new StoreSQL();
        store.createNewDatabase("store.db");
        store.createNewTable();
        store.generate(100);
        store.selectAll();
        System.out.println();
    }
}