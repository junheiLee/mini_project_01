package com.seat_arrangement.repository.impl_auto_increment;

import com.seat_arrangement.repository.SQLClass;
import com.seat_arrangement.repository.repoInterface.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepoImpl extends SQLClass implements StudentRepo {
    private static final String SELECT_ALL_ID = "select studentId from student";
    private static final String SELECT_ALL_USED_ID = SELECT_ALL_ID + " where isInProgress = true";
    private static final String INSERT_ALL = "insert into student (studentName, mbti) values (?, ?)";
    private static final String MODIFY_BY_ID = "update student set ? = ? where ? = ?";


    public StudentRepoImpl() {
    }

    @Override
    public ArrayList<Integer> findAllUsedId() {
        return super.findAllId(SELECT_ALL_USED_ID);
    }

    @Override
    public void save(String name, String mbti) {
        try {
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setString(1, name);
            pstmt.setString(2, mbti);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(INSERT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

    @Override
    public void modify(String modifyColumn, boolean modifyInfo, String targetColumn, int targetValue) {
        try {
            String sql = "update student set " + modifyColumn + " = ? where " + targetColumn + " = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, modifyInfo);
            pstmt.setInt(2, targetValue);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(MODIFY_BY_ID + "Error -> " + e.getMessage());
        }
    }

}
