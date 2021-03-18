package com.expensereimbursement.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Logger logger = LogManager.getFormatterLogger(ConnectionFactory.class);

    private static Connection conn;

    public static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver"); // In case somehow the connection doesn't work.
            conn = DriverManager.getConnection(
                    System.getenv("urlersys"), // Environment variables to keep my database secure
                    System.getenv("dbusername"),
                    System.getenv("dbpassword")
            );
        } catch (SQLException e) { // Exception handling for connection
            logger.warn("Can't connect to the database",e);
        } catch (ClassNotFoundException e) { // Exception handling for Class.forName()
            logger.warn("Class.forName not working",e);
        }
        return conn;
    }
}
