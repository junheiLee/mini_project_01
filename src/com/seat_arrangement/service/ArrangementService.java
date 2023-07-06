package com.seat_arrangement.service;


import java.util.ArrayList;
import java.util.Map;


public interface ArrangementService {

    ArrayList<Integer> arrangeStudent(ArrayList<Integer> ids);
    ArrayList<Integer> arrangeSeat(ArrayList<Integer> ids);
    ArrayList<Integer> sortBySeat(Map<Integer, Integer> arrangements);
}
