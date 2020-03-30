package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class BackHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_home);
        getSetBackDefault();
        userChoice();

    }

    private void getSetBackDefault(){
        SharedPreferences sharedPreferences = getSharedPreferences("back", MODE_PRIVATE);
        boolean pullup = sharedPreferences.getBoolean(getString(R.string.pullup),true);
        boolean latpulldown = sharedPreferences.getBoolean(getString(R.string.latPulldown), true);
        boolean dumbbellsinglearmrow = sharedPreferences.getBoolean(getString(R.string.dumbBellSingleArmRow), true);
        boolean bentoverbarbellrow = sharedPreferences.getBoolean(getString(R.string.bentOverBarbellRow),false);

        setChestDefault(pullup, latpulldown, dumbbellsinglearmrow, bentoverbarbellrow);


    }

    private void setChestDefault(boolean pullup, boolean latpulldown, boolean dumbbellsinglearmrow,
                                 boolean bentoverbarbellrow){
        CheckBox checkBox1 = findViewById(R.id.pullupCheckBox);
        CheckBox checkBox2 = findViewById(R.id.latPulldownCheckBox);
        CheckBox checkBox3 = findViewById(R.id.dumbbellSingleArmRowCheckBox);
        CheckBox checkBox4 = findViewById(R.id.bentoverBarbellRowCheckBox);
        if(pullup) checkBox1.setChecked(true);
        if(latpulldown) checkBox2.setChecked(true);
        if(dumbbellsinglearmrow) checkBox3.setChecked(true);
        if(bentoverbarbellrow) checkBox4.setChecked(true);
    }



    private void userChoice(){
        final CheckBox checkBox1 = findViewById(R.id.pullupCheckBox);
        final CheckBox checkBox2 = findViewById(R.id.latPulldownCheckBox);
        final CheckBox checkBox3 = findViewById(R.id.dumbbellSingleArmRowCheckBox);
        final CheckBox checkBox4 = findViewById(R.id.bentoverBarbellRowCheckBox);
        SharedPreferences sharedPreferences = getSharedPreferences("back", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when click it makes it checked
                if(checkBox1.isChecked()){
                    editor.putBoolean(getString(R.string.pullup), true);
                    editor.apply();
                }
                else {
                    editor.putBoolean(getString(R.string.pullup), false);
                    editor.apply();
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked()){
                    editor.putBoolean(getString(R.string.latPulldown), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.latPulldown), false);
                    editor.apply();
                }
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox3.isChecked()){
                    editor.putBoolean(getString(R.string.dumbBellSingleArmRow), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.dumbBellSingleArmRow), false);
                    editor.apply();
                }
            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox4.isChecked()){
                    editor.putBoolean(getString(R.string.bentOverBarbellRow), true);
                    editor.apply();
                }
                else{
                    editor.putBoolean(getString(R.string.bentOverBarbellRow), false);
                    editor.apply();
                }
            }
        });

    }

}
