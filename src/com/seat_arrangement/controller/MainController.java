package com.seat_arrangement.controller;


import com.seat_arrangement.DTO.StudentDTO;
import com.seat_arrangement.repository.ArrangementRepoImpl;
import com.seat_arrangement.repository.SeatRepoImpl;
import com.seat_arrangement.repository.StudentRepoImpl;
import com.seat_arrangement.repository.connection.DBConnection;
import com.seat_arrangement.repository.intf.ArrangementRepo;
import com.seat_arrangement.repository.intf.SeatRepo;
import com.seat_arrangement.repository.intf.StudentRepo;
import com.seat_arrangement.service.ArrangementService;
import com.seat_arrangement.service.VisionArrangementService;
import com.seat_arrangement.util.upload.DefaultUploader;
import com.seat_arrangement.util.upload.SupplementUploader;
import com.seat_arrangement.view.DetailHTMLMaker;
import com.seat_arrangement.view.SeatArrangementHTMLMaker;

import java.util.ArrayList;

import static com.seat_arrangement.util.ArrangeInfo.TODAY;

public class MainController implements SeatArrangementController {

    private final ArrangementService service = new VisionArrangementService();

    private final ArrangementRepo arrangementRepo = new ArrangementRepoImpl();
    private final StudentRepo studentRepo = new StudentRepoImpl();
    private final SeatRepo seatRepo = new SeatRepoImpl();

    private final ArrayList<Integer> arrangedStudents = new ArrayList<>();
    private final ArrayList<Integer> arrangedSeats = new ArrayList<>();
    private ArrayList<Integer> sortedStudentIds = new ArrayList<>(); // HTML 배치에 쓰일 실제 36좌석 배치 List

    @Override
    public void execute() {
        DBConnection.get();
        initInfo();
        arrange();
        createHTML(0);
    }

    @Override
    public void initInfo() {
        DefaultUploader.loadInfo();
        SupplementUploader.loadInfo();

        this.setUnused();
    }

    @Override
    public void setUnused() {
        ArrayList<Integer> unusedSeats = seatRepo.findAllNotUsedId();
        for (int i = 0; i < unusedSeats.size(); i++) {
            this.arrangedStudents.add(0);
        }
        this.arrangedSeats.addAll(unusedSeats);
    }

    @Override
    public void arrange() {
        // 자리와 학생 정렬
        this.arrangedStudents.addAll(service.randomByRow(service.arrangeStudent(studentRepo.findAllInProgress()), seatRepo.countUsedByRow()));
        this.arrangedSeats.addAll(service.arrangeSeat(seatRepo.findAllUsed()));

        // 해당 순서대로 arrangement db에 정보 저장
        for (int idx = 0; idx < arrangedStudents.size(); idx++) {
            arrangementRepo.save(this.arrangedSeats.get(idx), this.arrangedStudents.get(idx));
        }
    }

    @Override
    public void createHTML(int id) {
        this.sortedStudentIds.clear(); // controller의 list init
        this.sortedStudentIds = service.sortBySeat(arrangementRepo.findByDate(TODAY)); //controller list에 저장

        ArrayList<StudentDTO> students = studentRepo.findAllInProgress();
        DBConnection.close();
        DetailHTMLMaker.create(students);
        SeatArrangementHTMLMaker.create(sortedStudentIds);
    }
}
