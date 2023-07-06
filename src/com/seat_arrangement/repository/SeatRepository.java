package com.seat_arrangement.repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeatRepository extends SQLClass {
    private static final String SELECT_ALL_ID = "select seatId from seat";
    private static final String INSERT_ALL = "insert into seat (seatRow, seatColumn) values (?, ?)";
    private static final String UPDATE_BY_PROCESSION = "update seat set ? = ? where ? = ? and ? = ?";

    // 모든 자리 id 반환
    public ArrayList<Integer> findAllId() {
        ArrayList<Integer> seatIds = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_ID);

            while (rs.next()) {
                seatIds.add(rs.getInt(1));
            }
            return seatIds;

        } catch (SQLException e) {
            System.out.println(SELECT_ALL_ID + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
        return seatIds;
    }

    // 자리 데이터 저장 - init() 에 사용
    public static void save(int row, int column) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, row);
            pstmt.setInt(2, column);
            rs = pstmt.executeQuery();

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
            pstmt = conn.prepareStatement(UPDATE_BY_PROCESSION);
            pstmt.setString(1, modifyColumn);
            pstmt.setBoolean(2, modifyValue);

            pstmt.setString(3, targetColumn[0]);
            pstmt.setInt(4, targetValue[0]);

            pstmt.setString(5, targetColumn[1]);
            pstmt.setInt(6, targetValue[1]);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println();
        } finally {
            close(pstmt, rs);
        }
    }
}
