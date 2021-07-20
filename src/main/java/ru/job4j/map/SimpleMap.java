package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int countMod = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * если кладем пару с ключем null - вызываем PutIfNullKey()
     * иначе вычиляем бакет (index), если он пустой - кладем туда пару, count++
     * если бакет занят, и ключи равны - перезатираем значение
     * если бакет занят, и ключи не равны - возвращаем false
     * в конце проверяем, не надо ли расширять массив
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (key == null) {
            rsl = putIfNullKey(value);
        } else {
            int index = indexFor(key.hashCode());
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                count++;
                countMod++;
                rsl = true;
            } else {
                MapEntry<K, V> existedMapEntry = table[index];
                if (key.equals(existedMapEntry.key)) {
                    table[index] = new MapEntry<>(key, value);
                    countMod++;
                    rsl = true;
                }
            }
        }
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        return rsl;
    }

    /**
     * если бакет 0 пустой - увеличивает count на 1, затем кладет пару в бакет 0, true
     * если бакет 0 уже содержит пару с ключом null - перезатирает значение, true
     * если бакет 0 содержит пару с другим ключом - возвращает false
     */
    private boolean putIfNullKey(V value) {
        boolean rsl = false;
        if (table[0] == null) {
            table[0] = new MapEntry<>(null, value);
            count++;
            countMod++;
            rsl = true;
        } else {
            if (table[0].key == null) {
                table[0] = new MapEntry<>(null, value);
                countMod++;
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * вычисляет индекс (номер бакета)
     */
    private int indexFor(int hashCode) {
        int hash = hashCode ^ (hashCode >>> 16);
        return hash % capacity;
    }

    /**
     * расширяет массив table в два раза, кладет значения из старого массива в новый,
     * при это рехешируя их (в методе put)
     */
    private void expand() {
        capacity = capacity * 2;
        count = 0;
        MapEntry<K, V>[] oldTable = this.table;
        this.table = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : oldTable) {
            this.put(mapEntry.key, mapEntry.value);
        }
    }

    /**
     * если key == null - вызываем getIfNullKey(),
     * иначе вычисляем номер бакета (index) и сравниваем ключи, если равны - возвращаем значение
     * если бакет пустой или ключи не равны - возвращаем null
     */
    @Override
    public V get(K key) {
        V rsl = null;
        if (key == null) {
            rsl = getIfKeyNull();
        } else {
            int index = indexFor(key.hashCode());
            if (table[index] != null && table[index].key.equals(key)) {
                rsl = table[index].value;
            }
        }

        return rsl;
    }

    /**
     * если в бакете 0 лежит пара с ключом null - возвращаем значение,
     * если бакет 0 пустой, или содержит пару с другим ключом - возвращаем null
     */
    private V getIfKeyNull() {
        V rsl = null;
        if (table[0] != null && table[0].key == null) {
            rsl = table[0].value;
        }
        return rsl;
    }

    /**
     * возвращает итератор
     */
    @Override
    public Iterator iterator() {
        return new Iterator<>() {
            private int index = 0;
            private int iteratorPoint = 0;
            private final int expectedCountMod = countMod;

            @Override
            public boolean hasNext() {
                return iteratorPoint < count;
            }

            @Override
            public MapEntry<K, V> next() {
                while (index < table.length && table[index] == null) {
                    index++;
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedCountMod != countMod) {
                    throw new ConcurrentModificationException();
                }
                iteratorPoint++;
                return table[index++];
            }
        };
    }

    /**
     * вычисляет индекс переданного ключа
     * и обнуляет ячейку массива с таким индексом, если ячейка не пуста и ключи идентичны
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            countMod++;
            rsl = true;
        }
        return rsl;
    }

    public int size() {
        return count;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }
}
