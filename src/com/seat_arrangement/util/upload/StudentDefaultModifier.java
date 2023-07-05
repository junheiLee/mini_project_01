package com.seat_arrangement.util.upload;

import java.util.ArrayList;

import static com.seat_arrangement.util.SeatColumn.StudentColumn.*;

public class StudentDefaultModifier extends FileUploader {

    private static final String IS_SMOKER_ID_DATA_URI = "/supplement/student_isSmoker_id.txt";
    private static final String NOT_IN_PROGRESS_ID_DATA_URI = "/supplement/student_not_inProgress_id.txt";

    public StudentDefaultModifier() {
    }

    // 흡연 여부, 수료중 여부 default 값 총 변경
    public static void loadInfo() {
        modify(STUDENT_ID, readFile(IS_SMOKER_ID_DATA_URI), IS_SMOKER, true);
        modify(STUDENT_ID, readFile(NOT_IN_PROGRESS_ID_DATA_URI), IS_IN_PROGRESS, false);
    }

    // ex. update student set isSmoker = true where studentId = ?
    // "update student set " + modifyColumnToInfo + " where " + targetColumn + " = " + each
    private static void modify(String targetColumn,
                                   ArrayList<String> targetLst,
                                   String modifyColumn,
                                   boolean modifyInfo) {
        String sql = "update student set " + modifyColumn + " = ? where " + targetColumn + " = ?";
        for (String each : targetLst) {
//			studentRepository.modify(sql, each, modifyInfo);
        }
    }

}

