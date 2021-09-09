package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Test
    public void whenAnalizy1() {
        Analizy analizy = new Analizy();
        ArrayList<String> lines = new ArrayList<>();
        analizy.unavailable("./data/analizy_1.log", "./data/analizy_1_final.log");
        try (BufferedReader in = new BufferedReader(new FileReader("./data/analizy_1_final.log"))) {
            while (in.ready()) {
                lines.add(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(lines.size(), is(2));
    }

    @Test
    public void whenAnalizy2() {
        Analizy analizy = new Analizy();
        ArrayList<String> lines = new ArrayList<>();
        analizy.unavailable("./data/analizy_2.log", "./data/analizy_2_final.log");
        try (BufferedReader in = new BufferedReader(new FileReader("./data/analizy_2_final.log"))) {
            while (in.ready()) {
                lines.add(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(lines.size(), is(1));
    }
}