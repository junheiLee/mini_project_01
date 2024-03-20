package main.java.seat_arrangement.repository;

import main.java.seat_arrangement.repository.connection.DBConnection;
import main.java.seat_arrangement.repository.intf.ArrangementRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArrangementRepoImpl extends DBConnection implements ArrangementRepo {
    private static final String INSERT_ALL = "insert into arrangement (arrangeDate, seatId, studentId) values (curdate(), ?, ?)";
    private static final String SELECT_BY_DATE = "select seatId, studentId from arrangement where arrangeDate = ?";

    @Override
    public Map<Integer, Integer> findByDate(Date date) {
        Map<Integer, Integer> arrangement = new HashMap<>();
        try {
            pstmt = conn.prepareStatement(SELECT_BY_DATE);
            pstmt.setDate(1, date);
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


    @Override
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

    // sql limit 으로 id(1~30), id(31~60) 배치된 자리 순서로 가져오는 기능
    @Override
    public Map<Integer, Integer> findById(int idx) {
        return null;
    }
}