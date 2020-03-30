package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChestHomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_home);
        getSetChestDefault();
        userChoice();


    }

    private void getSetChestDefault(){
        SharedPreferences sharedPreferences = getSharedPreferences("chest", MODE_PRIVATE);
        boolean benchPress = sharedPreferences.getBoolean(getString(R.string.benchPress),true);
        boolean inclineDumbbellFlye = sharedPreferences.getBoolean(getString(R.string.inclineDumbbellFlye), true);
        boolean cableCrossover = sharedPreferences.getBoolean(getString(R.string.cableCrossover), true);
        boolean pushup = sharedPreferences.getBoolean(getString(R.string.pushup),false);
        boolean chestPressMachine = sharedPreferences.getBoolean(getString(R.string.chestPressMachine),true);
        boolean dumbbellFlye = sharedPreferences.getBoolean(getString(R.string.dumbbellFlye), true);
        setChestDefault(benchPress, inclineDumbbellFlye, cableCrossover, pushup, chestPressMachine, dumbbellFlye);


    }

    private void setChestDefault(boolean benchPress, boolean inclineDumbbellFlye, boolean cableCrossover,
                                 boolean pushup, boolean chestPressMachine, boolean dumbbellFlye){
        CheckBox checkBox1 = findViewById(R.id.benchPressCheckBox);
        CheckBox checkBox2 = findViewById(R.id.inclinedDumbbellFlyeCheckBox);
        CheckBox checkBox3 = findViewById(R.id.cableCrossoverCheckBox);
        CheckBox checkBox4 = findViewById(R.id.pushupCheckBox);
        CheckBox checkBox5 = findViewById(R.id.chestPressMachineCheckBox);
        CheckBox checkBox6 = findViewById(R.id.dumbbellFlyeCheckBox);
        if(benchPress) checkBox1.setChecked(true);
        if(inclineDumbbellFlye) checkBox2.setChecked(true);
        if(cableCrossover) checkBox3.setChecked(true);
        if(pushup) checkBox4.setChecked(true);
        if(chestPressMachine) checkBox5.setChecked(true);
        if(dumbbellFlye) checkBox6.setChecked(true);
    }



    private void userChoice(){
        final CheckBox checkBox1 = findViewById(R.id.benchPressCheckBox);
        final CheckBox checkBox2 = findViewById(R.id.inclinedDumbbellFlyeCheckBox);
        final CheckBox checkBox3 = findViewById(R.id.cableCrossoverCheckBox);
        final CheckBox checkBox4 = findViewById(R.id.pushupCheckBox);
        final CheckBox checkBox5 = findViewById(R.id.chestPressMachineCheckBox);
        final CheckBox checkBox6 = findViewById(R.id.dumbbellFlyeCheckBox);
        final Context context = getApplicationContext();
        SharedPreferences sharedPreferences = getSharedPreferences("chest", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when click it makes it checked
                if(checkBox1.isChecked()){
                    editor.putBoolean(getString(R.string.benchPress), true);
                    editor.apply();
                }
                else {
                    editor.putBoolean(getString(R.string.benchPress), false);
                    editor.apply();
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked()){
                    editor.putBoolean(getString(R.string.inclineDumbbellFlye), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.inclineDumbbellFlye), false);
                    editor.apply();
                }
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox3.isChecked()){
                    editor.putBoolean(getString(R.string.cableCrossover), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.cableCrossover), false);
                    editor.apply();
                }
            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox4.isChecked()){
                    editor.putBoolean(getString(R.string.pushup), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.pushup), false);
                    editor.apply();
                }
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox5.isChecked()){
                    editor.putBoolean(getString(R.string.chestPressMachine), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.chestPressMachine), false);
                    editor.apply();
                }
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox6.isChecked()){
                    editor.putBoolean(getString(R.string.dumbbellFlye), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.dumbbellFlye), false);
                    editor.apply();
                }
            }
        });




    }

//    @Override
//    public void onBackPressed(){
//
//
//        Intent intent = new Intent(this, ExecriseActivity.class);
//        startActivity(intent);
//    }
}
