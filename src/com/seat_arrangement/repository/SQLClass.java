package com.seat_arrangement.repository;

import com.seat_arrangement.repository.connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class SQLClass extends DBConnection {

    protected static Statement stmt = null;
    protected static PreparedStatement pstmt = null;
    protected static ResultSet rs = null;

    public SQLClass() {
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
}
