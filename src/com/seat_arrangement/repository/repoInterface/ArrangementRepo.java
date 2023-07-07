package com.seat_arrangement.repository.repoInterface;

import java.sql.Date;
import java.util.Map;

public interface ArrangementRepo {

    // 해당 날짜 자리 배치 정보 <자리id, 학생id> 가져오기
    Map<Integer, Integer> findByDate(Date date);

    // 해당 날짜의 자리 배치 정보 저장
    void save(int seatId, int studentId);
}
