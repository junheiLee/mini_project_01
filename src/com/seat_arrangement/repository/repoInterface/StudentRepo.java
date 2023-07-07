package com.seat_arrangement.repository.repoInterface;

import java.util.ArrayList;

public interface StudentRepo {
    static final String SELECT_ALL_ID = "select studentId from student";
    static final String SELECT_ALL_USED_ID = SELECT_ALL_ID + " where isInProgress = true";
    static final String UPDATE_BY_ID = "update student set ? = ? where ? = ?";
    static final String DELETE_ALL = "delete from student";

    // 모든 현재 수강중인 학생 id 반환
    ArrayList<Integer> findAllUsedId();

    // 학생 데이터 저장 - init() 에 사용
    void save(String name, String mbti);

    // 학생 데이터 수정 - init() - modify() 에 사용
    void modify(String modifyColumn, boolean modifyInfo, String targetColumn, int targetValue);

    // 발표 시, db data 초기화용
    void deleteAll();
}
