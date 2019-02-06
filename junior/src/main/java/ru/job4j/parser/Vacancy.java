package ru.job4j.parser;

import java.util.Date;

/**
 * Вакансия
 *
 * @author maksimspiridonov 
 */
public class Vacancy {
    private int id;
    private String name;
    private String text;
    private String link;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Vacancy(String name, String text, String link, Date date) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
