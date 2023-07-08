package com.seat_arrangement.controller;

public interface SeatArrangementController {
    //실행
    void execute();

    // 학생, 자리 정보 초기화
    void initInfo();

    // 결원과 사용하지 않는 자리 매핑해 총 결과값으로 변경 (모든 배치 방법에 필수)
    // 결원 id = 0 취급
    void setUnused();

    // 배치하기
    void arrange();

    void createHTML(int id);

}
