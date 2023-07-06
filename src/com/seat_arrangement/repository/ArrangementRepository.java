package com.seat_arrangement.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArrangementRepository extends SQLClass{
    private static final String INSERT_ALL = "insert into arrangement (arrangeDate, seatId, studentId) values (curdate(), ?, ?)";
    private static final String SELECT_BY_DATE = "select seatId, studentId from arrangement where arrangeDate = date_format(?, '%Y%M%D')";

    // 해당 날짜 자리 배치 정보 <자리id, 학생id> 가져오기
    public Map<Integer, Integer> findByDate(String date) {
        Map<Integer, Integer> arrangement = new HashMap<>();
        try {
            pstmt = conn.prepareStatement(SELECT_BY_DATE);
            pstmt.setString(1, date);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                arrangement.put(rs.getInt("seatId"), rs.getInt("studentId"));
            }
            return arrangement;
        } catch (SQLException e) {
            System.out.println(SELECT_BY_DATE + " Error -> " + e.getMessage());
        } finally {
            close(pstmt, rs);
        }
        return arrangement;
    }


    // 해당 날짜의 자리 배치 정보 저장
    public void save(int seatId, int studentId) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, seatId);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }
}
