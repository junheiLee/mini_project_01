package com.seat_arrangement.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class HTMLMaker {
    protected final static String URL = System.getProperty("user.dir") + "/src/com/seat_arrangement/util/result/";

    public static void save(String folderName, File file, String content) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(URL + folderName + file);
            fw.write(content);
        } catch (IOException e) {
            System.out.println("File Writer -> " + e.getMessage());
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("saveHTML() finally ->" + e.getMessage());
            }
        }
    }
}
