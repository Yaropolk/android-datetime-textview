package ru.ircode.datetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Date fDate;
    private Calendar fCalendar;

    private TextView fClock;
    private Timer fTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fDate = new Date();
        fCalendar = Calendar.getInstance();

        fClock = (TextView)findViewById(R.id.clock);

        fTimer = new Timer();

        fTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                clock();
            }
        }, 0, 1000);
    }

    private void clock() {
        this.runOnUiThread(Clock_Tick);
    }

    private Runnable Clock_Tick = new Runnable() {
        public void run() {

            fDate.setTime(System.currentTimeMillis());
            fCalendar.setTime(fDate);

            int hour_of_day = fCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = fCalendar.get(Calendar.MINUTE);
            int second = fCalendar.get(Calendar.SECOND);

            String date = String.format("%2d:%02d:%02d", hour_of_day, minute, second);

            fClock.setText(date);

            // Определение AM или PM

            // int hour = fCalendar.get(Calendar.HOUR);
            // boolean is_pm = Calendar.PM == fCalendar.get(Calendar.AM_PM);

            // String date = String.format("%2d:%02d:%02d %s",
            //         hour, minute, second, is_pm ? "p.m." : "a.m.");
        }
    };
}
