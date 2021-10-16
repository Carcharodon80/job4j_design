package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void contactTest() throws IOException, ClassNotFoundException {
        Contact oldContact = new Contact(765890, "+7 111 22 33");
        Contact newContact;
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(oldContact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
            newContact = (Contact) ois.readObject();
        }
        assertEquals(oldContact.toString(), newContact.toString());
    }

}