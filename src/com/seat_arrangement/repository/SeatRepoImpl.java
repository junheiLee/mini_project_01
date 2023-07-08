package com.seat_arrangement.repository;

import com.seat_arrangement.repository.intf.SeatRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeatRepoImpl extends SQLClass implements SeatRepo {

    private static final String INSERT_ALL = "insert into seat (seatRow, seatColumn) values (?, ?)";

    @Override
    public ArrayList<Integer> findAllUsedId() {
        return super.findAllId(SELECT_ALL_USED_ID);
    }

    @Override
    public ArrayList<Integer> findAllNotUsedId() {
        return super.findAllId(SELECT_ALL_NOT_USED_ID);
    }

    @Override
    public void save(int row, int column) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, row);
            pstmt.setInt(2, column);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

    @Override
    // update seat set ? = ? where ? = ? and ? = ?
    public void modify(String modifyColumn, boolean modifyValue, String[] targetColumn, Integer[] targetValue) {
        try {
            String sql = "update seat set " + modifyColumn
                    + " = ? where " + targetColumn[0]
                    + " = ? and " + targetColumn[1] + " = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, modifyValue);

            pstmt.setInt(2, targetValue[0]);
            pstmt.setInt(3, targetValue[1]);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(UPDATE_BY_PROCESSION + "Error -> " + e.getMessage());
        } finally {
            close(pstmt, rs);
        }
    }

    @Override
    public void deleteAll() {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(DELETE_ALL);
        } catch (SQLException e) {
            System.out.println(DELETE_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }
}
