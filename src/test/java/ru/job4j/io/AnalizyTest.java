package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenAnalizy1() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("analizy.log");
        File target = folder.newFile("analizy_target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.toString(), target.toString());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            while (in.ready()) {
                assertThat(in.readLine(), is("10:57:01;10:59:01"));
                assertThat(in.readLine(), is("11:01:02;11:02:02"));
                assertFalse(in.ready());
            }
        }
    }

    @Test
    public void whenAnalizy2() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("analizy.log");
        File target = folder.newFile("analizy_target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.toString(), target.toString());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            while (in.ready()) {
                assertThat(in.readLine(), is("10:57:01;11:02:02"));
                assertFalse(in.ready());
            }
        }
    }

    @Test
    public void whenAnalizy3() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("analizy.log");
        File target = folder.newFile("analizy_target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 10:56:01");
            out.println("500 10:57:01");
            out.println("200 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("500 11:02:02");
        }
        analizy.unavailable(source.toString(), target.toString());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            while (in.ready()) {
                assertThat(in.readLine(), is("10:57:01;10:58:01"));
                assertThat(in.readLine(), is("10:59:01;"));
                assertFalse(in.ready());
            }
        }
    }
}