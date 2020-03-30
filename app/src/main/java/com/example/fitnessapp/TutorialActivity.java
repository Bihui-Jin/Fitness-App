package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener{

    ViewFlipper viewFlipper;
    Button next;
    Button previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.nextButton);
        previous = (Button) findViewById(R.id.previousButton);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        setFinish();
    }

    @Override
    public void onClick(View v) {
        if (v == next) {
            viewFlipper.showNext();
        }
        else if (v == previous) {
            viewFlipper.showPrevious();
        }
    }

    private void setFinish(){
        Button button = findViewById(R.id.finishButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHomePage();
            }
        });
    }

    private void goHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
