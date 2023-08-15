package com.example.notifications;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RejectReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the rejection action here without launching an activity
        String message = intent.getStringExtra("message");
        NotificationManager n = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        n.cancel(0);

        Log.d(TAG, "onReceive: 0 called");
        // Perform any desired action on rejection
    }
}
