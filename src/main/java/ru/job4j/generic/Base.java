package ru.job4j.generic;

/**
 * 5.2.2. Реализовать Store<T extends Base> [#157]
 */
public abstract class Base {
    private final String id;

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
