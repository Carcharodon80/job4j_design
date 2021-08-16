package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {

    /**
     * переводим изначальный set в hashmap
     * сравниваем элементы конечного set с изначальным hashmap (по id):
     * если элемент не найден - значит он добавлен,
     * если элемент найден, но не идентичен - значит он изменен (удаляется из изначального),
     * если элемент найден и идентичен - удаляется из изначального.
     * Оставшиеся в изначальном элементы = кол-во удаленных элементов
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        HashMap<Integer, User> previousHashMap = new HashMap<>();
        for (User user : previous) {
            previousHashMap.put(user.getId(), user);
        }

        for (User newUser : current) {
            if (!previousHashMap.containsKey(newUser.getId())) {
                added++;
            } else {
                User oldUser = previousHashMap.get(newUser.getId());
                if (!oldUser.equals(newUser)) {
                    changed++;
                }
                previousHashMap.remove(newUser.getId());
            }
        }
        deleted = previousHashMap.size();

        return new Info(added, changed, deleted);
    }
}
