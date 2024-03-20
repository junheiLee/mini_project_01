package main.java.seat_arrangement.util;

import java.sql.Date;

public abstract class ArrangeInfo {
    public static final Date TODAY = new Date(System.currentTimeMillis());

    // student table column name
    public static final String STUDENT_ID = "studentId";
    public static final String STUDENT_NAME = "studentName";
    public static final String MBTI = "mbti";
    public static final String IS_SMOKER = "isSmoker";
    public static final String IS_IN_PROGRESS = "isInProgress";

    // seat table column name
    public static final String ROW = "seatRow";
    public static final String COLUMN = "seatColumn";
    public static final String IS_USED = "isUsed";

    //
    public static final int EMPTY_SEAT = 0;
}
