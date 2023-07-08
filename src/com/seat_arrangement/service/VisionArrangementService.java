package com.seat_arrangement.service;

import com.seat_arrangement.DTO.SeatDTO;
import com.seat_arrangement.DTO.StudentDTO;

import java.util.*;


public class VisionArrangementService extends SortingArrangement implements ArrangementService {

    @Override
    public ArrayList<Integer> arrangeStudent(ArrayList<StudentDTO> students) {
        ArrayList<Integer> studentIdsInOrders = new ArrayList<>();
        return sortByVision(visionByIdToMap(students));
    }

    // is_used = true 인 자리 id 오름차순으로 정렬 후 반환
    @Override
    public ArrayList<Integer> arrangeSeat(ArrayList<SeatDTO> seats) {
        ArrayList<Integer> seatIdsInOrder = new ArrayList<>();
        for (SeatDTO seat : seats) {
            seatIdsInOrder.add(seat.getSeatId());
        }
        return seatIdsInOrder;
    }

    private ArrayList<Integer> sortByVision(Map<Integer, Float> visionById) {
        ArrayList<Integer>  studentIdsInOrder = new ArrayList<>();
        visionById.put(-1, 99.0f); // 같은 시력인 사람끼리 shuffle 하기 위한 작업

        List<Map.Entry<Integer, Float>> entryList = new LinkedList<>(visionById.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        ArrayList<Integer> sameVision = new ArrayList<>();

        for (int i = 0; i < visionById.size()-1; i++) {
            sameVision.add(entryList.get(i).getKey());

            if(entryList.get(i).getValue().floatValue() != entryList.get(i+1).getValue().floatValue()){
                Collections.shuffle(sameVision);
                studentIdsInOrder.addAll(sameVision);
                sameVision.clear();
            }
        }
        return studentIdsInOrder;
    }

    private Map<Integer, Float> visionByIdToMap(ArrayList<StudentDTO> students) {
        Map<Integer, Float> visionById = new HashMap<>();
        for (StudentDTO student : students) {
            visionById.put(student.getStudentId(), student.getVision());
        }
        return visionById;
    }


}
