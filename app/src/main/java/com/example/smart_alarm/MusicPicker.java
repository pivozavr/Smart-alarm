package com.example.smart_alarm;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class MusicPicker extends AppCompatActivity{
    RadioGroup radioGroup;
    MediaPlayer player;
    Button acceptButton;

    int alarmMusic;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_picker);


        alarmMusic = R.raw.clock;

        radioGroup = findViewById(R.id.radio_group);
        acceptButton = findViewById(R.id.accept_music);
        player = MediaPlayer.create(this, R.raw.clock);

        radioGroup.setOnCheckedChangeListener((view, checkedId) -> {
            //переделать в switch
                if(checkedId==R.id.classic) {
                    player.stop();
                    player = MediaPlayer.create(this, R.raw.clock);
                    player.setLooping(true);
                    player.start();
                    alarmMusic = R.raw.clock;
                }
                else if(checkedId==R.id.hotline) {
                    player.stop();
                    player = MediaPlayer.create(this, R.raw.hotline);
                    player.setLooping(true);
                    player.start();
                    alarmMusic = R.raw.hotline;
                }
                else if(checkedId==R.id.oppo) {
                    player.stop();
                    player = MediaPlayer.create(this, R.raw.oppo);
                    player.setLooping(true);
                    player.start();
                    alarmMusic = R.raw.oppo;
                }
                else if(checkedId==R.id.xiaomi) {
                    player.stop();
                    player = MediaPlayer.create(this, R.raw.xiaomi);
                    player.setLooping(true);
                    player.start();
                    alarmMusic = R.raw.xiaomi;
                }

        });
            acceptButton.setOnClickListener(view1 -> {
                player.stop();
                Toast.makeText(this, "Default ringtone set", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("a", alarmMusic);
                startActivity(intent);
            });
    }

}
