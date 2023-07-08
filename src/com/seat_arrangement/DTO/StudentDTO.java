package com.seat_arrangement.DTO;

public class StudentDTO {
    private int studentId;
    private String name;
    private String mbti;
    private float vision;
    private boolean isSmoker;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public float getVision() {
        return vision;
    }

    public void setVision(float vision) {
        this.vision = vision;
    }

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }
}
