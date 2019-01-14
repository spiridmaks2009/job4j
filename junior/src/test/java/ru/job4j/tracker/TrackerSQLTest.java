package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
/*    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
        sql.add(new Item("2","task2","task 2 description", new Timestamp(System.currentTimeMillis())));
        sql.add(new Item("3","task3","task 3 description", new Timestamp(System.currentTimeMillis())));
        sql.add(new Item("4","task4","task 4 description", new Timestamp(System.currentTimeMillis())));

        List<Item> items = sql.findAll();
        for(Item item : items) {
            System.out.println(item.getId() + " " + item.getName() + "  " + item.getDesc() + "  " + item.getCreated().toString());
        }
        sql.delete("3");
        sql.replace("5", new Item("task5+","task 5 description"));
        for(Item item : items) {
            System.out.println(item.getId() + " " + item.getName() + "  " + item.getDesc() + "  " + item.getCreated().toString());
        }
    }*/

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }
}
