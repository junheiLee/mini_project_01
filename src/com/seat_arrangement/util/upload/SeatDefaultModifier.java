package com.seat_arrangement.util.upload;

import java.util.ArrayList;

import static com.seat_arrangement.util.column.SeatColumn.IS_USED;

public class SeatDefaultModifier extends FileUploader{
    private static final String NOT_USED_DATA_URI = "/supplement/seat_not_used_row_column.text";
    private static final boolean NOT_USED = false;
    private static String INFO_REGEX = ", ";

    public static void modifyInfo() {
        modify(readFile(NOT_USED_DATA_URI), IS_USED);

    }

    // update seat set isUsed = false where row = ? and column = ?
    // 추후 수정 필요
    private static void modify(ArrayList<String> targetLst, String modifyColumn) {
        String[] eachTargetInfo;

        String sql = "update seat set " + modifyColumn + " = ? where row = ? and column = ?";
        for (String each : targetLst) {
            eachTargetInfo = each.split(INFO_REGEX);
//			seatRepository.modify(sql, eachTargetInfo[0], eachTargetInfo[1], NOT_USED);
        }
    }
}
