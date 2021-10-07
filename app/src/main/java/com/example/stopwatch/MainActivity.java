package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    int mSeconds =0;
    boolean running;
    boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            mSeconds=savedInstanceState.getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }
        runTimer();
    }


    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", mSeconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);


    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning)
            running=true;
    }

    public void onClickStart(View view)
    {
        running = true;
    }
    public void onClickReset(View view)
    {
        running = false;
        mSeconds =0;
    }

    public void onClickStop(View view)
    {
        running = false;
    }
    private void runTimer() {
        final TextView timeView
                = (TextView)findViewById(
                R.id.time_view);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                int hours = mSeconds / 360000;
//                int minutes = (mSeconds % 360000) / 6000;
//                int secs = (mSeconds % 6000)/100;
//                int ms = mSeconds%100;

                int hours = mSeconds / 3600;
                int minutes = (mSeconds % 3600) / 60;
                int secs = mSeconds % 60;
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%02d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                if(running)
                    mSeconds++;
                timeView.setText(time);



              handler.postDelayed(this,1000);
            }
        });
      //  handler.postDelayed(this::runTimer,100);
    }

}

