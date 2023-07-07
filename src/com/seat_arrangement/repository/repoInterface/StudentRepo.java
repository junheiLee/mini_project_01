package com.seat_arrangement.repository.repoInterface;

import java.util.ArrayList;

public interface StudentRepo {

    // 모든 현재 수강중인 학생 id 반환
    ArrayList<Integer> findAllUsedId();

    // 학생 데이터 저장 - init() 에 사용
    void save(String name, String mbti);

    // 학생 데이터 수정 - init() - modify() 에 사용
    void modify(String modifyColumn, boolean modifyInfo, String targetColumn, int targetValue);

}
