package com.example.fitnessapp.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import androidx.annotation.NonNull;

class Myadapter<T> extends ArrayAdapter {
    public Myadapter(@NonNull Context context, int resource, @NonNull List<T> objects){
        super(context,resource,objects);
    }

    @Override
    public int getCount(){
        int i = super.getCount();
        return i>0 ? i-1 : i;
    }
}
