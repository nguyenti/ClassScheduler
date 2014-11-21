package hu.ait.tiffanynguyen.classscheduler.adapter;

import java.text.SimpleDateFormat;

/**
 * Created by tiffanynguyen on 11/11/14.
 */
public class FormatDate {

    public static String format(int hour, int minute) {
        return (new SimpleDateFormat("HH:mm")).format((minute * 60 + hour * 3600) * 1000);
    }
    public static String format(int date) {
        return (new SimpleDateFormat("HH:mm")).format(date);
    }

    public static int unformat(String time) {
        if (time.length() == 5)
            return (Integer.parseInt(time.substring(0, 2)) * 3600
                    + Integer.parseInt(time.substring(3,5)) * 60) * 1000;
        return -1;
    }
}
