package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Roman Akulov"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Roman Akulov"));
        assertThat(config.value("surname"), is("Akulov"));
    }

    @Test
    public void whenNotPairs() {
        String path = "./data/only_comments_and_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getValues().size(), is(0));
    }
}