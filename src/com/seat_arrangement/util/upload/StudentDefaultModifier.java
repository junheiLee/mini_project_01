package com.seat_arrangement.util.upload;

import com.seat_arrangement.repository.StudentRepoImpl;
import com.seat_arrangement.repository.repoInterface.StudentRepo;

import java.util.ArrayList;

import static com.seat_arrangement.util.column.StudentColumn.*;

public class StudentDefaultModifier extends FileUploader {

    private static final String IS_SMOKER_ID_DATA_URI = "/supplement/student_isSmoker_id.txt";
    private static final String NOT_IN_PROGRESS_ID_DATA_URI = "/supplement/student_not_inProgress_id.txt";

    private static final StudentRepo studentRepo = new StudentRepoImpl();

    public StudentDefaultModifier() {
    }

    // 흡연 여부, 수료중 여부 default 값 총 변경
    public static void modifyInfo() {
        modify(IS_SMOKER, true, STUDENT_ID, readFile(IS_SMOKER_ID_DATA_URI));
        modify(IS_IN_PROGRESS, false, STUDENT_ID, readFile(NOT_IN_PROGRESS_ID_DATA_URI));
    }

    // ex. update student set ? = ? where ? = ?
    private static void modify(String modifyColumn,
                               boolean modifyInfo,
                               String targetColumn,
                               ArrayList<String> targetList) {
        for (String each : targetList) {
            studentRepo.modify(modifyColumn, modifyInfo, targetColumn, Integer.parseInt(each));
        }
    }

}

