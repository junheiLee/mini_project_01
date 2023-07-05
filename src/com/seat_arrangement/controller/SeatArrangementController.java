package com.seat_arrangement.controller;


import com.seat_arrangement.util.upload.DefaultUploader;
import com.seat_arrangement.util.upload.SeatDefaultModifier;
import com.seat_arrangement.util.upload.StudentDefaultModifier;

public class SeatArrangementController {

    //전체 코드 실행
    public void run(){
        initInfo();

    }

    // 학생, 자리 정보 초기화
    private void initInfo(){
        DefaultUploader.loadInfo();

        StudentDefaultModifier.modifyInfo();
        SeatDefaultModifier.modifyInfo();
    }
}
