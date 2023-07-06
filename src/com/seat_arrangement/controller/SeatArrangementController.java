package com.seat_arrangement.controller;


import com.seat_arrangement.repository.*;
import com.seat_arrangement.repository.dbconnect.DBConnection;
import com.seat_arrangement.service.RandomArrangementService;
import com.seat_arrangement.util.upload.*;

import java.util.ArrayList;

import static com.seat_arrangement.util.ArrangeInfo.TODAY;

public class SeatArrangementController {

    private final RandomArrangementService service = new RandomArrangementService();

    private final ArrangementRepository arrangementRepo = new ArrangementRepository();
    private final StudentRepository studentRepo = new StudentRepository();
    private final SeatRepository seatRepo = new SeatRepository();

    private ArrayList<Integer> arrangedStudents;
    private ArrayList<Integer> arrangedSeats;
    private ArrayList<Integer> sortedStudentIds;

    //전체 코드 실행
    public void run() {
        DBConnection.getConnection();
        initInfo();
        arrange();

    }

    // 학생, 자리 정보 초기화
    private void initInfo() {
        DefaultUploader.loadInfo();

        StudentDefaultModifier.modifyInfo();
        SeatDefaultModifier.modifyInfo();
    }

    private void arrange() {
        this.arrangedStudents = service.arrangeStudent(studentRepo.findAllUsedId());
        this.arrangedSeats = service.arrangeSeat(seatRepo.findAllUsedId());

        for (int idx = 0; idx < arrangedStudents.size(); idx++) {
            arrangementRepo.save(arrangedSeats.get(idx), arrangedStudents.get(idx));
        }
    }

    private void createHTML() {
        this.sortedStudentIds = service.sortBySeat(arrangementRepo.findByDate(TODAY));

    }
}
