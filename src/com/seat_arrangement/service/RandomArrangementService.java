package com.seat_arrangement.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

// 랜덤으로 자리 배치하기
public class RandomArrangementService extends ArrangementService {

    // 학생 list 랜덤으로 섞은 정렬 반환
    @Override
    public ArrayList<Integer> arrangeStudent(ArrayList<Integer> ids) {
        Collections.shuffle(ids);
        return ids;
    }

    // is_used = true 인 자리 id 오름차순으로 정렬 후 반환
    @Override
    public ArrayList<Integer> arrangeSeat(ArrayList<Integer> ids) {
        return ids;
    }



}
