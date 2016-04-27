package javis.wearsyncservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.StatObj;

public class CustomDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        //ADDED
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.x = -100;
        params.height = 1000;
        params.width = 700;
        //params.y = -50;
        this.getWindow().setAttributes(params);
        //ADDED

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
    {
        Intent msgIntent = new Intent(this, SendWatchMessageIntentService.class);
        EditText msg = (EditText)findViewById(R.id.msg);
        String text = msg.getText().toString();
        Log.d("BAZOOKA", text);
        msgIntent.putExtra(SendWatchMessageIntentService.INPUT_EXTRA, "MSG;" + text);
        startService(msgIntent);
        finish(); //closes the dialogue box
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

