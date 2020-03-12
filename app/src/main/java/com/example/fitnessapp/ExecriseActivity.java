package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class ExecriseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execrise);
        setButton();
        getAvailableExe();
        changeChest();


    }

    private void setButton(){
        final Context context = getApplicationContext();
        final Button addToCalendarBtn = findViewById(R.id.addToCalendar);
        Button chestAddBtn = findViewById(R.id.chestAddButton);
        chestAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        addToCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choice = getUserChoice();
                if(choice!=0){
                    goCalendar();
                }
                else {
                    Toast.makeText(context, "Choose at least one set of exercise", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // read value from the check box
    private int getUserChoice(){
        int m = 0;
        CheckBox chestCheckBtn = findViewById(R.id.chestCheckBox);
        CheckBox backCheckBtn = findViewById(R.id.backCheckBox);
        if(chestCheckBtn.isChecked()) m++;
        if(backCheckBtn.isChecked()) m+=2;
        return m;
    }

    private void goCalendar(){
        Intent intent = new Intent(this, AddCalendarActivity.class);
        startActivity(intent);
    }

    private void getAvailableExe(){
        SharedPreferences sharedPreferences = getSharedPreferences("chest", MODE_PRIVATE);
        boolean benchPress = sharedPreferences.getBoolean(getString(R.string.benchPress),true);

        boolean inclineDumbbellFlye = sharedPreferences.getBoolean(getString(R.string.inclineDumbbellFlye), true);
        boolean cableCrossover = sharedPreferences.getBoolean(getString(R.string.cableCrossover), true);
        boolean pushup = sharedPreferences.getBoolean(getString(R.string.pushup),false);
        boolean chestPressMachine = sharedPreferences.getBoolean(getString(R.string.chestPressMachine),true);
        boolean dumbbellFlye = sharedPreferences.getBoolean(getString(R.string.dumbbellFlye), true);
        setChest1(benchPress);
        setChest2(inclineDumbbellFlye);
        setChest3(cableCrossover);
        setChest4(pushup);
        setChest5(chestPressMachine);
        setChest6(dumbbellFlye);
    }

    private void setChest1(boolean status){
        Button chestBtn1 = findViewById(R.id.chestButton1);
        if(!status){
            chestBtn1.setVisibility(View.GONE);
        }
        else{
            chestBtn1.setVisibility(View.VISIBLE);
        }
        chestBtn1.setBackgroundResource(R.drawable.benchpress);
    }

    private void setChest2(boolean status){
        Button chestBtn = findViewById(R.id.chestButton2);
        if(!status){
            chestBtn.setVisibility(View.GONE);
        }
        else{
            chestBtn.setVisibility(View.VISIBLE);
        }
        chestBtn.setBackgroundResource(R.drawable.inclinedumbbellflye);

    }

    private void setChest3(boolean status){
        Button chestBtn = findViewById(R.id.chestButton3);
        if(!status){
            chestBtn.setVisibility(View.GONE);
        }
        else{
            chestBtn.setVisibility(View.VISIBLE);
        }
        chestBtn.setBackgroundResource(R.drawable.cablecrossover);

    }

    private void setChest4(boolean status){
        Button chestBtn = findViewById(R.id.chestButton4);
        if(!status){
            chestBtn.setVisibility(View.GONE);
        }
        else{
            chestBtn.setVisibility(View.VISIBLE);
        }
        chestBtn.setBackgroundResource(R.drawable.pushup);

    }

    private void setChest5(boolean status){
        Button chestBtn = findViewById(R.id.chestButton5);
        if(!status){
            chestBtn.setVisibility(View.GONE);
        }
        else{
            chestBtn.setVisibility(View.VISIBLE);
        }
        chestBtn.setBackgroundResource(R.drawable.chestpressmachine);

    }
    private void setChest6(boolean status){
        Button chestBtn = findViewById(R.id.chestButton6);
        if(!status){
            chestBtn.setVisibility(View.GONE);
        }
        else{
            chestBtn.setVisibility(View.VISIBLE);
        }
        chestBtn.setBackgroundResource(R.drawable.dumbbellflye);
    }

    private void changeChest(){
        Button chestAddBtn = findViewById(R.id.chestAddButton);
        chestAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChestPage();

            }
        });

    }

    private void goChestPage(){
        Intent intent = new Intent(this, ChestHomeActivity.class);
        startActivity(intent);
    }

    // when back from other activity
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        getAvailableExe();
        //Refresh your stuff here
//        finish();
//        startActivity(getIntent());
    }

}
