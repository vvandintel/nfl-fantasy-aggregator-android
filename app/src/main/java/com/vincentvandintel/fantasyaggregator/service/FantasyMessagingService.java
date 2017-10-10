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

// import android.support.v4.app.NotificationCompat;

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
        NotificationCompat.Builder notification = createNotification(notificationTitle, notificationBody, pendingIntent);
        notificationManager.notify(1, notification.build());
    }

    private NotificationCompat.Builder createNotification(String notificationTitle, String notificationBody, PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(getApplicationContext())
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.football)
            .setContentTitle(notificationTitle)
            .setContentIntent(pendingIntent)
            .setContentText(notificationBody);
    }
}

