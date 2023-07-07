package com.seat_arrangement.view;

// html 태그 모음
public abstract class HTMLTag {
    protected final static String FRE_FRAME = "<!DOCTYPE html>\r\n"
            + "<html>\r\n"
            + "<head>\r\n"
            + "<title> 자리 배치</title>\r\n"
            + "</head>\r\n"
            + "\r\n"
            + "<body>\r\n"
            + "<table>";
    protected final static String POST_FRAME = "</table>\r\n"
            + "</body>\r\n"
            + "</html>";

    protected final static String OPEN_TR = "<tr>";
    protected final static String CLOSE_TR = "</tr>";

    protected final static String OPEN_TD = "<td>";
    protected final static String CLOSE_TD = "</td>";

    protected final static String OPEN_IMAGE = "<img src=./datafile/images/";
    protected final static String CLOSE_IMAGE = ".png /></a>";

}