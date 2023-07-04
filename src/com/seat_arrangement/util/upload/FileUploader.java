package com.seat_arrangement.util.upload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


// 파일에서 수강생 목록 업데이트 담당
public class FileUploader <T> {

    private static final String STUDENT_DATA_URI = "./file/student_info.txt";
    private static final String SEAT_DATA_URI = "./file/seat_info.txt";


    // 기본 정보 db에 load
    public static void loadInfo(){
        loadStudentsInfo(readFile(STUDENT_DATA_URI));
        loadSeatsInfo(readFile(SEAT_DATA_URI));
    }

    // 한 줄의 학생 정보 split()으로 분리해 db에 저장
    private static void loadStudentsInfo(ArrayList<String> list){
        String[] eachInfo;

        for(String each: list) {
            eachInfo = each.split(", ");
//			repository.saveStudents(Integer.parseInt(eachInfo[0]),eachInfo[1],eachInfo[2],Boolean.valueOf(eachInfo[3]));
        }
    }

    // 한 줄의 좌석 정보 split()으로 분리해 db에 저장
    private static void loadSeatsInfo(ArrayList<String> list){
        String[] eachInfo;

        for(String each: list) {
            eachInfo = each.split(", ");
//			repository.saveSeats(Integer.parseInt(eachInfo[0]),Integer.parseInt(eachInfo[1]),Integer.parseInt(eachInfo[2]),Boolean.valueOf(eachInfo[3]));
        }
    }

    //text 파일 한 줄씩 담아 반환
    @SuppressWarnings("finally")
    private static ArrayList<String> readFile(String uri) {
        ArrayList<String> list = new ArrayList<>();

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(uri);
            br = new BufferedReader(fr);

            String temp = "";
            while((temp = br.readLine()) != null) {
                list.add(temp);
            }
            br.close();
            fr.close();

        } catch(FileNotFoundException e) {
            System.out.println("File Reader ===>" + e.getMessage());
        }catch (IOException e) {
            System.out.println("File Reader close ===>" + e.getMessage());
        }finally {
            return list;
        }
    }

}
