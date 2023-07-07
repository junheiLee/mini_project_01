package com.seat_arrangement.repository.repoInterface;

import java.util.ArrayList;

public interface SeatRepo {

    ArrayList<Integer> findAllUsedId();

    ArrayList<Integer> findAllNotUsedId();

    // 자리 데이터 저장 - init() 에 사용
    void save(int row, int column);

    // 자리 데이터 수정 - init() - modify() 에 사용
    void modify(String modifyColumn, boolean modifyValue, String[] targetColumn, Integer[] targetValue);

}
