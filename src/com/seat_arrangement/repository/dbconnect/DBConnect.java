package com.seat_arrangement.repository.dbconnect;

import java.sql.*;

// db connection에 필요한 상수 모음
public abstract class DBConnect {

    //DRIVER -> "org.mariadb.jdbc.Driver" hdh.ver
    public final static String DRIVER = "";

    //URL -> "jdbc:mariadb://localhost:3306/dbName" hdh.ver
    public final static String URL = "jdbc:mysql://localhost/~/";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "";
    public static Connection conn = null;

    public DBConnect() {
        this.ConnectionDB();
    }

    public void ConnectionDB() {
        try {
            Class.forName(DRIVER);
            try {
                conn = DriverManager.getConnection(URL);

            } catch (SQLException e) {
                System.out.println("Connection err! : " + e.getMessage());

            }
            System.out.println("Connect success!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver err! : " + e.getMessage());

        }
    }
}