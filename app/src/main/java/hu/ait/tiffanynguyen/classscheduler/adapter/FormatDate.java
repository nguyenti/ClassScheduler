package hu.ait.tiffanynguyen.classscheduler.adapter;

/**
 * Created by tiffanynguyen on 11/11/14.
 */
public class FormatDate {

    static String format(int start, int end) {
        return Integer.toString(start/100) + (start % 100) + ":" + (end / 100) + (end % 100);
    }

    public static int unformat(String time) {
        if (time.length() == 5)
            return Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3,5));
        return -1;
    }
}
