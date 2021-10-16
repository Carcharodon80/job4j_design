package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * 2. Формат JSON [#313164]
 */
public class Pet {
    private final String name;
    private final boolean isBird;
    private final int age;
    private final Contact ownerContact;
    private final String[] parents;

    public Pet(String name, boolean isBird, int age, Contact ownerContact, String... parents) {
        this.name = name;
        this.isBird = isBird;
        this.age = age;
        this.ownerContact = ownerContact;
        this.parents = parents;
    }

    public static void main(String[] args) {
        Pet pet = new Pet("Doggy", false, 7, new Contact(456789, "+7 123 45 67"),
                "Mummy", "Daddy");
        final Gson gson = new GsonBuilder().create();
        String petJson = gson.toJson(pet);
        System.out.println(petJson);

        Pet petMod = gson.fromJson(petJson, Pet.class);
        System.out.println(petMod);
    }

    @Override
    public String toString() {
        return "Pet{"
                + "name='" + name + '\''
                + ", isBird=" + isBird
                + ", age=" + age
                + ", ownerContact=" + ownerContact
                + ", parents=" + Arrays.toString(parents)
                + '}';
    }
}
