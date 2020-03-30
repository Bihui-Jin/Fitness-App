package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExecriseActivity extends AppCompatActivity {
    public static boolean chestChosen;
    boolean backChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execrise);
        setButton();
        getAvailableExe();


    }

    private void setButton(){
        final Context context = getApplicationContext();
        final Button addToCalendarBtn = findViewById(R.id.addToCalendar);
        Button chestAddBtn = findViewById(R.id.chestAddButton);
        Button backAddBtn = findViewById(R.id.backAddButton);
        chestAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChestPage();
            }
        });
        backAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackPage();
            }
        });
        addToCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = getUserChoice();
                if(checked){
                    goCalendar();
                }
                else {
                    Toast.makeText(context, "Choose at least one set of exercise", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // read value from the check box
    private boolean getUserChoice(){
//        RadioGroup radioGroup = findViewById()
        RadioButton chestRadioBtn = findViewById(R.id.chestRadioButton);
        RadioButton backRadioBtn = findViewById(R.id.backRadioButton);
        if(chestRadioBtn.isChecked()){
            ChoiceSet.doChest();
            return true;
        }
        if(backRadioBtn.isChecked()){
            ChoiceSet.doBack();
            return true;
        }
        return false;
    }

    private void goCalendar(){
        Intent intent = new Intent(this, AddCalendarActivity.class);
        startActivity(intent);
    }

    private void getAvailableExe(){
        setChest();
        setBack();
    }

    private void setBack(){
        SharedPreferences sharedPreferences = getSharedPreferences("back", MODE_PRIVATE);
        boolean pullup = sharedPreferences.getBoolean(getString(R.string.pullup),true);
        boolean latpulldown = sharedPreferences.getBoolean(getString(R.string.latPulldown), true);
        boolean dumbbellsinglearmrow = sharedPreferences.getBoolean(getString(R.string.dumbBellSingleArmRow), true);
        boolean bentoverbarbellrow = sharedPreferences.getBoolean(getString(R.string.bentOverBarbellRow),false);
        setBack1(pullup);
        setBack2(latpulldown);
        setBack3(dumbbellsinglearmrow);
        setBack4(bentoverbarbellrow);
        // not available now
//        setBack5(false);
//        setBack6(false);
    }

    private void setBack1(boolean status){
        Button btn = findViewById(R.id.backButton1);
        if(!status){
            btn.setVisibility(View.GONE);
        }
        else{
            btn.setVisibility(View.VISIBLE);
        }
        btn.setBackgroundResource(R.drawable.pullup);
    }

    private void setBack2(boolean status){
        Button btn = findViewById(R.id.backButton2);
        if(!status){
            btn.setVisibility(View.GONE);
        }
        else{
            btn.setVisibility(View.VISIBLE);
        }
        btn.setBackgroundResource(R.drawable.latpulldown);

    }

    private void setBack3(boolean status){
        Button btn = findViewById(R.id.backButton3);
        if(!status){
            btn.setVisibility(View.GONE);
        }
        else{
            btn.setVisibility(View.VISIBLE);
        }
        btn.setBackgroundResource(R.drawable.dumbbellsinglearmrow);

    }

    private void setBack4(boolean status){
        Button btn = findViewById(R.id.backButton4);
        if(!status){
            btn.setVisibility(View.GONE);
        }
        else{
            btn.setVisibility(View.VISIBLE);
        }
        btn.setBackgroundResource(R.drawable.bentoverbarbellrow);
    }
//    private void setBack5(boolean status){
//        Button btn = findViewById(R.id.backButton5);
//        if(!status){
//            btn.setVisibility(View.GONE);
//        }
//        else{
//            btn.setVisibility(View.VISIBLE);
//        }
//        // replace later
////        btn.setBackgroundResource(R.drawable.cablecrossover);
//
//    }

//    private void setBack6(boolean status){
//        Button btn = findViewById(R.id.backButton6);
//        if(!status){
//            btn.setVisibility(View.GONE);
//        }
//        else{
//            btn.setVisibility(View.VISIBLE);
//        }
//        // replace later
////        btn.setBackgroundResource(R.drawable.pushup);
//    }

    private void setChest(){
        SharedPreferences sharedPreferences = getSharedPreferences("chest", MODE_PRIVATE);
        boolean benchPress = sharedPreferences.getBoolean(getString(R.string.benchPress),true);
        boolean inclineDumbbellFlye = sharedPreferences.getBoolean(getString(R.string.inclineDumbbellFlye), true);
        boolean cableCrossover = sharedPreferences.getBoolean(getString(R.string.cableCrossover), true);
        boolean pushup = sharedPreferences.getBoolean(getString(R.string.pushup),false);
        boolean chestPressMachine = sharedPreferences.getBoolean(getString(R.string.chestPressMachine),true);
        boolean dumbbellFlye = sharedPreferences.getBoolean(getString(R.string.dumbbellFlye), true);
        String mesg = "push up: " + pushup +"\n" + "bench press: " + benchPress + "\n" + "incline dumbbell flye: " + inclineDumbbellFlye + "\n" + "cable cross over: " + cableCrossover + "\n" + "chest press machine: " + chestPressMachine +
                "\n" + "dumbbell flye: " + dumbbellFlye + "\n";
        Log.v("dam", mesg);
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


    private void goChestPage(){
        Intent intent = new Intent(this, ChestHomeActivity.class);
        startActivity(intent);
    }

    private void goBackPage(){
        Context context = getApplicationContext();
        Intent intent = new Intent(context, BackHomeActivity.class); // that's the problem !!
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
