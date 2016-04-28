package javis.wearsyncservice;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImageHelper;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.FileOutputStream;

import com.theartofdev.edmodo.cropper.CropImageHelper;
import com.theartofdev.edmodo.cropper.CropImageView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class CircCropActivity extends Activity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circ_crop);

        CropImageView cropImageView = (CropImageView)findViewById(R.id.cropImageView);
        cropImageView.setImageUriAsync(uri);
    }*/
    private CropImageView mCropImageView;
    private Uri mCropImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circ_crop);
        mCropImageView = (CropImageView)  findViewById(R.id.CropImageView);
        mCropImageView.setAspectRatio(1, 1); //FOR CIRCLE SHAPE
        mCropImageView.setFixedAspectRatio(true); //FOR CIRCLE SHAPE
        mCropImageView.setCropShape(CropImageView.CropShape.OVAL);

        //ADDED
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.x = -100;
        params.height = 1050;
        params.width = 700;
        //params.y = -50;
        this.getWindow().setAttributes(params);
        //ADDED
    }

    /**
     * On load image button click, start pick  image chooser activity.
     */
    public void onLoadImageClick(View view) {
        //TODO: Remove the backgound blank person image
        CropImageView img = (CropImageView)findViewById(R.id.CropImageView);
        img.setBackgroundColor(getResources().getColor(R.color.transparent));
        startActivityForResult(CropImageHelper.getPickImageChooserIntent(this), 200);
    //Second argument used to be 200
    }

    /**
     * Crop the image and set it back to the  cropping view.
     */
    public void onCropImageClick(View view) {
        //Bitmap cropped =  mCropImageView.getCroppedImage(500, 500);
        Bitmap cropped =  mCropImageView.getCroppedOvalImage();
        if (cropped != null) {
            //mCropImageView.setImageBitmap(cropped);
            /*ImageView img = (ImageView) findViewById(R.id.Ouput);
            img.setImageDrawable(new RoundedAvatarDrawable(cropped));*/
            //TODO: Figure out how to store the bitmap to the phone
            saveImage(this, cropped, "profile","BMP");
            finish();
        }
    }

    public void saveImage(Context context, Bitmap b,String name,String extension){
        /**
         * Saves the bitmap cropped image to the phone
         */
        name=name+"."+extension;
        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int  requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri =  CropImageHelper.getPickImageResultUri(this, data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    CropImageHelper.isUriRequiresPermissions(this, imageUri)) {

                // request permissions and handle the result in on RequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }

            if (!requirePermissions) {
                mCropImageView.setImageUriAsync(imageUri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //mCropImageView.setImageUriAsync(mCropImageUri);
            mCropImageView.getCroppedImageAsync(CropImageView.CropShape.OVAL, 40, 40); //used to be 400,400
            Log.d("BAZOOKA", "Inside onrequestpermissionresult");
        } else {
            Toast.makeText(this, "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }
}
