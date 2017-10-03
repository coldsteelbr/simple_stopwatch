package ru.romanbrazhnikov.simplestopwatch.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by roman on 03.10.17.
 */

public class DateTimeUtils {
    private static final String sFormat = "%02d:%02d:%02d.%02d";

    public static String DurationInMillisToFormattedTime(long millis) {
        String hms = String.format(sFormat,
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                millis - TimeUnit.MILLISECONDS.toSeconds(millis));
        return hms;
    }
}
