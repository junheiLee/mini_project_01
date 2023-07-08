package com.seat_arrangement.repository.intf;

import com.seat_arrangement.DTO.SeatDTO;

import java.util.ArrayList;

public interface SeatRepo {

    String SELECT_ALL = "select * from seat";
    String SELECT_ALL_NOT_USED_ID = "select seatId from seat where isUsed = false";
    String UPDATE_BY_PROCESSION = "update seat set isUsed = ? where seatRow = ? and seatColumn = ?";
    String DELETE_ALL = "delete from seat";

    ArrayList<SeatDTO> findAllUsed();

    ArrayList<Integer> findAllNotUsedId();

    // 한 줄당 사용하는 좌석 개수 반환 - 줄당 랜덤 시 사용
    ArrayList<Integer> countUsedByRow();

    // 자리 데이터 저장 - init() 에 사용
    void save(int row, int column);

    // 자리 데이터 수정 - init() - modify() 에 사용
    void modify(String modifyColumn, boolean modifyValue, String[] targetColumn, Integer[] targetValue);

    // 발표 시, db data 초기화용
    void deleteAll();
}
