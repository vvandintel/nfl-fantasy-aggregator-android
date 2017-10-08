package com.vincentvandintel.fantasyaggregator.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vincentvandintel.fantasyaggregator.MainActivity;
import com.vincentvandintel.fantasyaggregator.R;

/**
 * Created by vvand on 10/7/2017.
 */

public class FantasyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message) {
        Log.v("Firebase Message", "Message received!");

        String notificationTitle = null, notificationBody = null;

        if (message.getNotification() != null) {
            Log.d("MessageService", "Notification: " + message.getNotification().getBody());
            notificationTitle = message.getNotification().getTitle();
            notificationBody = message.getNotification().getBody();
        }

         sendNotification(notificationTitle, notificationBody);
    }

    private void sendNotification(String notificationTitle, String notificationBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), getString(R.string.default_notification_channel_id))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setChannelId(getString(R.string.default_notification_channel_id));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}

