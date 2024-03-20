package main.java.seat_arrangement;

import main.java.seat_arrangement.controller.MainController;
import main.java.seat_arrangement.controller.SeatArrangementController;

public class SeatArrangement {

    static SeatArrangementController controller = new MainController();

    public static void main(String[] args) {
        controller.execute();
    }
}