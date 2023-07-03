package com.seat_arrangement.service;

import com.seat_arrangement.DTO.SeatDTO;
import com.seat_arrangement.DTO.StudentDTO;

import java.util.ArrayList;

public interface ArrangementService {

    ArrayList<StudentDTO> arrangeStudent();
    ArrayList<SeatDTO> arrangeSeat();
}
