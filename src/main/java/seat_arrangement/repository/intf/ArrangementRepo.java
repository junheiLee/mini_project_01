package main.java.seat_arrangement.repository.intf;

import java.sql.Date;
import java.util.Map;

public interface ArrangementRepo {

    Map<Integer, Integer> findById(int idx);

    // 해당 날짜 자리 배치 정보 <seatId, studentId> 가져오기
    Map<Integer, Integer> findByDate(Date date);

    // 해당 날짜의 자리 배치 정보 저장
    void save(int seatId, int studentId);
}
