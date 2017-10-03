package ru.romanbrazhnikov.simplestopwatch.utils;

/**
 * Created by roman on 03.10.17.
 */

public class DateTimeUtils {
    private static final String sFormat = "%02d:%02d:%02d.%02d";

    public static String DurationInMillisToFormattedTime(long millis) {
        int s = (int) (millis / 1000) % 60 ;
        int m = (int) ((millis / (1000*60)) % 60);
        int h   = (int) ((millis / (1000*60*60)) % 24);
        return String.format(sFormat, h, m, s, millis % 1000 / 10);
    }
}
