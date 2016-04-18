package javis.wearsyncservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.StatObj;

public class SendMsgActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);

        final EditText msg = (EditText)findViewById(R.id.msg);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mWifiScanReceiver, new IntentFilter(Constant.MY_INTENT_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
    }

    public void SendMsgToWatch(View w)
    /**
     * Called onClick of the send mesage button. Starts the service to send the
     * message in the editText to the watch.
     */
    //TODO: What happens if the editText is empty? Prompt the user to reenter
    //TODO: Get a screenshot without the 'enter message' editText
    {
        Intent msgIntent = new Intent(this, SendWatchMessageIntentService.class);
        EditText msg = (EditText)findViewById(R.id.msg);
        String text = msg.getText().toString();
        Log.d("Bazooka", text);
        msgIntent.putExtra(SendWatchMessageIntentService.INPUT_EXTRA, text);
        startService(msgIntent);

        Intent toSignUp = new Intent(this, signUpBrush.class);
        startActivity(toSignUp);
    }

    private void write_to_phone(String parcel)
    /**
     * Decodes the parcel and writes the received parcel to a
     * file on the phone for retrieval later.
     */
    {
        //TODO:Fill in saving to internal storage bit here
        StatObj obj = new StatObj();
        obj.parseParcel(parcel);
        Log.d("BAZOOKA", obj.getDate());
        Log.d("BAZOOKA", String.valueOf(obj.getCircularScore()));
        Log.d("BAZOOKA", String.valueOf(obj.getHorizontalScore()));
        Log.d("BAZOOKA", String.valueOf(obj.getVerticalScore()));
        Log.d("BAZOOKA", String.valueOf(obj.getTotalScore()));
    }



    private BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals(Constant.MY_INTENT_FILTER)) {
                //textView.setText(intent.getStringExtra(Constant.PHONE_TO_WATCH_TEXT));
                write_to_phone(intent.getStringExtra(Constant.PHONE_TO_WATCH_TEXT));
            }
        }
    };

    private void unregisterReceiver() {
        try {
            if (mWifiScanReceiver != null) {
                unregisterReceiver(mWifiScanReceiver);
            }
        } catch (IllegalArgumentException e) {
            mWifiScanReceiver = null;
        }
    }
}