package ru.job4j.tracker;

import java.util.List;

public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    List<Item> findById(String id);
}
