package com.flightinfo.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightDurationUtil {

    public static String getHours(String time1, String time2) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date d1 = format.parse(time1);
            Date d2 = format.parse(time2);
            long diff = d1.getTime() - d2.getTime();
            long diffInMinutes = diff / (60 * 1000) % 60;
            long diffInHours = diff / (60 * 60 * 1000);
            // logger.info("duration format:"+diffInHours+":"+diffInMinutes);
            return diffInHours + ":" + diffInMinutes;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
