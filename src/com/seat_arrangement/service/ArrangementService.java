package com.seat_arrangement.service;

import com.seat_arrangement.DTO.SeatDTO;
import com.seat_arrangement.DTO.StudentDTO;

import java.util.ArrayList;


public interface ArrangementService {

    ArrayList<Integer> arrangeStudent(ArrayList<Integer> ids);
    ArrayList<Integer> arrangeSeat(ArrayList<Integer> ids);
}
