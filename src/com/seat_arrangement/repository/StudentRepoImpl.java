package com.seat_arrangement.repository;

import com.seat_arrangement.repository.repoInterface.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepoImpl extends SQLClass implements StudentRepo {

    private static final String INSERT_ALL = "insert into student (studentName, mbti) values (?, ?)";

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
            System.out.println(UPDATE_BY_ID + "Error -> " + e.getMessage());
        } finally {
            close(pstmt, rs);
        }
    }

    @Override
    public void deleteAll() {
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(DELETE_ALL);
        } catch(SQLException e){
            System.out.println(DELETE_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
    }

}
