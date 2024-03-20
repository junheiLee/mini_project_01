package main.java.seat_arrangement.util.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class HTMLMaker {
    protected final static String URL = System.getProperty("user.dir") + "/src/main/resources/result/";


    public static void save(String folderName, File file, String content) {
        FileWriter fw = null;
        File directory = new File(URL + folderName);
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (!result) {
                System.out.println("폴더 생성 실패. 파일 쓰기 작업을 중단합니다.");
                return; // 폴더 생성에 실패했으므로 이후 작업 중단
            }
        }
        try {
            fw = new FileWriter(URL + folderName + file);
            fw.write(content);
        } catch (IOException e) {
            System.out.println("File Writer -> " + e.getMessage());
            System.out.println(e);
        } finally {
            try {
//                assert fw != null;
//                fw.close();
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("saveHTML() finally ->" + e.getMessage());
            }
        }
    }
}
