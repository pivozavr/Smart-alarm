package com.example.smart_alarm;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService1 extends Service {
    public MyService1() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_secure);
        Notification notification = builder.build();
        startForeground(777, notification);
        Intent hideIntent = new Intent(this, MyService2.class);
        startService(hideIntent);
        return null;
    }

}