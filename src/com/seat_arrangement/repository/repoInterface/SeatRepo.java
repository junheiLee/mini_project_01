package com.seat_arrangement.repository.repoInterface;

import java.util.ArrayList;

public interface SeatRepo {

    static final String SELECT_ALL_ID = "select seatId from seat";
    static final String SELECT_ALL_USED_ID = SELECT_ALL_ID + " where isUsed = true";
    static final String SELECT_ALL_NOT_USED_ID = SELECT_ALL_ID + " where isUsed = false";
    static final String UPDATE_BY_PROCESSION = "update seat set isUsed = ? where seatRow = ? and seatColumn = ?";
    static final String DELETE_ALL = "delete from seat";

    ArrayList<Integer> findAllUsedId();

    ArrayList<Integer> findAllNotUsedId();

    // 자리 데이터 저장 - init() 에 사용
    void save(int row, int column);

    // 자리 데이터 수정 - init() - modify() 에 사용
    void modify(String modifyColumn, boolean modifyValue, String[] targetColumn, Integer[] targetValue);

    // 발표 시, db data 초기화용
    void deleteAll();
}
