package com.seat_arrangement.util.upload;

import java.io.*;
import java.util.ArrayList;

public class FileUploader {
    private static final String STUDENT_DATA_URI = "./src/com/seat_arrangement/util/upload/file/student_info.txt";
    private static final String SEAT_DATA_URI = "./src/com/seat_arrangement/util/upload/file/seat_info.txt";
    private static final String INFO_REGEX = ", ";


    // ArrayList<DTO>에 담아 반환(자리, 수강생)
    public static void loadInfo(){
        loadStudentsInfo(readFile(STUDENT_DATA_URI));
        loadSeatsInfo(readFile(SEAT_DATA_URI));

    }

    private static void loadStudentsInfo(ArrayList<String> list){
        String[] eachInfo;

        for(String each: list) {
            eachInfo = each.split(INFO_REGEX);
//			repository.saveStudents(Integer.parseInt(eachInfo[0]),eachInfo[1],eachInfo[2],Boolean.valueOf(eachInfo[3]));
        }
    }

    // insert into seat (seatRow, seatColumn) values (?, ?);
    private static void loadSeatsInfo(ArrayList<String> list){
        String[] eachInfo;

        for(String each: list) {
            eachInfo = each.split(INFO_REGEX);
//			repository.saveSeats(Integer.parseInt(eachInfo[0]),Integer.parseInt(eachInfo[1]),Integer.parseInt(eachInfo[2]),Boolean.valueOf(eachInfo[3]));
        }
    }

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
