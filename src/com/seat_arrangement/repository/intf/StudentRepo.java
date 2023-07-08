package com.seat_arrangement.repository.intf;

import com.seat_arrangement.DTO.StudentDTO;

import java.util.ArrayList;

public interface StudentRepo {
    String SELECT_ALL = "select * from student";
    String SELECT_ALL_ID = "select studentId from student";
    String SELECT_ALL_USED_ID = SELECT_ALL_ID + " where isInProgress = true";
    String UPDATE_BY_ID = "update student set ? = ? where ? = ?";
    String DELETE_ALL = "delete from student";

    // 수강중인 학생 DTO 반환
    ArrayList<StudentDTO> findAll();

    // 모든 현재 수강중인 학생 id 반환
    ArrayList<Integer> findAllUsedId();

    // 학생 데이터 저장 - init() 에 사용
    void save(String name, String mbti);

    // 학생 데이터 수정 - init() - modify() 에 사용
    void modify(String modifyColumn, boolean modifyInfo, String targetColumn, int targetValue);

    // 발표 시, db data 초기화용
    void deleteAll();
}
