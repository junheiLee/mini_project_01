package main.java.seat_arrangement.repository.connection;

import java.sql.*;

// db connection에 필요한 상수 모음
public abstract class DBConnection {

    //db명
    public static String URL = "jdbc:h2:~/test"; // 규약 - h2 접근 방법
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

    protected static Statement stmt = null;
    protected static PreparedStatement pstmt = null;
    protected static ResultSet rs = null;
    protected static Connection conn = null;

    public DBConnection() {

    }

    public static void get() {
        try {
            // JDBC 제공 DB 연결 메서드
            // 라이브러리에 있는 DB 드라이버 탐색 -> 해당 드라이버가 제공하는 커넥션(java.sql.Connection 구현체) 반환
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS student, seat, arrangement;");
            stmt.execute(
                    "CREATE TABLE student (" +
                            "studentId INT AUTO_INCREMENT, " +
                            "studentName VARCHAR(20) NOT NULL, " +
                            "mbti VARCHAR(20), " +
                            "vision float, " +
                            "isSmoker BOOLEAN DEFAULT FALSE, " +
                            "isInProgress BOOLEAN DEFAULT TRUE, " +
                            "PRIMARY KEY(studentId))");

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS seat (" +
                            "seatId INT AUTO_INCREMENT, " +
                            "seatRow TINYINT, " +
                            "seatColumn TINYINT, " +
                            "isUsed BOOLEAN DEFAULT TRUE, " +
                            "PRIMARY KEY(seatId))");

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS arrangement (" +
                            "id INT AUTO_INCREMENT, " +
                            "arrangeDate DATE, " +
                            "seatId INT, " +
                            "studentId INT, " +
                            "PRIMARY KEY(id))");

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void close() {
        try {
            conn.close();
            System.out.println("Close success!");
        } catch (SQLException e) {
            System.out.println("Close Connection Error -> " + e.getMessage());
        }
    }

    protected static void close(Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Close ResultSet Error-> " + e.getMessage());
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Close Statement Error->" + e.getMessage());
            }
        }
    }
}