package com.seat_arrangement.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// 저장된 배치 정보를 토대로 html 파일로 배치도 저장
public class HTMLMaker extends HTMLTag {

    private final static String URL = System.getProperty("user.dir") + "/src/com/seat_arrangement/util/";
    private static final File seat_arrangement = new File("seat_arrangement.html");
    private static String content = "";

    public static void save() {
        FileWriter fw = null;

        try {
            fw = new FileWriter(URL + seat_arrangement);
            fw.write(content);
        } catch (IOException e) {
            System.out.println("File Writer ===>" + e.getMessage());
        } finally {

            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("saveHTML() finally ==>" + e.getMessage());
            }
        }
    }

    public static void make(ArrayList<Integer> sortedStudentIds) {
        int length = sortedStudentIds.size();

        content += FRE_FRAME;
        for (int i = 1; i < length; i++) {
            content += OPEN_TR;
            i--;
            for (int j = 0; j < 6; j++) {
                content += OPEN_TD;
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