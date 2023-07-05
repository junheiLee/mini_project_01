package com.seat_arrangement.util.upload;

import java.util.ArrayList;

public class DefaultModifier extends FileUploader{

    private static final String STUDENT_IS_SMOKER_ID_DATA_URI = "/supplement/student_isSmoker_id.txt";
    private static final String STUDENT_NOT_IN_PROGRESS_ID_DATA_URI = "/supplement/student_not_inProgress_id.txt";
    private static final String SEAT_NOT_USED_DATA_URI = "/supplement/seat_not_used_row_column.text";
    private static String INFO_REGEX = ", ";

    public DefaultModifier() {}

    // ArrayList<DTO>에 담아 반환(자리, 수강생)
    public static void loadInfo(){
        modifyStudentBooleanInfo("studentId", readFile(STUDENT_IS_SMOKER_ID_DATA_URI), "isSmoker", true);
        modifyStudentBooleanInfo("studentId", readFile(STUDENT_NOT_IN_PROGRESS_ID_DATA_URI), "isInProgress", false);

//        modifySeatDefaultInfo(readFile(SEAT_NOT_USED_DATA_URI), "isUsed", );
    }

    // update student set isSmoker = true where studentId = ?
    // "update student set " + modifyColumnToInfo + " where " + targetColumn + " = " + each
    private static void modifyStudentBooleanInfo( String targetColumn,
                                                  ArrayList<String> targetLst,
                                                  String modifyColumn,
                                                  boolean modifyInfo){
        String sql = "update student set " + modifyColumn + " = ? where "+ targetColumn + " = ?";
        for(String each: targetLst) {
//			studentRepository.modify(sql, each, modifyInfo);
        }
    }

    // update seat set isUsed = false where row = ? and column = ?
    // 추후 수정 필요
    private static void modifySeatDefaultInfoByProcession(ArrayList<String> targetLst, String modifyColumn, boolean modifyInfo){
        String[] eachTargetInfo;

        String sql = "update seat set " + modifyColumn + " = ? where row = ? and column = ?";
        for(String each: targetLst) {
            eachTargetInfo = each.split(INFO_REGEX);
//			seatRepository.modify(sql, eachTargetInfo[0], eachTargetInfo[1], modifyInfo);
        }
    }
}

