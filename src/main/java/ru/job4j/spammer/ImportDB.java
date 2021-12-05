package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 0.2. PrepareStatement [#379307]
 * У нас появился клиент, который хочет создать базу данных для спамеров.
 * На рынке ему продали диск, в котором находятся txt файлы.
 * Клиент просит перевести эти файлы в базу данных PostgreSQL.
 */
public class ImportDB {
    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * загружает данные из .txt файла в список
     * Формат данных dump.txt:
     * Petr Arsentev;parsentev@yandex.ru;
     * Ivan Ivanov;iivanov@gmail.com;
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(x -> {
                String[] line = x.split(";");
                if (line.length != 2) {
                    throw new IllegalArgumentException("Некорректные данные в файле.");
                }
                String name = line[0];
                String email = line[1];
                if ("".equals(name) || "".equals(email) || !email.contains("@")) {
                    throw new IllegalArgumentException("Некорректные данные в файле.");
                }
                users.add(new User(name, email));
            });
        }
        return users;
    }

    /**
     * из списка добавляет данные в базу SQL
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps =
                             cnt.prepareStatement("insert into users (name, email) values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }

}
