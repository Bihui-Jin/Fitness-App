package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setLogoutButton();
        cleanHisotry();

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
        Toast.makeText(context, "done",Toast.LENGTH_SHORT).show();

    }
}
