package com.seat_arrangement.repository.testRepo;

import com.seat_arrangement.repository.SQLClass;
import com.seat_arrangement.repository.repoInterface.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepoTest extends SQLClass implements StudentRepo {

    private static final String INSERT_ALL = "insert into student (studentId, studentName, mbti) values (?, ?, ?)";
    private static final String DELETE_ALL = "delete from student";
    private static int id = 0;

    public StudentRepoTest() {
    }

    @Override
    public ArrayList<Integer> findAllUsedId() {
        return super.findAllId(SELECT_ALL_USED_ID);
    }

    @Override
    public void save(String name, String mbti) {
        try {
            this.id++;
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, this.id);
            pstmt.setString(2, name);
            pstmt.setString(3, mbti);
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
            System.out.println(UPDATE_BY_ID + "Error -> " + e.getMessage());
        }
    }

    public void deleteAll() {
        this.id = 0;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(DELETE_ALL);
        } catch (SQLException e) {
            System.out.println(DELETE_ALL + "Error -> " + e.getMessage());
        }

    }

}