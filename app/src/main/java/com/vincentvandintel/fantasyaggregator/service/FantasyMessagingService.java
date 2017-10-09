package com.vincentvandintel.fantasyaggregator.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vincentvandintel.fantasyaggregator.MainActivity;
import com.vincentvandintel.fantasyaggregator.R;

// import android.support.v4.app.NotificationCompat;
import static android.R.attr.id;

/**
 * Created by vvand on 10/7/2017.
 */

public class FantasyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message) {
        Log.v("Firebase Message", "Message received!");

        if (message.getNotification() != null) {
            setNotification(message);
        }
    }

    private void setNotification(RemoteMessage message) {
        Log.d("MessageService", "Notification: " + message.getNotification().getBody());
        String notificationTitle = message.getNotification().getTitle();
        String notificationBody = message.getNotification().getBody();

        sendNotification(notificationTitle, notificationBody);
    }

    private void sendNotification(String notificationTitle, String notificationBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = getString(R.string.default_notification_channel_id);
        createChannel(notificationManager, channelId);

        Notification notification = createNotification(notificationTitle, notificationBody, channelId);
        notificationManager.notify(id, notification);
    }

    private Notification createNotification(String notificationTitle, String notificationBody, String channelId) {
        return new Notification.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .build();
    }

    private void createChannel(NotificationManager notificationManager, String channelId) {
        CharSequence channelName = "Fantasy Update";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(notificationChannel);
    }
}

