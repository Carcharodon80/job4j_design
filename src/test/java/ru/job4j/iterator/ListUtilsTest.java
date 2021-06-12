package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.addAfter(input, 1, 17);
        assertThat(Arrays.asList(0, 1, 17, 2, 3, 4), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.addAfter(input, 9, 4);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> predicate = i -> i > 3;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfWithWrongFilter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> predicate = i -> i > 10;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> predicate = i -> i % 2 == 0;
        ListUtils.replaceIf(input, predicate, 44);
        assertThat(Arrays.asList(1, 44, 3, 44, 5, 44, 7), Is.is(input));
    }

    @Test
    public void whenReplaceIfWithWrongFilter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> predicate = i -> i < 0;
        ListUtils.replaceIf(input, predicate, 44);
        assertThat(Arrays.asList(1, 2, 3, 4, 5, 6, 7), Is.is(input));
    }

    @Test
    public void whenRemoveAllOddNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1));
        List<Integer> deleteElements = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 5, -13, 9, 11, 1));
        ListUtils.removeAll(input, deleteElements);
        assertThat(Arrays.asList(2, 4, 6, 6, 4, 2), Is.is(input));
    }
}