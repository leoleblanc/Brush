package javis.wearsyncservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.FileInputStream;

/**
 * Created by Me on 4/19/16.
 */
public class childQuestionsOrange extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_questions_orange);

        Bitmap bmp = getImageBitmap(this, "profile","BMP");;
        ImageView img = (ImageView) findViewById(R.id.cropped_final);
        img.setImageDrawable(new RoundedAvatarDrawable(bmp));

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
        //return null;
        //If there is no selected image displays the fox icon
        Bitmap Icon = BitmapFactory.decodeResource(getResources(), R.drawable.foxicon);
        return Icon;
    }

    public void toChildQuestionsBlue(View v) {
        Intent next = new Intent(this, childQuestionsBlue.class);
        startActivity(next);
    }
}
