package com.enset.glsid.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static  Connection connection = null;
    static {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                // connect to mysql db using jdbc and connection string
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/n7invaders","root","");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
