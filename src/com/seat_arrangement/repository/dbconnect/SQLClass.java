package com.seat_arrangement.repository.dbconnect;

import java.sql.*;


public class SQLClass extends DBConnect {

    public Statement stmt = null;
    public PreparedStatement ptsmt = null;
    public ResultSet rs = null;

    public SQLClass() {

    }

    public void selectAllName() {

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select *from student");

            while (rs.next()) {

                String name = rs.getString("studentName");
                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println("selectAll error!" + e.getMessage());
        } finally {

            try {
                rs.close();
                stmt.close();

            } catch (SQLException e) {
                System.out.println("close err!" + e.getMessage());
            }
        }
    }

    public void selectOneName() {
        //studentId
        int idx = 1;

        String query = "select *from student where studentId=?";

        try {

            ptsmt = conn.prepareStatement(query);
            ptsmt.setInt(1, idx);
            rs = ptsmt.executeQuery();

            while (rs.next()) {


                String name = rs.getString("studentName");
                String mbti = rs.getNString("mbti");


                System.out.println(name);

            }
        } catch (SQLException e) {
            System.out.println("selectAll error!" + e.getMessage());
        }

//        finally {
//
//            try {
//                rs.close();
//                stmt.close();
//
//            } catch (SQLException e) {
//                System.out.println("close err!" + e.getMessage());
//            }s
//        }
    }

    public void insert() {

    }

    public void update() {
    }

    public void delete() {

    }
}
