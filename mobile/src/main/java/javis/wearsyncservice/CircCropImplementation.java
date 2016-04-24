package javis.wearsyncservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileInputStream;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo class showing how to call the dialog for the photo selection,
 * crop the photo and store it as a bitmap on the mobile internal storage
 */
public class CircCropImplementation extends Activity {

    public static int BITMAP_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circ_crop_implementation);

        //ProgressWheel pw = new ProgressWheel(this, myAttributes);
        //ProgressWheel pw = (ProgressWheel) findViewById(R.id.pw_spinner);
        //pw.startSpinning();

        /*RelativeLayout rel = (RelativeLayout)findViewById(R.id.daisy);
        LineChartView chart = new LineChartView(this);
        //RelativeLayout.LayoutParams params =
        //        new RelativeLayout.LayoutParams(50,50); // figure out how to dynamically change the size
        rel.addView(chart);
*/

        Intent intent = new Intent(this, CircCropActivity.class);

        startActivityForResult(intent, CircCropImplementation.BITMAP_REQUEST_CODE);

        /*Bitmap bmp = getImageBitmap(this, "profile","BMP");
        ImageView img = (ImageView) findViewById(R.id.cropped_final);
        img.setImageDrawable(new RoundedAvatarDrawable(bmp));*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bmp = getImageBitmap(this, "profile","BMP");
        ImageView img = (ImageView) findViewById(R.id.cropped_final);
        img.setImageDrawable(new RoundedAvatarDrawable(bmp));
        /*switch(requestCode) {
            case (MY_CHILD_ACTIVITY) : {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                }
                break;
            }
        }*/
    }

    public Bitmap getImageBitmap(Context context,String name,String extension){
        name=name+"."+extension;
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e){
        }
        return null;
    }

}
