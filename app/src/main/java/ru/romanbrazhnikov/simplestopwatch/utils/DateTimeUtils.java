package ru.romanbrazhnikov.simplestopwatch.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by roman on 03.10.17.
 */

public class DateTimeUtils {
    private static final String sFormat = "%02d:%02d:%02d.%02d";

    public static String DurationInMillisToFormattedTime(long millis) {
        return String.format(sFormat, 0, 0, millis / 1000, millis % 1000);
    }
}
