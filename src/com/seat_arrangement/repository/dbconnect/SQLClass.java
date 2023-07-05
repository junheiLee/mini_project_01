package com.seat_arrangement.repository.dbconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//CRUD 메소드 생성
public class SQLClass extends DBConnect{

    public SQLClass() {

    }

    public void selectAll() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select *from testdb");

            while (rs.next()) {

                String name = rs.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println("select error!"+e.getMessage());
        }finally {

            try {
                rs.close();
                stmt.close();

            } catch (SQLException e) {
                System.out.println("close err!" + e.getMessage());
            }
        }
    }

    public void selectOne() {

    }

    public void insert() {

    }

    public void update() {
    }

    public void delete() {

    }
}
