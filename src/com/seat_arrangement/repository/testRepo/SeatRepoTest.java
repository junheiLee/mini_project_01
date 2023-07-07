package com.seat_arrangement.repository.testImpl;

import com.seat_arrangement.repository.SQLClass;
import com.seat_arrangement.repository.repoInterface.SeatRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeatRepoTest extends SQLClass implements SeatRepo {

    private static final String INSERT_ALL = "insert into seat (seatId, seatRow, seatColumn) values (?, ?, ?)";
    private static final String DELETE_ALL = "delete from seat";

    private static int id = 0;

    public ArrayList<Integer> findAllUsedId() {
        return super.findAllId(SELECT_ALL_USED_ID);
    }

    public ArrayList<Integer> findAllNotUsedId() {
        return super.findAllId(SELECT_ALL_NOT_USED_ID);
    }

    public void save(int row, int column) {

        try {
            this.id++;
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, this.id);
            pstmt.setInt(2, row);
            pstmt.setInt(3,  column);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

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

    public void deleteAll() {
        this.id = 0;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(DELETE_ALL);
        }catch(SQLException e) {
            System.out.println(DELETE_ALL + "Error -> " + e.getMessage());
        }

    }
}