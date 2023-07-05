package com.seat_arrangement.controller;


import com.seat_arrangement.repository.ArrangementRepository;
import com.seat_arrangement.repository.SeatRepository;
import com.seat_arrangement.repository.StudentRepository;
import com.seat_arrangement.service.RandomArrangementService;
import com.seat_arrangement.util.upload.DefaultUploader;
import com.seat_arrangement.util.upload.SeatDefaultModifier;
import com.seat_arrangement.util.upload.StudentDefaultModifier;

import java.util.ArrayList;

public class SeatArrangementController {

    private RandomArrangementService randomService = new RandomArrangementService();

    private ArrangementRepository arrangements = new ArrangementRepository();
    private StudentRepository students = new StudentRepository();
    private SeatRepository seats = new SeatRepository();

    private ArrayList<Integer> studentIds;
    private ArrayList<Integer> seatIds;

    //전체 코드 실행
    public void run(){
        initInfo();
        arrangeRandom();

    }

    // 학생, 자리 정보 초기화
    private void initInfo(){
        DefaultUploader.loadInfo();

        StudentDefaultModifier.modifyInfo();
        SeatDefaultModifier.modifyInfo();
    }

    private void arrangeRandom(){
        this.studentIds = randomService.arrangeStudent(students.findAllId());
        this.seatIds = randomService.arrangeSeat(seats.findAllId());

        for(int idx = 0; idx < studentIds.size(); idx ++) {
            arrangements.save(seatIds.get(idx), studentIds.get(idx));
        }
    }
}
