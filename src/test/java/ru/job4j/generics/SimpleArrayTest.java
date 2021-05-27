package ru.job4j.generics;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleArrayTest {

    @Test
    public void whenAdd2Elements() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(56);
        simpleArray.add(42);
        assertThat(simpleArray.get(0) + simpleArray.get(1), is(98));
        assertThat(simpleArray.getSize(), is(2));
    }

    @Test
    public void whenAddAndSet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(56);
        simpleArray.add(42);
        simpleArray.set(0, 10);
        simpleArray.set(1, 20);
        assertThat(simpleArray.get(0) + simpleArray.get(1), is(30));
        assertThat(simpleArray.getSize(), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetInvalidIndex() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(56);
        simpleArray.add(42);
        simpleArray.get(3);
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.remove(1);
        assertThat(simpleArray.get(1), is(3));
        assertThat(simpleArray.get(3), is(5));
        assertThat(simpleArray.getSize(), is(4));
    }

    @Test
    public void whenIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        int sum = 0;
        for (Integer integer : simpleArray) {
            sum += integer;
        }
        assertThat(sum, is(15));
    }





}