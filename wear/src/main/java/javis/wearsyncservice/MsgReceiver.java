package javis.wearsyncservice;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.widget.Toast;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Serah on 4/19/2016.
 */
public class MsgReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BAZOOKA", "Receiving the encouragement message");
        Toast.makeText(context, "Notification is shown", Toast.LENGTH_SHORT).show();
        showNotification(context);
    }

    public Notification buildNotification(Context mContext) {


        Bitmap bitmap = Bitmap.createBitmap(320,320, Bitmap.Config.ARGB_8888);
        int dull_purple = Color.parseColor("#895A99");
        bitmap.eraseColor(dull_purple);

        Notification.WearableExtender extender = new Notification.WearableExtender();
        extender.setBackground(bitmap);

        Intent playbackIntent = new Intent(mContext, InstructionActivity.class);

        PendingIntent playbackPendingIntent = PendingIntent.getActivity(
                mContext, 0, playbackIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(mContext)
                .setContentTitle("Brush!")
                .setContentText("It's time to brush!")
                .setSmallIcon(R.drawable.notif_icon_fox)
                .extend(extender)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(playbackPendingIntent);

        Notification notif = builder.build();
        notif.defaults = Notification.DEFAULT_ALL;

        return notif;
    }

    @TargetApi(23)
    public void showNotification(Context mContext) {
        Notification notification = buildNotification(mContext);

        NotificationManager manager = (NotificationManager)
                mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.visibility=Notification.VISIBILITY_PUBLIC;
        Log.d("BAZOOKA", notification.toString());
        manager.notify(1, notification);
    }

}
