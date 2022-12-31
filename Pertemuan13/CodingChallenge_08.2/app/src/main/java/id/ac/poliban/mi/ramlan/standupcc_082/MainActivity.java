package id.ac.poliban.mi.ramlan.standupcc_082;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null)getSupportActionBar().setTitle("Stand Up!");

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void next_alarm_button(View view) {
        AlarmManager.AlarmClockInfo info = alarmManager.getNextAlarmClock();
        String message;
        if (info != null){
            Date nextAlarm = new Date(info.getTriggerTime());
            String newDate = DateFormat.getTimeInstance().format(nextAlarm);

            message = "The alarm is set for " + newDate + ".";
        }
        else{
            message = "There is no alarm scheduled.";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}