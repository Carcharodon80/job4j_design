package ru.job4j.collection.map;

import java.util.*;

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

    public static void main(String[] args) {
        User first = new User("aaa", 1, new GregorianCalendar());
        User second = new User("aaa", 1, new GregorianCalendar());
        System.out.println(first);
        System.out.println(second);
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        for (Map.Entry entry : map.entrySet()) {
            System.out.println((entry.getKey().hashCode() & 15) + " : " + entry.getKey().toString() + " - "
                    + entry.getValue().toString());
        }
        System.out.println(map.toString());
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("aaaaaaaaaaaaaaa");
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
