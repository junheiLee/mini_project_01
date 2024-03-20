package test.java.seat_arrangement.controller;

import main.java.seat_arrangement.DTO.StudentDTO;
import main.java.seat_arrangement.controller.SeatArrangementController;
import main.java.seat_arrangement.repository.connection.DBConnection;
import main.java.seat_arrangement.repository.intf.SeatRepo;
import main.java.seat_arrangement.repository.intf.StudentRepo;
import main.java.seat_arrangement.service.RandomArrangementService;
import main.java.seat_arrangement.util.upload.DefaultUploader;
import main.java.seat_arrangement.util.upload.SupplementUploader;
import main.java.seat_arrangement.util.view.DetailHTMLMaker;
import main.java.seat_arrangement.util.view.SeatArrangementHTMLMaker;
import test.java.seat_arrangement.repository.testRepo.ArrangementRepoTest;
import test.java.seat_arrangement.repository.testRepo.SeatRepoTest;
import test.java.seat_arrangement.repository.testRepo.StudentRepoTest;

import java.util.ArrayList;

import static main.java.seat_arrangement.util.ArrangeInfo.EMPTY_SEAT;

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
