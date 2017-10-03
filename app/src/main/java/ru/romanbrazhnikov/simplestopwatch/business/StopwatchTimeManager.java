package ru.romanbrazhnikov.simplestopwatch.business;

import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ru.romanbrazhnikov.simplestopwatch.view.StopwatchView;

/**
 * Created by roman on 03.10.17.
 */

public class StopwatchTimeManager {

    private TimeManager TM;
    private StopwatchView mView;
    private long mCurrentDurationInMillis;
    private long mDurationInMillis;
    private long initSystemElapsedTimeInMillis;
    private ScheduledFuture<?> task;

    Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            mCurrentDurationInMillis =
                    Math.round(System.nanoTime() / 1000000) - initSystemElapsedTimeInMillis;

            runViewCommand();
        }
    };

    public StopwatchTimeManager(StopwatchView view) {
        mView = view;
        TM = new TimeManager(1);
    }

    class TimeManager extends ScheduledThreadPoolExecutor {
        public TimeManager(int corePoolSize) {
            super(corePoolSize);
        }
    }

    public void start(long millis) {
        mDurationInMillis = millis;
        mCurrentDurationInMillis = 0;
        initSystemElapsedTimeInMillis = Math.round(System.nanoTime() / 1000000);

        task = TM.scheduleAtFixedRate(stopwatchRunnable, 0, 10, TimeUnit.MILLISECONDS);
    }

    public void stop() {

        task.cancel(false);
        mDurationInMillis += mCurrentDurationInMillis;
        mCurrentDurationInMillis = 0;
    }

    public void reset() {
        stop();
        mDurationInMillis = 0;
        mCurrentDurationInMillis = 0;

    }

    public long getTotalDurationInMillis() {
        return mDurationInMillis + mCurrentDurationInMillis;
    }

    private Runnable viewRunnable = new Runnable() {
        @Override
        public void run() {
            mView.refreshDisplay(getTotalDurationInMillis());
        }
    };

    public void runViewCommand() {
        if (mView instanceof AppCompatActivity) {
            ((AppCompatActivity) mView).runOnUiThread(viewRunnable);
        }
    }
}
