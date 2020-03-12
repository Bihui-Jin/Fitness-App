package com.example.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CalendarActivity extends AppCompatActivity {
    CompactCalendarView compactCalendarView2;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    Set<String> dayList = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        compactCalendarView2 = findViewById(R.id.calendarView);
        compactCalendarView2.setUseThreeLetterAbbreviation(true);
        dispearShow();
        loadDaylist();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        connectTo(actionBar);

    }


    private void connectTo(final ActionBar actionBar){

//        CalendarView calendar = findViewById(R.id.workOutcalendarView);
//        calendar.setMinDate(System.currentTimeMillis() - 1000);

        compactCalendarView2.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                dispearShow();
                int month = dateClicked.getMonth();
                int dtime = dateClicked.getDate();
                String date = "" + (month+1) + "/" + dtime;
                getAvailableExe(date);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormat.format(firstDayOfNewMonth));


            }
        });

    }

    private void addEvent(Long datel){
        Event event = new Event(Color.RED, datel, "");
        compactCalendarView2.addEvent(event);
    }

    private void loadDaylist(){
        SharedPreferences sharedPreferences = getSharedPreferences("ExeDate", MODE_PRIVATE);
        dayList = sharedPreferences.getStringSet(getString(R.string.execriseDay), new HashSet<String>());
        for(String str: dayList){
            Log.v("daylist", str);
            if(!str.equals("1583985600000"))
                addEvent(Long.parseLong(str));
        }
    }

    private void display(String exe, String container){
        Context context = getApplicationContext();
        int resID = getResources().getIdentifier(container, "id", getPackageName());
        Button calenderShowBtn = findViewById(resID);
        calenderShowBtn.setVisibility(View.VISIBLE);
        int imageResource = getResources().getIdentifier(exe, "drawable", context.getPackageName());
        calenderShowBtn.setBackgroundResource(imageResource);
        Log.v("exe", exe);
        if(exe.equals("dumbbellflye")){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDumbellFlye();
                }
            });
        }
    }

    private void getAvailableExe(String date){
        SharedPreferences sharedPreferences = getSharedPreferences("chestDate", MODE_PRIVATE);
        Set<String> result = sharedPreferences.getStringSet(date,new HashSet<String>());
        String container = "calendarShowButton";
        int i = 1;
        for(String str : result){
            if(!str.equals("1583985600000")){
                display(str, container+i);
                i++;
            }
        }
    }

    private void dispearShow(){
        String container = "calendarShowButton";
        for(int i =1; i<=6; i++){
            String s = container+i;
            int resID = getResources().getIdentifier(s, "id", getPackageName());
            Button calenderShowBtn = findViewById(resID);
            calenderShowBtn.setVisibility(View.GONE);
        }
    }

    private void goToDumbellFlye(){
        Intent intent = new Intent(this, dumbellFlyeActivity.class);
        startActivity(intent);
    }


}
