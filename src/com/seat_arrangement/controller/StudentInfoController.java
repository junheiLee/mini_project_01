package com.seat_arrangement.controller;

import com.seat_arrangement.DTO.StudentDTO;
import com.seat_arrangement.view.HTMLMaker;

import java.util.ArrayList;

public class StudentInfoController{
    SeatArrangementController seatArrangementController = new SeatArrangementController();
    StudentDTO studentDTO = new StudentDTO();
    boolean timeOut = false;
    ArrayList<String> studentsDetails = new ArrayList<>();



      public StudentInfoController() {
        studentDetails();
    }


    public void studentDetails() {
        for (int i = 0; i < 10; i++) {


            System.out.println(studentsDetails);
        }
    }

}
