package net.jevgeni.androidsamples.timers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class TimerSamples {

    public void createRunnable() {
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable", "second passed...");
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(run);
    }

    public void createCountDownTimer() {
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisecondsUntilDone) {
                Log.i("CountDownTimer seconds left", String.valueOf(millisecondsUntilDone / 1000));

            }

            @Override
            public void onFinish() {
                Log.i("CountDownTimer", "finished.");
            }
        }.start();
    }
}
