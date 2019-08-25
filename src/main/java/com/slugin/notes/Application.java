package com.slugin.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        createDatabase();
        SpringApplication.run(Application.class, args);
    }

    private static void createDatabase() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        String dbName = "note";
        String testDbName = "noteTest";

        try {
            Class.forName(jdbcDriver);

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "root"
            );
            statement = connection.createStatement();

            String SQL = "CREATE DATABASE IF NOT EXISTS " + dbName;
            statement.executeUpdate(SQL);

            SQL = "CREATE DATABASE IF NOT EXISTS " + testDbName;
            statement.executeUpdate(SQL);
        }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}