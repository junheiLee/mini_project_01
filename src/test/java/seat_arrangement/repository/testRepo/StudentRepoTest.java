package test.java.seat_arrangement.repository.testRepo;

import main.java.seat_arrangement.DTO.StudentDTO;
import main.java.seat_arrangement.repository.connection.DBConnection;
import main.java.seat_arrangement.repository.intf.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepoTest extends DBConnection implements StudentRepo {

    private static final String INSERT_ALL = "insert into student (studentId, studentName, mbti, vision) values (?, ?, ?, ?)";
    private static final String DELETE_ALL = "delete from student";
    private static int id = 0;

    public StudentRepoTest() {
    }

    @Override
    public ArrayList<StudentDTO> findAllInProgress() {
        ArrayList<StudentDTO> students = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL + " where isInProgress = true");
            while (rs.next()) {
                StudentDTO student = new StudentDTO();

                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getString("studentName"));
                student.setMbti(rs.getString("mbti"));
                student.setVision(rs.getFloat("vision"));
                student.setSmoker(rs.getBoolean("isSmoker"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println(SELECT_ALL + " Error -> " + e.getMessage());
        } finally {
            close(stmt, rs);
        }
        System.out.println(students.size());
        return students;
    }

    @Override
    public void save(String name, String mbti, float vision) {
        try {
            this.id++;
            pstmt = conn.prepareStatement(INSERT_ALL);
            pstmt.setInt(1, this.id);
            pstmt.setString(2, name);
            pstmt.setString(3, mbti);
            pstmt.setFloat(4, vision);
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

    @Override
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