package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setLogoutButton();
        cleanHisotry();
        setTutorialButton();

    }

    private void setLogoutButton(){
        Button logoutBtn = findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBack();
            }
        });
    }


    private void setBack(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_status", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.login_status), false);
        editor.apply();
        Toast.makeText(this, "status saved", Toast.LENGTH_SHORT).show();
    }

    private void cleanHisotry(){
        Button cleanHistroyBtn = findViewById(R.id.cleanHistoryButton);
        cleanHistroyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
    }

    private void clean(){
        Context context = getApplicationContext();
        SharedPreferences settings = context.getSharedPreferences("chestDate", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
        SharedPreferences settings2 = context.getSharedPreferences("ExeDate", Context.MODE_PRIVATE);
        settings2.edit().clear().apply();
        SharedPreferences settings3 = context.getSharedPreferences("chosenDay", Context.MODE_PRIVATE);
        settings3.edit().clear().apply();

        String cDate;
        Calendar c = Calendar.getInstance();
        cDate = Integer.toString(c.get(Calendar.YEAR)) +","+ Integer.toString(c.get(Calendar.MONTH))+","+ Integer.toString(c.get(Calendar.DATE));
        SharedPreferences settings4 = context.getSharedPreferences(cDate, Context.MODE_PRIVATE);
        Log.d(cDate,(settings4.getString(cDate,"null")));
        settings4.edit().clear().apply();
        SharedPreferences settings5 = context.getSharedPreferences("FoodIntake", Context.MODE_PRIVATE);
        settings5.edit().clear().apply();
        for(int i=0; i< 23;i++) {
            SharedPreferences settings6 = context.getSharedPreferences(Integer.toString(i), Context.MODE_PRIVATE);
            settings6.edit().clear().apply();
        }

        Toast.makeText(context, "done",Toast.LENGTH_SHORT).show();

    }

    private void setTutorialButton(){
        Button finishBtn = findViewById(R.id.ToTutorialButton);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goTutorialPage();

            }
        });
    }

    private void goTutorialPage(){
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}
