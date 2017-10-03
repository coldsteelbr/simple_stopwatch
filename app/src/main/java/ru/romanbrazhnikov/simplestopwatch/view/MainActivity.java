package ru.romanbrazhnikov.simplestopwatch.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.romanbrazhnikov.simplestopwatch.R;
import ru.romanbrazhnikov.simplestopwatch.model.Stopwatch;
import ru.romanbrazhnikov.simplestopwatch.utils.DateTimeUtils;

public class MainActivity extends AppCompatActivity
implements StopwatchView{

    private Stopwatch mStopwatch;

    // Widgets
    private Button bStartStop;
    private Button bReset;
    private TextView tvDisplay;

    // Listeners
    private StartClickListener mStartClickListener = new StartClickListener();
    private StopClickListener mStopClickListener = new StopClickListener();
    private ResetClickListener mResetClickListener = new ResetClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStopwatch = new Stopwatch(this);

        initWidgets();
    }

    private void initWidgets() {
        tvDisplay = findViewById(R.id.tv_display);

        bStartStop = findViewById(R.id.b_startStop);
        bStartStop.setOnClickListener(mStartClickListener);

        bReset = findViewById(R.id.b_reset);
        bReset.setOnClickListener(mResetClickListener);
    }

    @Override
    public void refreshDisplay(long durationInMillis){
        tvDisplay.setText(DateTimeUtils.DurationInMillisToFormattedTime(durationInMillis));
    }

    class StartClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            bStartStop.setEnabled(false);
            mStopwatch.start();
            bStartStop.setOnClickListener(mStopClickListener);
            bStartStop.setText("Stop");
            bReset.setVisibility(View.VISIBLE);
            bStartStop.setEnabled(true);
        }
    }

    class StopClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            bStartStop.setEnabled(false);
            mStopwatch.stop();
            bStartStop.setOnClickListener(mStartClickListener);
            bStartStop.setText("Start");
            bStartStop.setEnabled(true);
        }
    }

    class ResetClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mStopwatch.reset();
            bReset.setVisibility(View.GONE);
        }
    }
}
