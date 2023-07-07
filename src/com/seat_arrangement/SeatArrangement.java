package com.seat_arrangement;

import com.seat_arrangement.controller.SeatArrangementController;
import com.seat_arrangement.controller.StudentInfoController;

public class SeatArrangement {

    static SeatArrangementController controller = new SeatArrangementController();
    static StudentInfoController controller2th = new StudentInfoController();

    public static void main(String[] args) {

        // 프로그램 실행
        controller.run();
//        new Thread(controller2th).start();
//        controller2th.studentDetails();


    }

}