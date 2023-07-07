package com.seat_arrangement.util.upload;

import com.seat_arrangement.repository.SeatRepoImpl;
import com.seat_arrangement.repository.repoInterface.SeatRepo;

import java.util.ArrayList;
import java.util.stream.Stream;

import static com.seat_arrangement.util.column.SeatColumn.*;

public class SeatDefaultModifier extends FileUploader {
    private static final String NOT_USED_DATA_URI = "/supplement/seat_not_used_row_column.txt";
    private static final boolean NOT_USED = false;

    private static SeatRepo seatRepo = new SeatRepoImpl();

    public static void modifyInfo() {
        modifyIsUsedByRowAndColumn(readFile(NOT_USED_DATA_URI));

    }

    // 행, 열 기준으로 수정할 콜럼 수정
    private static void modifyIsUsedByRowAndColumn(ArrayList<String> targetLst) {
        String[] targetColumn = {ROW, COLUMN};
        Integer[] targetValue;

        for (String each : targetLst) {
            targetValue = Stream.of(each.split(INFO_REGEX)).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            seatRepo.modify(IS_USED, NOT_USED, targetColumn, targetValue);     // update seat set ? = ? where ? = ? and ? = ?

        }
    }
}
