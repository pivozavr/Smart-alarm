package com.example.smart_alarm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    MediaPlayer player2;
    MediaPlayer player1;
    Equation_v2 equation = new Equation_v2();
    Button stopAlarm;
    EditText edittext1;
    EditText edittext2;
    TextView text;
    TextView textAlarm;
    LinearLayout layout;
    AudioManager audioManager;
    int alarmMusic;
    long[] pattern = {0, 2000};

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        stopAlarm = findViewById(R.id.alarm_off);
        textAlarm = findViewById(R.id.equationText);
        text = findViewById(R.id.alarm_text);
        layout = findViewById(R.id.layout);
        edittext1 = findViewById(R.id.text1);
        edittext2 = findViewById(R.id.text2);

        alarmMusic = getIntent().getIntExtra("a", R.raw.clock);

        player1 = MediaPlayer.create(this, alarmMusic);
        player2 = MediaPlayer.create(this, R.raw.error);
        player1.setLooping(true);
        player1.start();
        String[] answers = equation.equation();
        textAlarm.setText(answers[2]);
        stopAlarm.setOnClickListener(view -> {
            if (((edittext1.getText().toString().equals(answers[0]))) & (edittext2.getText().toString().equals(answers[1]))
                    | ((edittext2.getText().toString().equals(answers[0])) & (edittext1.getText().toString().equals(answers[1])))) {
                text.setText("Alarm stopped");
                textAlarm.setText("Have a good day!");
                player1.stop();
            }
            else{
                player2.start();
                int color = textAlarm.getCurrentTextColor();
                text.setText("Try again!");
            }

        });

        startService(new Intent(this, MyService1.class));
    }

}
