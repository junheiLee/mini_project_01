package com.seat_arrangement.util.upload;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// FileReader, BufferedReader
public abstract class FileUploader {
    protected static final String LOCATION = "./src/com/seat_arrangement/util/upload/datafile";
    protected static final String INFO_REGEX = ", ";

    @SuppressWarnings("finally")
    protected static ArrayList<String> readFile(String uri) {
        ArrayList<String> list = new ArrayList<>();

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(LOCATION + uri);
            br = new BufferedReader(fr);

            String temp = "";
            while ((temp = br.readLine()) != null) {
                list.add(temp);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Reader ===>" + e.getMessage());
        } catch (IOException e) {
            System.out.println("File Reader close ===>" + e.getMessage());
        } finally {
            return list;
        }
    }

}
