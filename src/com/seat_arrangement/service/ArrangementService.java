package com.seat_arrangement.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


public abstract class ArrangementService {

    abstract ArrayList<Integer> arrangeStudent(ArrayList<Integer> ids);

    abstract ArrayList<Integer> arrangeSeat(ArrayList<Integer> ids);

    public ArrayList<Integer> sortBySeat(Map<Integer, Integer> arrangements) {
        ArrayList<Integer> sortedStudents = new ArrayList<>();
        ArrayList<Integer> keySet = new ArrayList<>(arrangements.keySet());
        Collections.sort(keySet);

        for (Integer seatIdx : keySet) {
            sortedStudents.add(arrangements.get(seatIdx));
        }
        System.out.println(sortedStudents.size());
        return sortedStudents;
    }
}
