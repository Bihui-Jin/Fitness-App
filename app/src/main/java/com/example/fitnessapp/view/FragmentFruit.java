package com.example.fitnessapp.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitnessapp.Controller.NutritionAdd;
import com.example.fitnessapp.Controller.NutritionPage;
import com.example.fitnessapp.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorSeekBarType;
import com.warkiz.widget.TickType;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class FragmentFruit extends DialogFragment implements DialogInterface.OnCancelListener {

    private double picecs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruit, null);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ImageView avocado, apple, pear, orange, banana, grape, back;
        avocado = getView().findViewById(R.id.avocado);
        apple = getView().findViewById(R.id.apple);
        pear = getView().findViewById(R.id.pear);
        orange = getView().findViewById(R.id.orange);
        grape = getView().findViewById(R.id.grape);
        banana = getView().findViewById(R.id.banana);
        back = getView().findViewById(R.id.backNutritionAddFruit);

        avocado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(18);
            }
        });
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(19);
            }
        });
        pear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(20);
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(21);
            }
        });
        banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(22);
            }
        });
        grape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(23);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getActivity(), NutritionPage.class);
                startActivity(home);
                getActivity().finish();
            }
        });
    }

    private String currentDate(){
        Calendar c = Calendar.getInstance();
        return Integer.toString(c.get(Calendar.YEAR)) +","+ Integer.toString(c.get(Calendar.MONTH))+","+ Integer.toString(c.get(Calendar.DATE));
    }

    private void dialog(final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout linear = new LinearLayout(getActivity());
        IndicatorSeekBar bar = new IndicatorSeekBar
                .Builder(getActivity())
                .setMax(5)
                .setMin(0)
                .setSeekBarType(IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS)
                .setTickType(TickType.OVAL)
                .setTickColor(Color.parseColor("#7cfc00"))
                .setTickSize(50)
                .setTickNum(48)
                .setBackgroundTrackSize(2)//dp size
                .setBackgroundTrackColor(Color.parseColor("#666666"))
                .setProgressTrackSize(3)//dp size
                .setProgressTrackColor(Color.parseColor("#7cfc00"))
                .showIndicator(false)
                .build();
        final TextView text = new TextView(getActivity());

        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.addView(bar);
        text.setText(R.string.food_dialog);
        bar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                String v = String.valueOf(progressFloat);
                if(progressFloat < 1.0) {
                    text.setText("\n How many pieces you have eaten:  " + v +" piece \n");
                }else {
                    text.setText("\n How many pieces you have eaten: " + v + " pieces \n");
                }
                picecs = progressFloat;
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        linear.addView(text);
        builder.setView(linear);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                SharedPreferences sp = getActivity().getSharedPreferences("FoodIntake", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                //index + pieces + date
                String s = Integer.toString(index)+"," + Double.toString(picecs)+","+ currentDate();
                ed.putString(s.substring(0,s.indexOf(",")),s);
                //ed.putString(currentDate(),s);
                ed.apply();
                Intent home = new Intent(getActivity().getApplicationContext(), NutritionAdd.class);
                startActivity(home);
                getActivity().finish();
                //testing
                Log.d("intake", s);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setContentView(R.layout.fragment_fruit);
        dialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentFruit fileFragment=(FragmentFruit)getFragmentManager().findFragmentById(R.id.fragment_fruit);
        if (fileFragment!=null)
            getFragmentManager().beginTransaction().remove(fileFragment).commit();
    }

}
