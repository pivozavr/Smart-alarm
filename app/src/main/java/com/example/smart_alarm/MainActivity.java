package com.example.smart_alarm;

import static android.app.AlarmManager.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    AlarmViget alarmViget;
    FloatingActionButton setAlarm;
    Button setMusic;
    LinearLayout layoutList;
    Calendar calendar;
    AlarmManager alarmManager;
    SimpleDateFormat sdf;
    SharedPreferences spref;
    int alarmMusic;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spref = getSharedPreferences("spref", MODE_PRIVATE);
        alarmMusic = spref.getInt("sp", R.raw.clock);

        layoutList = findViewById(R.id.scrolllayout);
        setAlarm = findViewById(R.id.create_alarm);
        sdf = new SimpleDateFormat("HH:mm");
        setMusic = findViewById(R.id.set_music);

        setMusic.setOnClickListener(view2 -> {
            Intent intent = new Intent(MainActivity.this, MusicPicker.class);
            startActivity(intent);
        });


        setAlarm.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            DateFormat hour = new SimpleDateFormat("HH");
            DateFormat minute = new SimpleDateFormat("mm");
            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(Integer.parseInt(hour.format(cal.getTime())))
                    .setMinute(Integer.parseInt(minute.format(cal.getTime())))
                    .setTitleText("Set time")
                    .setNegativeButtonText("Cancel")
                    .setPositiveButtonText("OK")
                    .build();
            materialTimePicker.addOnPositiveButtonClickListener(view1 -> {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());

                Intent intent2 = new Intent(this, MyBroadcastReceiver.class);
                intent2.putExtra("b", alarmMusic);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        this.getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                intent.putExtra("a", alarmMusic);
                PendingIntent getAlarmActive =  PendingIntent.getActivity(
                        getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),INTERVAL_DAY, pendingIntent);
                alarmManager.setInexactRepeating(RTC_WAKEUP, calendar.getTimeInMillis(),INTERVAL_DAY, getAlarmActive);


                addView(sdf.format(calendar.getTime()));

                Toast.makeText(this, "Alarm set for " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
            });
            materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
        });
        startService(new Intent(this, MyService1.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addView(String time) {
        final View alarmView = getLayoutInflater().inflate(R.layout.alarm_viget, null, false);
        TextView textView = (TextView)alarmView.findViewById(R.id.time_text);
        ImageButton imageButton = (ImageButton) alarmView.findViewById(R.id.remove_button);
        textView.setText(time);

        imageButton.setOnClickListener(view -> {
            removeView(alarmView , time);
            Intent alarmInfoIntent = new Intent(getApplicationContext(), AlarmActivity.class);
            PendingIntent getAlarmInfoPending =  PendingIntent.getActivity(
                    getApplicationContext(), 0, alarmInfoIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            Intent alarmInfoIntent2 = new Intent(this, MyService1.class);
            PendingIntent getAlarmInfoPending2 = PendingIntent.getService(
                    getApplicationContext(), 0, alarmInfoIntent2, PendingIntent.FLAG_CANCEL_CURRENT);

            Intent alarmInfoIntent3 = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent getAlarmInfoPending3 = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, alarmInfoIntent3, PendingIntent.FLAG_CANCEL_CURRENT);
        });

        layoutList.addView(alarmView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        alarmMusic = getIntent().getIntExtra("a", spref.getInt("sp", R.raw.clock));
        if (alarmMusic!=R.raw.clock) {
            spref.edit().putInt("sp", alarmMusic).apply();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        alarmMusic = getIntent().getIntExtra("a", spref.getInt("sp", R.raw.clock));
        if (alarmMusic!=R.raw.clock) {
            spref.edit().putInt("sp", alarmMusic).apply();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        alarmMusic = getIntent().getIntExtra("a", spref.getInt("sp", R.raw.clock));
        if (alarmMusic!=R.raw.clock) {
            spref.edit().putInt("sp", alarmMusic).apply();
        }
    }

    private void removeView(View view, String time){
        layoutList.removeView(view);
        Toast.makeText(this, "Deleted alarm for "+time, Toast.LENGTH_LONG).show();
    }
}