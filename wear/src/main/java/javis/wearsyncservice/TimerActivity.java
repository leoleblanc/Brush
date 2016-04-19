package javis.wearsyncservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimerActivity extends WearableActivity {

    private TextView mTextView;
    private TextView mAcce;
    private MyReceiver myReceiver;
    private float speed;
    private RelativeLayout mRelativeLayout;
    final int defaultBrushingTime = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTextView = (TextView) findViewById(R.id.timer);
        mAcce = (TextView) findViewById(R.id.acce);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.back);


        // Set BroadcastReceiver
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACCELEROMETER");
        registerReceiver(myReceiver, intentFilter);



        // Start AccelService
        Intent accIntent = new Intent(this, AccelService.class);
        Log.d("TimerActivity", "timer starting acc");
        startService(accIntent);



        // Start the timer
        CountDownTimer timer = new CountDownTimer(defaultBrushingTime * 1000, 1000) {

            long last_stop = defaultBrushingTime * 1000;
            boolean doCount = false;

            public void onTick(long millisUntilFinished) {
                mTextView.setText(millisToString(millisUntilFinished));
                if (speed < 25) {
                    if (doCount) {
                        if (last_stop - millisUntilFinished > 5000) {
                            mRelativeLayout.setBackgroundResource(R.drawable.timer_r);
                        }
                    } else {
                        doCount = true;
                        last_stop = millisUntilFinished;
                    }
                } else {
                    mRelativeLayout.setBackgroundResource(R.drawable.timer);
                    doCount = false;
                }
            }


            private String millisToString(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                int minutes = seconds / 60;
                seconds = seconds - minutes * 60;
                return Integer.toString(minutes) + ":" + Integer.toString(seconds);
            }




            public void onFinish() {
                Intent i = new Intent(TimerActivity.this, ResultActivity.class);
                startActivity(i);
            }
        };

        timer.start();



    }


    @Override
    protected void onPause() {
        super.onPause();
    }



    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {

            Log.d("TimerActivity", "Received something");
            String acc = arg1.getStringExtra("ACCE");
            speed = Float.parseFloat(acc);
            Log.d("TimerActivity", acc);
            if (mAcce != null){
                mAcce.setText(acc);
            } else {
                Log.d("TimerActivity", "mACCE is null");
            }

        }

    }
}
