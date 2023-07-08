package com.seat_arrangement.service;


import com.seat_arrangement.DTO.SeatDTO;
import com.seat_arrangement.DTO.StudentDTO;

import java.util.ArrayList;
import java.util.Map;


public interface ArrangementService {

    // 학생 순서 정렬
    ArrayList<Integer> arrangeStudent(ArrayList<StudentDTO> students);

    // 자리 순서 정렬
    ArrayList<Integer> arrangeSeat(ArrayList<SeatDTO> seats);

    ArrayList<Integer> sortBySeat(Map<Integer, Integer> arrangements);
}
