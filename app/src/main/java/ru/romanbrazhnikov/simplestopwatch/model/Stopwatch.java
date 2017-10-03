package ru.romanbrazhnikov.simplestopwatch.model;

import ru.romanbrazhnikov.simplestopwatch.business.StopwatchTimeManager;
import ru.romanbrazhnikov.simplestopwatch.utils.DateTimeUtils;
import ru.romanbrazhnikov.simplestopwatch.view.StopwatchView;

/**
 * Created by roman on 03.10.17.
 */

public class Stopwatch {

    private long durationInMillis = 0;
    private StopwatchTimeManager mStopwatchTimeManager;
    private StopwatchView mView;

    public Stopwatch(StopwatchView view) {
        mView = view;
        mStopwatchTimeManager = new StopwatchTimeManager(mView);
    }

    public void start() {
        mStopwatchTimeManager.start(durationInMillis);
    }

    public void stop() {
        mStopwatchTimeManager.stop();
    }

    public void reset() {
        stop();
        durationInMillis = 0;
    }

    public String getTime() {
        return DateTimeUtils.DurationInMillisToFormattedTime(durationInMillis);
    }
}
