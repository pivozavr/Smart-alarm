package com.example.smart_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    int alarmMusic;
    AudioManager audioManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        alarmMusic = intent.getIntExtra("b", R.raw.clock);
        mp=MediaPlayer.create(context, alarmMusic);
        mp.setLooping(true);
        mp.start();
    }

}
