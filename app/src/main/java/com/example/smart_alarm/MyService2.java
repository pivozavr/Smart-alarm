package com.example.smart_alarm;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService2 extends Service {
    public MyService2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_secure);
        Notification notification = builder.build();
        startForeground(999, notification);
        stopForeground(true);
        return null;
    }
}