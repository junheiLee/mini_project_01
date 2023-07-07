package com.seat_arrangement.util.upload;

import com.seat_arrangement.repository.SeatRepoImpl;
import com.seat_arrangement.repository.StudentRepoImpl;
import com.seat_arrangement.repository.repoInterface.SeatRepo;
import com.seat_arrangement.repository.repoInterface.StudentRepo;

import java.util.ArrayList;

public class DefaultUploader extends FileUploader {

    private static final String STUDENT_DATA_URI = "/default/student_data.txt";
    private static final String SEAT_DATA_URI = "/default/seat_data.txt";

    private static final StudentRepo studentRepo = new StudentRepoImpl();
    private static final SeatRepo seatRepo = new SeatRepoImpl();

    public DefaultUploader() {
    }

    // ArrayList<DTO>에 담아 반환(자리, 수강생)
    public static void loadInfo() {
        loadStudentsInfo(readFile(STUDENT_DATA_URI));
        loadSeatsInfo(readFile(SEAT_DATA_URI));
    }

    // insert into student (studentName, mbti) values (?, ?);
    private static void loadStudentsInfo(ArrayList<String> list) {
        String[] eachInfo;

        for (String each : list) {
            eachInfo = each.split(INFO_REGEX);
            studentRepo.save(eachInfo[0].trim(), eachInfo[1].trim());
        }
    }

    // insert into seat (seatRow, seatColumn) values (?, ?);
    private static void loadSeatsInfo(ArrayList<String> list) {
        String[] eachInfo;

        for (String each : list) {
            eachInfo = each.split(INFO_REGEX);
            seatRepo.save(Integer.parseInt(eachInfo[0].trim()), Integer.parseInt(eachInfo[1].trim()));
        }
    }

}
