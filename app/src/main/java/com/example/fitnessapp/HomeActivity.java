package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.example.fitnessapp.Controller.NutritionPage;

public class HomeActivity extends AppCompatActivity {
    int waterLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setButtons();
        restoreWater();
    }



    private void setButtons(){
        Button execriseBtn = findViewById(R.id.execriseButton);
        execriseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goExecrisePage();
            }
        });

        Button nutritionBtn = findViewById(R.id.nutritionButton);
        nutritionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNutritionPage();
            }
        });

        Button calendarBtn = findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCalendarPage();
            }
        });

        Button profileBtn = findViewById(R.id.profileButton);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goProfilePage();
            }
        });

        pressAdd();
        pressMinus();


    }

    private void goExecrisePage(){
        Intent intent = new Intent(this, ExecriseActivity.class);
        startActivity(intent);
    }

    private void goNutritionPage(){
        Intent intent = new Intent(this, NutritionPage.class);
        startActivity(intent);
    }

    private void goCalendarPage(){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void goProfilePage(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void addWater(){
        Button imageView = findViewById(R.id.waterImageView);
        if(waterLevel == 1){
            imageView.setBackgroundResource(R.drawable.ic_water_30dp);
            waterLevel++;
        }
        else if(waterLevel == 2){
            imageView.setBackgroundResource(R.drawable.ic_water_50dp);
            waterLevel++;
        }
        else if(waterLevel == 3){
            imageView.setBackgroundResource(R.drawable.ic_water_60dp);
            waterLevel++;
        }
        else if(waterLevel == 4){
            imageView.setBackgroundResource(R.drawable.ic_water_80dp);
            waterLevel++;
        }
        else if(waterLevel == 5){
            imageView.setBackgroundResource(R.drawable.ic_water_90dp);
            waterLevel++;
        }
        else if(waterLevel == 6){
            imageView.setBackgroundResource(R.drawable.ic_water_100dp);
        }
    }

    private void minusWater(){
        Button imageView = findViewById(R.id.waterImageView);
        if(waterLevel == 1){
            imageView.setBackgroundResource(R.drawable.ic_water_20dp);
        }
        else if(waterLevel == 2){
            imageView.setBackgroundResource(R.drawable.ic_water_30dp);
            waterLevel--;
        }
        else if(waterLevel == 3){
            imageView.setBackgroundResource(R.drawable.ic_water_50dp);
            waterLevel--;
        }
        else if(waterLevel == 4){
            imageView.setBackgroundResource(R.drawable.ic_water_60dp);
            waterLevel--;
        }
        else if(waterLevel == 5){
            imageView.setBackgroundResource(R.drawable.ic_water_80dp);
            waterLevel--;
        }
        else if(waterLevel == 6){
            imageView.setBackgroundResource(R.drawable.ic_water_90dp);
            waterLevel--;
        }

    }

    private void pressAdd(){
        Button addBtn = findViewById(R.id.addWaterButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWater();
                storeWater();
            }
        });
    }

    private void pressMinus(){
        Button minusBtn = findViewById(R.id.minusWaterButton);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusWater();
                storeWater();
            }
        });
    }

    private void storeWater(){
        SharedPreferences sharedPreferences = getSharedPreferences("waterLevel", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("water", waterLevel);
    }

    private void restoreWater(){
        SharedPreferences sharedPreferences = getSharedPreferences("waterLvel", MODE_PRIVATE);
        waterLevel = sharedPreferences.getInt("water", 1);
        Button imageView = findViewById(R.id.waterImageView);
        if(waterLevel == 1){
            imageView.setBackgroundResource(R.drawable.ic_water_20dp);
        }
        else if(waterLevel == 2){
            imageView.setBackgroundResource(R.drawable.ic_water_30dp);
        }
        else if(waterLevel == 3){
            imageView.setBackgroundResource(R.drawable.ic_water_50dp);
        }
        else if(waterLevel == 4){
            imageView.setBackgroundResource(R.drawable.ic_water_60dp);
        }
        else if(waterLevel == 5){
            imageView.setBackgroundResource(R.drawable.ic_water_80dp);
        }
        else if(waterLevel == 6){
            imageView.setBackgroundResource(R.drawable.ic_water_100dp);
        }
    }
}
