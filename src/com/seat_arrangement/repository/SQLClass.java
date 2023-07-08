package com.seat_arrangement.repository;

import com.seat_arrangement.repository.connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class SQLClass extends DBConnection {

    protected static Statement stmt = null;
    protected static PreparedStatement pstmt = null;
    protected static ResultSet rs = null;

    public SQLClass() {
    }

}
