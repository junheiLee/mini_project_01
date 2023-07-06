package com.seat_arrangement.repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepository extends SQLClass {
    private static final String SELECT_ALL_ID = "select studentId from student";
    private static final String SELECT_ALL_USED_ID = SELECT_ALL_ID + "where isUsed = true";
    private static final String INSERT_ALL = "insert into student (studentName, mbti) values (?, ?)";
    private static final String MODIFY_BY_ID = "update student set ? = ? where ? = ?";


    public StudentRepository() {
    }

    // 모든 학생 id 반환
    public ArrayList<Integer> findAllUsedId() {
        return super.findAllId(SELECT_ALL_USED_ID);
    }

    // 학생 데이터 저장 - init() 에 사용
    public static void save(String name, String mbti) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setString(1, name);
            pstmt.setString(2, mbti);
            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

    // 학생 데이터 수정 - init() - modify() 에 사용
    public static void modify(String modifyColumn, boolean modifyInfo, String targetColumn, int targetValue) {
        try {
            pstmt = conn.prepareStatement(MODIFY_BY_ID);
            pstmt.setString(1, modifyColumn);
            pstmt.setBoolean(2, !modifyInfo);
            pstmt.setString(3, targetColumn);
            pstmt.setInt(4, targetValue);

        } catch (SQLException e) {
            System.out.println();
        }
    }

}
