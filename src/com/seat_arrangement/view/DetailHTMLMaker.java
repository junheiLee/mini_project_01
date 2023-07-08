package com.seat_arrangement.view;

import com.seat_arrangement.DTO.StudentDTO;

import java.io.File;
import java.util.ArrayList;

public class DetailHTMLMaker extends HTMLMaker implements HTMLTag {

    private static String content = "";

    public DetailHTMLMaker() {
    }

    public static void create(ArrayList<StudentDTO> students) {
        make(students);
    }

    public static void make(ArrayList<StudentDTO> students) {
        int length = students.size();
        System.out.println(length);

        for (int i = 0; i < length; i++) {
            File file = new File(i + 1 + ".html");

            content += PRE_DETAIL_FRAME;
            content += OPEN_UL;
            content += (OPEN_LI + students.get(i).getMbti() + CLOSE_LI);
            if (students.get(i).isSmoker()) {
                content += (OPEN_LI + "흡연자" + CLOSE_LI);
            } else {
                content += (OPEN_LI + "비흡연자" + CLOSE_LI);
            }

            content += CLOSE_UL;
            content += POST_FRAME;

            save("details/", file, content);
            content = "";
        }

    }
}
