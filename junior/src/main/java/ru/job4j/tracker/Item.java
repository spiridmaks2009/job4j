package ru.job4j.tracker;

import java.sql.Timestamp;

public class Item {
    private String id;
    private String name;
    private String desc;
    private Timestamp created;
    private String[] comments;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String[] getComments() {
        return this.comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public  Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Item(String id, String name, String desc, Timestamp created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }
}
