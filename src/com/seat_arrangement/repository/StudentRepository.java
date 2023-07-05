package com.seat_arrangement.repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepository extends SQLClass{
    private static final String SELECT_ALL_ID = "select studentId from student";
    private static final String INSERT_ALL = "insert into student (studentName, mbti) values (?, ?)";

    public StudentRepository() {
    }

    // 모든 학생 id 반환
    public ArrayList<Integer> findAllId(){
        ArrayList<Integer> studentIds = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_ID);

            while(rs.next()) {
                studentIds.add(rs.getInt(1));
            }
            return studentIds;

        } catch (SQLException e) {
            System.out.println(SELECT_ALL_ID + " Error -> " + e.getMessage());
        }finally {
            close(stmt, rs);
        }
        return studentIds;

    }

    // 학생 데이터 저장 - init() 에 사용
    public static void saveStudent(String name, String mbti) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setString(1, name);
            pstmt.setString(1, mbti);
            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }


}
