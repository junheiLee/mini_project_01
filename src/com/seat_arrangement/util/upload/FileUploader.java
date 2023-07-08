package com.seat_arrangement.util.upload;

import com.seat_arrangement.repository.SeatRepoImpl;
import com.seat_arrangement.repository.StudentRepoImpl;
import com.seat_arrangement.repository.intf.SeatRepo;
import com.seat_arrangement.repository.intf.StudentRepo;
import com.seat_arrangement.util.ArrangeInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// FileReader, BufferedReader
public abstract class FileUploader extends ArrangeInfo {
    protected static final String LOCATION = System.getProperty("user.dir") + "/src/com/seat_arrangement/util/datafile/default/";
    protected static final String INFO_REGEX = ",";

    protected static final StudentRepo studentRepo = new StudentRepoImpl();
    protected static final SeatRepo seatRepo = new SeatRepoImpl();

    protected static ArrayList<String> readFile(String uri) {
        ArrayList<String> list = new ArrayList<>();

        FileReader fr;
        BufferedReader br;

        try {
            fr = new FileReader(LOCATION + uri);
            br = new BufferedReader(fr);

            String temp;
            while ((temp = br.readLine()) != null) {
                list.add(temp);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Reader ===>" + e.getMessage());
        } catch (IOException e) {
            System.out.println("File Reader close ===>" + e.getMessage());
        }

        return list;
    }

}
