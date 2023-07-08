package com.seat_arrangement.service;

import com.seat_arrangement.DTO.SeatDTO;
import com.seat_arrangement.DTO.StudentDTO;

import java.util.ArrayList;
import java.util.Collections;


// 랜덤으로 자리 배치하기
public class RandomArrangementService extends SortingArrangement implements ArrangementService {

    // 학생 list 랜덤으로 섞은 정렬 반환
    @Override
    public ArrayList<Integer> arrangeStudent(ArrayList<StudentDTO> students) {
        ArrayList<Integer> studentIdsInOrders = new ArrayList<>();
        for (StudentDTO student : students) {
            studentIdsInOrders.add(student.getStudentId());
        }
        Collections.shuffle(studentIdsInOrders);
        return studentIdsInOrders;
    }

    // is_used = true 인 자리 id 오름차순으로 정렬 후 반환
    @Override
    public ArrayList<Integer> arrangeSeat(ArrayList<SeatDTO> seats) {
        ArrayList<Integer> seatIdsInOrders = new ArrayList<>();
        for (SeatDTO seat : seats) {
            seatIdsInOrders.add(seat.getSeatId());
        }
        return seatIdsInOrders;
    }


}
