package ru.job4j.collection.map;

import java.util.Calendar;

/**
 * 1. Создать модель User [#999]
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
