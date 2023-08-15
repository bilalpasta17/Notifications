package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button btn_1;
    final String CHANNEL_ID = "FB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNotification();

            }
        });
    }


    public void makeNotification() {
        // Define the channel ID for the notification channel

        String channel_id = "Channel_id_Notification";

        // Create a NotificationCompat.Builder instance to build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id);

        // Set the small icon, title, content text, auto-cancel behavior, and priority of the notification
        builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("New Notification")
                .setContentText("Some Text")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create an intent to launch the NotificationActivity when the notification is clicked
        Intent intent = new Intent(MainActivity.this,
                NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message", "some value");

        // Create a PendingIntent to specify the action to be taken when the notification is clicked
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setFullScreenIntent(pendingIntent, true);
        builder.setContentIntent(pendingIntent);

        // Create an intent for the "Accept" action button
        Intent acceptIntent = new Intent(MainActivity.this, acceptActivity.class);
        acceptIntent.putExtra("message", "Accept");

        PendingIntent acceptPendingIntent = PendingIntent.getActivity(MainActivity.this,
                0, acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create an intent for the "Reject" action button
        Intent rejectIntent = new Intent(MainActivity.this, RejectReciever.class);
        rejectIntent.putExtra("message", "Reject");

        PendingIntent rejectPendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                0, rejectIntent, PendingIntent.FLAG_IMMUTABLE);


        // Create the "Accept" and "Reject" action buttons
        NotificationCompat.Action acceptAction = new NotificationCompat.Action.Builder(
                R.drawable.accept, "Accept", acceptPendingIntent)
                .build();

        NotificationCompat.Action rejectAction = new NotificationCompat.Action.Builder(
                R.drawable.reject, "Reject", rejectPendingIntent)
                .build();

        // Add the action buttons to the notification builder
        builder.addAction(acceptAction);
        builder.addAction(rejectAction);

        // Get the NotificationManager to manage and display the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);

        // Generate a unique notification ID
        int notificationId = generateNotificationId();

        // Display the notification by calling notify() on the NotificationManager
        notificationManager.notify(notificationId, builder.build());
//
//        // To dismiss the notification programmatically, call cancel() on the NotificationManager
//        // after a certain duration or on a specific event
//        // For example, to dismiss the notification after 5 seconds, you can use a Handler
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notificationManager.cancel(notificationId);
//            }
//        }, 1000); // 5 seconds


    // Create a notification channel (for Android 8.0 and above)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)

    {
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channel_id);
        if (notificationChannel == null) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            notificationChannel = new NotificationChannel(channel_id, "some description", importance);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    // Display the notification by calling notify() on the NotificationManager
        notificationManager.notify(0,builder.build());

}

    private int generateNotificationId() {
        // Generate a unique notification ID using a random number or other logic
        // Return the generated ID
        return 0;
    }
}




