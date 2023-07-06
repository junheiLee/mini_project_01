package com.seat_arrangement.service;


import java.util.ArrayList;


public interface ArrangementService {

    ArrayList<Integer> arrangeStudent(ArrayList<Integer> ids);
    ArrayList<Integer> arrangeSeat(ArrayList<Integer> ids);
}
