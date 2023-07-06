package com.seat_arrangement.repository.dbconnect;

import java.sql.*;

// db connection에 필요한 상수 모음
public abstract class DBConnection {
    //db명
    private final static String DB_NAME = "mini_project_01";

    //DRIVER -> "org.mariadb.jdbc.Driver" hdh.ver
    private final static String DRIVER = "";

    //URL -> "jdbc:mariadb://localhost:3306/dbName" hdh.ver
    private final static String URL = "jdbc:mysql://localhost:3306/"
                                        + DB_NAME
                                        +"?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "qlalf79";

    protected static Connection conn = null;

    public DBConnection() {
        this.getConnection();
    }

    protected void getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect success!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver err! : " + e.getMessage());

        }catch (SQLException e) {
            System.out.println("Connection err! : " + e.getMessage());
        }
    }

    protected static void close(Statement stmt, ResultSet rs){

        if (rs != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Close ResultSet Error-> " + e.getMessage());
            }
        }

        if( stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Close Statement Error->" + e.getMessage());
            }
        }

    }
}