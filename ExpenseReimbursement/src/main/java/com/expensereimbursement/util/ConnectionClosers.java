package com.expensereimbursement.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionClosers {

    private static Logger logger = LogManager.getFormatterLogger(ConnectionClosers.class);

    public static void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            logger.warn("Close connection problem",e);
        }
    }

    public static void closeStatement(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            logger.warn("Close statement problem",e);
        }

    }

    public static void closeResultSet(ResultSet set){
        try {
            set.close();
        } catch (SQLException e) {
            logger.warn("Close ResulSet problem",e);
        }
    }
}
