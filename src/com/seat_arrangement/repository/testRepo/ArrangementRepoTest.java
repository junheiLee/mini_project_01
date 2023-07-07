package com.seat_arrangement.repository.testRepo;

import com.seat_arrangement.repository.SQLClass;
import com.seat_arrangement.repository.repoInterface.ArrangementRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArrangementRepoTest extends SQLClass implements ArrangementRepo {
    private static final String INSERT_ALL = "insert into arrangement (id, arrangeDate, seatId, studentId) values (?, curdate(), ?, ?)";
    private static final String SELECT_BY_DATE = "select seatId, studentId from arrangement where arrangeDate = ?";
    private static final String SELECT_BY_ID = "select seatId, studentId from arrangement where id = ?";

    private static int idx = 0;

    public Map<Integer, Integer> findById(int id) {
        Map<Integer, Integer> arrangement = new HashMap<>();
        try {
            pstmt = conn.prepareStatement(SELECT_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                arrangement.put(rs.getInt("seatId"), rs.getInt("studentId"));
            }
            return arrangement;
        } catch (SQLException e) {
            System.out.println(SELECT_BY_ID + " Error -> " + e.getMessage());
        } finally {
            close(pstmt, rs);
        }
        return arrangement;
    }

    public static void setId(int id) {
        idx = id;
    }

    // 해당 날짜의 자리 배치 정보 저장
    public void save(int seatId, int studentId) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, this.idx);
            pstmt.setInt(2, seatId);
            pstmt.setInt(3, studentId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

    // 사용 X
    public Map<Integer, Integer> findByDate(Date date) {
        Map<Integer, Integer> arrangement = new HashMap<>();
        return arrangement;
    }


}
