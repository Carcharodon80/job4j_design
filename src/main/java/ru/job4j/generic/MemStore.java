package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.2.2. Реализовать Store<T extends Base> [#157]
 * @param <T>
 */
public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        if (mem.containsKey(id)) {
            rsl = true;
            mem.replace(id, model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        if (mem.containsKey(id)) {
            rsl = true;
            mem.remove(id);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
