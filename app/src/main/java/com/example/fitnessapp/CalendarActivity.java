package com.example.fitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
        disappearShow();
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
                disappearShow();
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
//            Log.v("daylist", str);
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
        if(exe.equals(getString(R.string.inclineDumbbellFlye))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.inclineDumbbellFlye));
                }
            });
        }
        if(exe.equals(getString(R.string.benchPress))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.benchPress));
                }
            });
        }
        if(exe.equals(getString(R.string.cableCrossover))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.cableCrossover));
                }
            });
        }
        if(exe.equals(getString(R.string.dumbbellFlye))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.dumbbellFlye));
                }
            });
        }
        if(exe.equals(getString(R.string.chestPressMachine))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.chestPressMachine));
                }
            });
        }
        if(exe.equals(getString(R.string.pushup))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.pushup));
                }
            });
        }
        if(exe.equals(getString(R.string.bentOverBarbellRow))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.bentOverBarbellRow));
                }
            });
        }
        if(exe.equals(getString(R.string.dumbBellSingleArmRow))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.dumbBellSingleArmRow));
                }
            });
        }
        if(exe.equals(getString(R.string.latPulldown))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.latPulldown));
                }
            });
        }
        if(exe.equals(getString(R.string.pullup))){
            calenderShowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetailExe(getString(R.string.pullup));
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
            Log.v("exe", str);
            if(!str.equals("1583985600000")){
                display(str, container+i);
                i++;
            }
        }
        int j = 1;
        SharedPreferences sharedPreferences1 = getSharedPreferences("backDate", MODE_PRIVATE);
        Set<String> result1 = sharedPreferences1.getStringSet(date,new HashSet<String>());
        for(String str: result1){
            display(str, container+j);
            j++;
        }
    }


    private void disappearShow(){
        String container = "calendarShowButton";
        for(int i =1; i<=6; i++){
            String s = container+i;
            int resID = getResources().getIdentifier(s, "id", getPackageName());
            Button calenderShowBtn = findViewById(resID);
            calenderShowBtn.setVisibility(View.GONE);
        }
    }

    private void goToDetailExe(String exe){
        String text = getString(this.getResources().getIdentifier(exe+"Des", "string", getPackageName()));
        String src = getString(this.getResources().getIdentifier(exe+"Video", "string", getPackageName()));
        detailExeActivity.setTextDescription(text);
        detailExeActivity.setVideoSrc(src);
        Intent intent = new Intent(this, detailExeActivity.class);
        startActivity(intent);
    }




}
