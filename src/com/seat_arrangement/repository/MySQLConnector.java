package com.seat_arrangement.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// db에 연결 담당
public abstract class MySQLConnector {
    //connection 정보
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String id_mysql = "root";
    private String pw_mysql = "qlalf79";

    protected Connection conn = null;

    public void connectMySQL() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id_mysql, pw_mysql);
            System.out.println("MySQL 드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("Class.forName(drive) ERR: " + e.getMessage());
        }catch (SQLException e) {
            System.out.println("getConnection() ERR:" + e.getMessage());
        }
    }

}