package com.seat_arrangement.controller;


import com.seat_arrangement.repository.ArrangementRepoImpl;
import com.seat_arrangement.repository.SeatRepoImpl;
import com.seat_arrangement.repository.StudentRepoImpl;
import com.seat_arrangement.repository.dbconnect.DBConnection;
import com.seat_arrangement.repository.repoInterface.ArrangementRepo;
import com.seat_arrangement.repository.repoInterface.SeatRepo;
import com.seat_arrangement.repository.repoInterface.StudentRepo;
import com.seat_arrangement.service.RandomArrangementService;
import com.seat_arrangement.util.upload.DefaultUploader;
import com.seat_arrangement.util.upload.SeatDefaultModifier;
import com.seat_arrangement.util.upload.StudentDefaultModifier;
import com.seat_arrangement.view.HTMLMaker;

import java.util.ArrayList;

import static com.seat_arrangement.util.ArrangeInfo.TODAY;

public class SeatArrangementController {

    private final RandomArrangementService service = new RandomArrangementService();

    private final ArrangementRepo arrangementRepo = new ArrangementRepoImpl();
    private final StudentRepo studentRepo = new StudentRepoImpl();
    private final SeatRepo seatRepo = new SeatRepoImpl();

    private final ArrayList<Integer> arrangedStudents = new ArrayList<>();
    private final ArrayList<Integer> arrangedSeats = new ArrayList<>();
    private ArrayList<Integer> sortedStudentIds = new ArrayList<>(); // HTML 배치에 쓰일 실제 36좌석 배치 List

    //전체 코드 실행
    public void run() {
        DBConnection.get();
        initInfo();
        arrange();
        DBConnection.close();

        createHTML();
    }

    // 학생, 자리 정보 초기화
    private void initInfo() {
        DefaultUploader.loadInfo();

        StudentDefaultModifier.modifyInfo();
        SeatDefaultModifier.modifyInfo();

        this.setUnused();
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

    // 배치하기
    private void arrange() {
        // 자리와 학생 정렬
        this.arrangedStudents.addAll(service.arrangeStudent(studentRepo.findAllUsedId()));
        this.arrangedSeats.addAll(service.arrangeSeat(seatRepo.findAllUsedId()));

        // 해당 순서대로 arrangement db에 정보 저장
        for (int idx = 0; idx < arrangedStudents.size(); idx++) {
            arrangementRepo.save(this.arrangedSeats.get(idx), this.arrangedStudents.get(idx));
        }
        this.sortedStudentIds.clear(); // controller의 list init
        this.sortedStudentIds = service.sortBySeat(arrangementRepo.findByDate(TODAY)); //controller list에 저장
    }

    private void createHTML() {
        HTMLMaker.make(sortedStudentIds);
        HTMLMaker.save();
    }
}
