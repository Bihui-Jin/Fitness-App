package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class loginActivity extends AppCompatActivity {
    private static final String TAG = "loginActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static String user_dob;
    private static String user_gender;
    private static String user_height;
    private static String user_weight;
    private static String user_goal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(loginStatus()){
            goHomePage();
        }
        setGenderSpinner();
        setGoalSpinner();
        setDate_getDob();
        getGender();
        getGoal();
        getHeight();
        getWeight();
        setButton();







    }
    private void setDate_getDob(){
        mDisplayDate = findViewById(R.id.dobText);
        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        loginActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + dayOfMonth +"/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
                user_dob = date;
            }
        };

    }
    private void setGenderSpinner(){
        Spinner spinner = findViewById(R.id.genderSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_spinner_items, R.layout.gender_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.gender_spinner_item,
                        this));

    }

    private void setGoalSpinner(){
        Spinner spinner = findViewById(R.id.goalSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.goal_spinner_items, R.layout.goal_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select your goal!");
        // Apply the adapter to the spinner
        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.goal_spinner_item,
                        this));
    }

    private void setButton(){
        Button finishBtn = findViewById(R.id.loginFinishButton);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_gender !=null & user_goal != null & user_dob != null
                & user_height != null & user_weight != null){
                    storeData();
                    goHomePage();
                }
            }
        });
    }

    private void goHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void getGender(){
        Spinner spinner = findViewById(R.id.genderSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        user_gender = "Male";
                        break;
                    case 2:
                        user_gender = "Female";
                        break;
                    case 3:
                        user_gender = "Other";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getGoal(){
        Spinner spinner = findViewById(R.id.goalSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        user_goal= "Gain muscle";
                        break;
                    case 2:
                        user_goal = "Loose weight";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getHeight(){
        TextView textView = findViewById(R.id.heightText);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user_height = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getWeight(){
        TextView textView = findViewById(R.id.weightText);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user_weight = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void storeData(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_status", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("user_dob", user_dob);
//        editor.putString("user_gender", user_gender);
//        editor.putString("user_height", user_height);
//        editor.putString("user_weight", user_weight);
//        editor.putString("user_goal", user_goal);
        editor.putBoolean(getString(R.string.login_status), true);
        editor.apply();
        Toast.makeText(this, "status saved", Toast.LENGTH_SHORT).show();
    }

    private boolean loginStatus(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_status", MODE_PRIVATE);
        return sharedPreferences.getBoolean(getString(R.string.login_status),false);
    }



}

