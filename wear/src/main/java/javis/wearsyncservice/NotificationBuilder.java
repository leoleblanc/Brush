package javis.wearsyncservice;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;

public class NotificationBuilder {
    private final Context mContext;

    public NotificationBuilder(Context context) {
        mContext = context;
    }

    public Notification buildNotification() {
        Notification.Builder builder = new Notification.Builder(mContext)
                .setContentTitle("Please Work!!")
                .setContentText("Please!!")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent playbackIntent = new Intent(mContext, HelloWorldActivity.class);

        PendingIntent playbackPendingIntent = PendingIntent.getActivity(
                mContext, 0, playbackIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.WearableExtender extender = new Notification.WearableExtender()
                .setBackground(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setCustomSizePreset(Notification.WearableExtender.SIZE_MEDIUM)
                .setDisplayIntent(playbackPendingIntent);

        builder.extend(extender);

        return builder.build();
    }

    @TargetApi(23)
    public void showNotification() {
        Notification notification = buildNotification();

        NotificationManager manager = (NotificationManager)
                mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.visibility=Notification.VISIBILITY_PUBLIC;
        Log.d("BAZOOKA", notification.toString());
        manager.notify(1, notification);
    }
}