package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 *  1. Реализовать коллекцию Set на массиве [#996]
 */
public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddAndIterator() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(10));
        Iterator<Integer> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(10));
    }
}