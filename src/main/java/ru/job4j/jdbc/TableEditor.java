package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * 0.1. Statement [#379306]
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * - createTable() – создает пустую таблицу без столбцов с указанным именем;
     */
    public void createTable(String tableName) throws Exception {
        String query = String.format("create table if not exists %s (id serial primary key);", tableName);
        executeQuery(query);
        System.out.println(getTableScheme(connection, tableName));
    }

    /**
     * - dropTable() – удаляет таблицу по указанному имени;
     */
    public void dropTable(String tableName) throws Exception {
        String query = String.format("drop table if exists %s;", tableName);
        executeQuery(query);
        System.out.printf("Table %s deleted!%n", tableName);
    }

    /**
     * - addColumn() – добавляет столбец в таблицу;
     */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String query = String.format("alter table %s add column %s %s;",
                tableName, columnName, type);
        executeQuery(query);
        System.out.println(getTableScheme(connection, tableName));
    }

    /**
     *  - dropColumn() – удаляет столбец из таблицы;
     */
    public void dropColumn(String tableName, String columnName) throws Exception {
        String query = String.format("alter table %s drop column %s;",
                tableName, columnName);
        executeQuery(query);
        System.out.println(getTableScheme(connection, tableName));
    }

    /**
     * - renameColumn() – переименовывает столбец
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String query = String.format("alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName);
        executeQuery(query);
        System.out.println(getTableScheme(connection, tableName));
    }

    private void executeQuery(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("app.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        String tableName = "demoTable1";
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, "addedColumn", "varchar(20)");
            tableEditor.renameColumn(tableName, "addedColumn", "renamedColumn");
            tableEditor.dropColumn(tableName, "renamedColumn");
            tableEditor.dropTable(tableName);
    }
}
