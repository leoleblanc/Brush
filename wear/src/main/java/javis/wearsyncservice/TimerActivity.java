package javis.wearsyncservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimerActivity extends WearableActivity {

    private TextView mTimerTextView;
    private RelativeLayout mCircularView;
    private RelativeLayout mHorizontalView;
    private RelativeLayout mVerticalView;
    private RelativeLayout mSideView;
    private TextView mSideViewText;
    private RelativeLayout mClean;


    private TextView mAcce;
    private MyReceiver myReceiver;
    private float speed;
    private RelativeLayout mRelativeLayout;
    final int defaultBrushingTime = 149;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTimerTextView = (TextView) findViewById(R.id.timer);
        mCircularView = (RelativeLayout) findViewById(R.id.circular);
        mHorizontalView = (RelativeLayout) findViewById(R.id.horizontal);
        mVerticalView =  (RelativeLayout) findViewById(R.id.vertical);
        mSideView = (RelativeLayout) findViewById(R.id.brushside);
        mSideViewText = (TextView) findViewById(R.id.brushside_text);
        mClean = (RelativeLayout) findViewById(R.id.clean);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.back);

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(500);


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

            int timertime = defaultBrushingTime * 1000;
            long last_stop = defaultBrushingTime * 1000;
            boolean doCount = false;

            public void onTick(long millisUntilFinished) {
                mTimerTextView.setText(millisToString(millisUntilFinished, timertime*3));
                if (speed < 25) {
                    if (doCount) {
                        if (last_stop - millisUntilFinished > 5000) {
                            mRelativeLayout.setBackgroundColor(0xffeb5757);
                        }
                    } else {
                        doCount = true;
                        last_stop = millisUntilFinished;
                    }
                } else {
                    mRelativeLayout.setBackgroundColor(0xff84e96d);
                    doCount = false;
                }

                if (millisUntilFinished > 146000) {
                    mSideViewText.setText("top left");
                    mTimerTextView.setVisibility(View.INVISIBLE);
                    mSideView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 143000) {
                    mSideView.setVisibility(View.INVISIBLE);
                    mCircularView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 113000) {
                    mTimerTextView.setText(millisToString(millisUntilFinished, -3*6-5));
                    mCircularView.setVisibility(View.INVISIBLE);
                    mTimerTextView.setVisibility(View.VISIBLE);

                } else if (millisUntilFinished > 110000) {
                    mSideViewText.setText("top right");
                    mTimerTextView.setVisibility(View.INVISIBLE);
                    mSideView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 107000) {
                    mSideView.setVisibility(View.INVISIBLE);
                    mVerticalView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 77000) {
                    mTimerTextView.setText(millisToString(millisUntilFinished, -2*6-5));
                    mVerticalView.setVisibility(View.INVISIBLE);
                    mTimerTextView.setVisibility(View.VISIBLE);

                } else if (millisUntilFinished > 74000) {
                    mSideViewText.setText("bottom left");
                    mTimerTextView.setVisibility(View.INVISIBLE);
                    mSideView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 71000) {
                    mSideView.setVisibility(View.INVISIBLE);
                    mHorizontalView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 41000) {
                    mTimerTextView.setText(millisToString(millisUntilFinished, -1*6-5));
                    mHorizontalView.setVisibility(View.INVISIBLE);
                    mTimerTextView.setVisibility(View.VISIBLE);

                } else if (millisUntilFinished > 38000) {
                    mSideViewText.setText("bottom right");
                    mTimerTextView.setVisibility(View.INVISIBLE);
                    mSideView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 35000) {
                    mSideView.setVisibility(View.INVISIBLE);
                    mCircularView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 5000) {
                    mTimerTextView.setText(millisToString(millisUntilFinished, -5));
                    mCircularView.setVisibility(View.INVISIBLE);
                    mTimerTextView.setVisibility(View.VISIBLE);
                } else if (millisUntilFinished > 4000){
                    mClean.setVisibility(View.VISIBLE);
                    vibrator.vibrate(500);
                }
            }

            private String millisToString(long millisUntilFinished, int adjustment) {
                int seconds = (int) millisUntilFinished / 1000 + adjustment;
                int minutes = seconds / 60;
//                return Integer.toString(seconds) + " raw:" + Integer.toString((int) millisUntilFinished / 1000);

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
