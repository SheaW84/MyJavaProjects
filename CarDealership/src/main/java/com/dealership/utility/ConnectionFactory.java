package com.dealership.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conn;

    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver"); // In case somehow the connection doesn't work.
            conn = DriverManager.getConnection(
                    System.getenv("urlcarlot"), // Environment variables to keep my database secure
                    System.getenv("dbusername"),
                    System.getenv("dbpassword")
            );
        } catch (SQLException e) { // Exception handling for connection
            e.printStackTrace();
        } catch (ClassNotFoundException e) { // Exception handling for Class.forName()
            e.printStackTrace();
        }

        return conn;
    }
}
