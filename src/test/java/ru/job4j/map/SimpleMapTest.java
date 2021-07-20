package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putThreeDifferentPairs() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(2, "BBB");
        assertThat(map.put(5, "CCC"), is(true));
        assertThat(map.size(), is(3));
    }

    @Test
    public void putTwoEquallyKeys() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(2, "BBB");
        assertThat(map.put(2, "CCC"), is(true));
        assertThat(map.size(), is(2));
    }

    @Test
    public void putTwoNullKeys() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(null, "AAA");
        map.put(12, "BBB");
        assertThat(map.put(null, "CCC"), is(true));
        assertThat(map.size(), is(2));
    }

    @Test
    public void getNullKey() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(null, "AAA");
        map.put(12, "BBB");
        map.put(null, "CCC");
        assertThat(map.get(null), is("CCC"));
        assertThat(map.size(), is(2));
    }

    @Test
    public void getNullKeyWhenNotExist() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(12, "BBB");
        map.put(3, "CCC");
        assertNull(map.get(null));
    }

    @Test
    public void getKeyWhenNotExist() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(12, "BBB");
        map.put(3, "CCC");
        assertNull(map.get(5));
    }

    @Test
    public void getKey() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(13, "AAA");
        map.put(13, "BBB");
        map.put(3, "CCC");
        assertThat(map.get(13), is("BBB"));
    }

    @Test
    public void putTenPairs() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(2, "BBB");
        map.put(3, "CCC");
        map.put(4, "DDD");
        map.put(5, "EEE");
        map.put(6, "FFF");
        map.put(7, "GGG");
        map.put(8, "HHH");
        map.put(9, "III");
        map.put(10, "JJJ");
        assertThat(map.get(10), is("JJJ"));
        assertThat(map.size(), is(10));
    }

    @Test
    public void putAndRemove() {
        SimpleMap<Integer, Character> map = new SimpleMap<>();
        map.put(1, 'a');
        map.put(2, 'b');
        assertTrue(map.remove(1));
        assertNull(map.get(1));
        assertThat(map.get(2), is('b'));
        assertThat(map.size(), is(1));
    }

    @Test
    public void putAndRemoveWrong() {
        SimpleMap<Integer, Double> map = new SimpleMap<>();
        map.put(1, 6.8d);
        assertFalse(map.remove(2));
        assertThat(map.get(1), is(6.8d));
        assertThat(map.size(), is(1));
    }

    @Test
    public void getIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "A");
        map.put(2, "B");
        Iterator it = map.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void getIteratorWhenNoElements() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "A");
        Iterator it = map.iterator();
        it.next();
        it.next();
    }

    @Test
    public void getElementWithIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "A");
        Iterator it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next().toString(), is("MapEntry{"
                + "key=" + 1
                + ", value=A"
                + '}'));
    }
}