package com.seat_arrangement.service;

import com.seat_arrangement.DTO.SeatDTO;
import com.seat_arrangement.DTO.StudentDTO;

import java.util.ArrayList;

// 랜덤으로 자리 배치하기
public class RandomArrangementService implements ArrangementService{

    // 학생 list 랜덤으로 섞은 정렬 반환
    @Override
    public ArrayList<StudentDTO> arrangeStudent(){
        return null; //Collections.shuffled(repository.findAllStudent());
    }

    // is_used = true 인 자리 id 오름차순으로 정렬 후 반환
    @Override
    public ArrayList<SeatDTO> arrangeSeat(){
        return null; //repository.findAllSeat();
    }

}
