package id.ac.poliban.mi.ramlan.notifyme_081;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button button_notify;
    private Button button_cancel;
    private Button button_update;
    private static final String ACTION_CANCEL_NOTIFICATION =
            BuildConfig.APPLICATION_ID + ".ACTION_CANCEL_NOTIFICATION";
    private static final String ACTION_UPDATE_NOTIFICATION =
            "id.ac.poliban.mi.ramlan.notifyme_081.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    private NotificationManager mNotifyManager;
    private NotificationReceiver mReceiver = new NotificationReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("NotifyMe!_025");

        button_notify = findViewById(R.id.notify);
        button_update = findViewById(R.id.update);
        button_cancel = findViewById(R.id.cancel);
        setNotificationButtonState(true, false, false);
        createNotificationChannel();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UPDATE_NOTIFICATION);
        intentFilter.addAction(ACTION_CANCEL_NOTIFICATION);
        registerReceiver(mReceiver, intentFilter);
    }

    // create notification channel if android version is Oreo and above
    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, getString(R.string.mascot_notification), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getString(R.string.notify_from_mascot));
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    // helper method for building notifications
    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent cancelIntent = new Intent(ACTION_CANCEL_NOTIFICATION);
        PendingIntent notificationCancelPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, cancelIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);
        notifyBuilder.setContentTitle(getString(R.string.youve_been_notified))
                .setContentText(getString(R.string.notification_text))
                .setContentIntent(notificationPendingIntent)
                .setDeleteIntent(notificationCancelPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_android);
        return notifyBuilder;
    }

//    public void sendNotification() {    }

    // Onclick method for Notify button
    public void notifyButton(View view) {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.addAction(R.drawable.ic_update, getString(R.string.update_notification), updatePendingIntent);
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, true, true);
    }

    // onclick method for Update button
    public void updateButton(View view) {
        updateNotification();
    }

    // onclick method for Cancel button
    public void cancelButton(View view) {
        mNotifyManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true, false, false);
    }

    // helper method to enable/disable buttons
    void setNotificationButtonState(Boolean isNotifyEnabled, Boolean isUpdateEnabled, Boolean isCancelEnabled) {
        button_notify.setEnabled(isNotifyEnabled);
        button_update.setEnabled(isUpdateEnabled);
        button_cancel.setEnabled(isCancelEnabled);
    }

    //helper class for updating notification
    public void updateNotification(){
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle(getString(R.string.notification_updated)));
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        setNotificationButtonState(false, false, true);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    // broadcast receiver helper class
    public class NotificationReceiver extends BroadcastReceiver{

        public NotificationReceiver() {
            // empty constructor
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            if (intentAction != null) {
                switch (intentAction){
                    case ACTION_UPDATE_NOTIFICATION: updateNotification(); break;
                    case ACTION_CANCEL_NOTIFICATION: setNotificationButtonState(true, false, false);
                    default: break;
                }
            }
        }
    }
}
