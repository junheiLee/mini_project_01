package com.seat_arrangement.controller;

import com.seat_arrangement.DTO.StudentDTO;
import com.seat_arrangement.repository.connection.DBConnection;
import com.seat_arrangement.repository.intf.SeatRepo;
import com.seat_arrangement.repository.intf.StudentRepo;
import com.seat_arrangement.repository.testRepo.ArrangementRepoTest;
import com.seat_arrangement.repository.testRepo.SeatRepoTest;
import com.seat_arrangement.repository.testRepo.StudentRepoTest;
import com.seat_arrangement.service.RandomArrangementService;
import com.seat_arrangement.util.upload.DefaultUploader;
import com.seat_arrangement.util.upload.SupplementUploader;
import com.seat_arrangement.view.DetailHTMLMaker;
import com.seat_arrangement.view.SeatArrangementHTMLMaker;

import java.util.ArrayList;

import static com.seat_arrangement.util.ArrangeInfo.EMPTY_SEAT;

public class TestController implements SeatArrangementController {

    private final RandomArrangementService service = new RandomArrangementService();

    private final ArrangementRepoTest arrangementRepo = new ArrangementRepoTest();
    private final StudentRepo studentRepo = new StudentRepoTest();
    private final SeatRepo seatRepo = new SeatRepoTest();

    private final ArrayList<Integer> arrangedStudents = new ArrayList<>();
    private final ArrayList<Integer> arrangedSeats = new ArrayList<>();
    private ArrayList<Integer> sortedStudentIds = new ArrayList<>(); // HTML 배치에 쓰일 실제 36좌석 배치 List

    @Override
    public void execute() {
        int id = 1;
        DBConnection.get();
        initInfo();
        arrange();
        DBConnection.close();
        createHTML(id);
    }

    @Override
    public void initInfo() {
        studentRepo.deleteAll();
        seatRepo.deleteAll();
        DefaultUploader.loadInfo();

        SupplementUploader.loadInfo();
        this.setUnused();
    }

    @Override
    public void arrange() {
        this.arrangedStudents.addAll(service.arrangeStudent(studentRepo.findAllInProgress()));
        this.arrangedSeats.addAll(service.arrangeSeat(seatRepo.findAllUsed()));

        for (int idx = 0; idx < arrangedStudents.size(); idx++) {
            arrangementRepo.save(this.arrangedSeats.get(idx), this.arrangedStudents.get(idx));
        }
    }

    @Override
    public void setUnused() {
        ArrayList<Integer> unusedSeats = seatRepo.findAllNotUsedId();
        for (int i = 0; i < unusedSeats.size(); i++) {
            this.arrangedStudents.add(EMPTY_SEAT);
        }
        this.arrangedSeats.addAll(unusedSeats);
    }

    @Override
    public void createHTML(int id) {
        this.sortedStudentIds.clear(); // controller의 list init
        this.sortedStudentIds = service.sortBySeat(arrangementRepo.findById(id)); //controller list에 저장

        ArrayList<StudentDTO> students = studentRepo.findAllInProgress();
        DetailHTMLMaker.create(students);
        SeatArrangementHTMLMaker.create(sortedStudentIds);
    }
}
