package com.seat_arrangement.repository;

import com.seat_arrangement.repository.dbconnect.DBConnection;

import java.sql.*;
import java.util.ArrayList;

import static com.seat_arrangement.util.column.StudentColumn.*;


public abstract class SQLClass extends DBConnection {

    protected static Statement stmt = null;
    protected static PreparedStatement pstmt = null;
    protected static ResultSet rs = null;

    public SQLClass() {
    }

    //"select * from student"
    public void findAll(String sql) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
            }

        } catch (SQLException e) {
            System.out.println("selectAll error!" + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

    // 모든 id 반환
    public ArrayList<Integer> findAllId(String sql) {
        ArrayList<Integer> seatIds = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                seatIds.add(rs.getInt(1));
            }
            return seatIds;

        } catch (SQLException e) {
            System.out.println(sql + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
        return seatIds;
    }

    //"select *from student where studentId=?"
    public void findById(String sql, Object value, int idx) {

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString(STUDENT_NAME);
                String mbti = rs.getNString(MBTI);

                System.out.println(name);
            }
        } catch (SQLException e) {
            System.out.println("selectAll error!" + e.getMessage());
        } finally {
            close(pstmt, rs);
        }
    }

    public void insert() {

    }

    public void update() {
    }

    public void delete() {

    }
}
