package com.cashwu.javajunit.ch19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author cash.wu
 * @since 2024/07/01
 */
public class ConnectionManger {

    private static Connection connection;

    public static Connection openConnection() {

        try {

            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            return connection;

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
