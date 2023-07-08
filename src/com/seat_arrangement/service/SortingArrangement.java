package com.seat_arrangement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public abstract class SortingArrangement {

    // key(seatId)기준 value(studentId) 정렬 후 list 반환
    public ArrayList<Integer> sortBySeat(Map<Integer, Integer> arrangements) {
        ArrayList<Integer> sortedStudents = new ArrayList<>();
        ArrayList<Integer> keySet = new ArrayList<>(arrangements.keySet());
        Collections.sort(keySet);

        for (Integer seatIdx : keySet) {
            sortedStudents.add(arrangements.get(seatIdx));
        }
        return sortedStudents;
    }
}
