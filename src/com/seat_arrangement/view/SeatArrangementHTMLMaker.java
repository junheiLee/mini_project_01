package com.seat_arrangement.view;

import java.io.File;
import java.util.ArrayList;

import static com.seat_arrangement.util.ArrangeInfo.EMPTY_SEAT;

// 저장된 배치 정보를 토대로 html 파일로 배치도 저장
public class SeatArrangementHTMLMaker extends HTMLMaker implements HTMLTag {

    private static final File seat_arrangement = new File("seat_arrangement.html");
    private static String content = "";

    public static void create(ArrayList<Integer> sortedStudentIds) {
        make(sortedStudentIds);
        save("", seat_arrangement, content);
    }

    public static void make(ArrayList<Integer> sortedStudentIds) {
        int length = sortedStudentIds.size();

        content += FRE_SEAT_ARRANGEMENT_FRAME;
        for (int i = 1; i < length; i++) {
            content += OPEN_TR;
            i--;
            for (int j = 0; j < 6; j++) {

                content += OPEN_TD;
                if (sortedStudentIds.get(i) != EMPTY_SEAT) {
                    content += (OPEN_LINK + URL + "details/" + sortedStudentIds.get(i) + CLOSE_LINK);
                }
                content += OPEN_IMAGE;
                content += sortedStudentIds.get(i);
                content += CLOSE_IMAGE;
                content += CLOSE_TD;
                i++;
            }
            content += CLOSE_TR;
        }
        content += POST_FRAME;
    }
}