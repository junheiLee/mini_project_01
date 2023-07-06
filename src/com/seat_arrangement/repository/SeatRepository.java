package com.seat_arrangement.repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeatRepository extends SQLClass {
    private static final String SELECT_ALL_ID = "select seatId from seat";
    private static final String SELECT_ALL_USED_ID = SELECT_ALL_ID + " where isUsed = true";
    private static final String INSERT_ALL = "insert into seat (seatRow, seatColumn) values (?, ?)";
    private static final String UPDATE_BY_PROCESSION = "update seat set isUsed = ? where seatRow = ? and seatColumn = ?";

    public ArrayList<Integer> findAllUsedId(){
        return super.findAllId(SELECT_ALL_USED_ID);
    }

    // 자리 데이터 저장 - init() 에 사용
    public static void save(int row, int column) {
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

    // 자리 데이터 수정 - init() - modify() 에 사용
    // update seat set ? = ? where ? = ? and ? = ?
    public static void modify(String modifyColumn, boolean modifyValue, String[] targetColumn, Integer[] targetValue) {
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
}
