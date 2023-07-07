package com.seat_arrangement.controller;

import com.seat_arrangement.repository.dbconnect.DBConnection;
import com.seat_arrangement.repository.repoInterface.SeatRepo;
import com.seat_arrangement.repository.repoInterface.StudentRepo;
import com.seat_arrangement.repository.testRepo.ArrangementRepoTest;
import com.seat_arrangement.repository.testRepo.SeatRepoTest;
import com.seat_arrangement.repository.testRepo.StudentRepoTest;
import com.seat_arrangement.service.RandomArrangementService;
import com.seat_arrangement.util.upload.DefaultUploader;
import com.seat_arrangement.util.upload.SeatDefaultModifier;
import com.seat_arrangement.util.upload.StudentDefaultModifier;
import com.seat_arrangement.view.SeatArrangementHTMLMaker;

import java.util.ArrayList;

public class TestController extends SeatArrangementController {

    private final RandomArrangementService service = new RandomArrangementService();

    private final ArrangementRepoTest arrangementRepo = new ArrangementRepoTest();
    private final StudentRepo studentRepo = new StudentRepoTest();
    private final SeatRepo seatRepo = new SeatRepoTest();

    private final ArrayList<Integer> arrangedStudents = new ArrayList<>();
    private final ArrayList<Integer> arrangedSeats = new ArrayList<>();
    private ArrayList<Integer> sortedStudentIds = new ArrayList<>(); // HTML 배치에 쓰일 실제 36좌석 배치 List

    //전체 코드 실행
    public void run() {
        int id = 1;
        DBConnection.get();
        initInfo();
        arrange(id);
        DBConnection.close();
        createHTML();
    }

    // 학생, 자리 정보 초기화
    private void initInfo() {
        studentRepo.deleteAll();
        seatRepo.deleteAll();
        DefaultUploader.loadInfo();

        StudentDefaultModifier.modifyInfo();
        SeatDefaultModifier.modifyInfo();
        this.setUnused();
    }

    // 배치하기
    private void arrange(int id) {
        this.arrangedStudents.addAll(service.arrangeStudent(studentRepo.findAllUsedId()));
        this.arrangedSeats.addAll(service.arrangeSeat(seatRepo.findAllUsedId()));

        arrangementRepo.setId(id);
        for (int idx = 0; idx < arrangedStudents.size(); idx++) {
            arrangementRepo.save(this.arrangedSeats.get(idx), this.arrangedStudents.get(idx));
        }
        this.sortedStudentIds = service.sortBySeat(arrangementRepo.findById(id));
    }

    // 결원과 사용하지 않는 자리 매핑해 총 결과값으로 변경 (모든 배치 방법에 필수)
    // 결원 id = 0 취급
    private void setUnused() {
        ArrayList<Integer> unusedSeats = seatRepo.findAllNotUsedId();
        for (int i = 0; i < unusedSeats.size(); i++) {
            this.arrangedStudents.add(0);
        }
        this.arrangedSeats.addAll(unusedSeats);
    }


    private void createHTML() {
        SeatArrangementHTMLMaker.create(sortedStudentIds);
    }
}
