package ru.romanbrazhnikov.simplestopwatch.business;

import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ru.romanbrazhnikov.simplestopwatch.view.StopwatchView;

/**
 * Created by roman on 03.10.17.
 */

public class StopwatchTimeManager {

    private TimeManager TM;
    private StopwatchView mView;
    private long mDurationInMillis;
    private long initSystemElapsedTimeInMillis;

    Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            mDurationInMillis +=
                    Math.round(System.nanoTime() / 1000000000L) * 1000 - initSystemElapsedTimeInMillis;

            if (mView != null) {
                if (mView instanceof AppCompatActivity) {
                    ((AppCompatActivity) mView).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.refreshDisplay(getDurationInMillis());
                        }
                    });
                }
            }
        }
    };

    public StopwatchTimeManager(StopwatchView view) {
        mView = view;
    }

    class TimeManager extends ScheduledThreadPoolExecutor {
        public TimeManager(int corePoolSize) {
            super(corePoolSize);
        }
    }

    public void start(long millis) {
        mDurationInMillis = millis;
        initSystemElapsedTimeInMillis = Math.round(System.nanoTime() / 1000000000L) * 1000;
        TM = new TimeManager(1);
        TM.scheduleAtFixedRate(stopwatchRunnable, 0, 1, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        TM.shutdown();
    }

    public long getDurationInMillis() {
        return mDurationInMillis;
    }

}
