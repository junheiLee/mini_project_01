package main.java.seat_arrangement.util.upload;

import java.util.ArrayList;
import java.util.stream.Stream;

public class SupplementUploader extends FileUploader {
    //seat
    private static final String NOT_USED_DATA_URI = "supplement/seat_not_used_row_column.txt";
    //student
    private static final String IS_SMOKER_ID_DATA_URI = "supplement/student_isSmoker_id.txt";
    private static final String NOT_IN_PROGRESS_ID_DATA_URI = "supplement/student_not_inProgress_id.txt";


    public static void loadInfo() {
        // seat : 사용하지 않는 자리 설정
        modifyIsUsedByRowAndColumn(readFile(NOT_USED_DATA_URI));
        // student : 흡연자, 탈주자 설정
        modify(IS_SMOKER, true, readFile(IS_SMOKER_ID_DATA_URI));
        modify(IS_IN_PROGRESS, false, readFile(NOT_IN_PROGRESS_ID_DATA_URI));
    }

    // 행, 열 기준으로 수정할 콜럼 수정
    // update seat set ? = ? where ? = ? and ? = ?
    private static void modifyIsUsedByRowAndColumn(ArrayList<String> targetLst) {
        String[] targetColumn = {ROW, COLUMN};
        Integer[] targetValue;

        for (String each : targetLst) {
            targetValue = Stream.of(each.split(INFO_REGEX)).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            seatRepo.modify(IS_USED, false, targetColumn, targetValue);

        }
    }

    // ex. update student set ? = ? where ? = ?
    private static void modify(String modifyColumn,
                               boolean modifyInfo,
                               ArrayList<String> targetList) {
        for (String each : targetList) {
            studentRepo.modify(modifyColumn, modifyInfo, STUDENT_ID, Integer.parseInt(each));
        }
    }

}
