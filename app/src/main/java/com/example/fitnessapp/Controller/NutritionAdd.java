package com.example.fitnessapp.Controller;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.view.FragmentDrink;
import com.example.fitnessapp.view.FragmentFruit;
import com.example.fitnessapp.view.FragmentMeat;
import com.example.fitnessapp.view.FragmentVegie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NutritionAdd extends AppCompatActivity implements View.OnClickListener {

    private TextView vegie,meat,drink,fruit;

    private FragmentVegie fragmentVegie;
    private FragmentMeat fragmentMeat;
    private FragmentDrink fragmentDrink;
    private FragmentFruit fragmentFruit;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        setContentView(R.layout.nutrition_add);
        fManager = getSupportFragmentManager();
        bindViews();


        vegie.performClick(); // default
    }

    //UI components initialize & event binding
    private void bindViews() {
        vegie = findViewById(R.id.foodType_vegie);
        meat = findViewById(R.id.foodType_meat);
        drink = findViewById(R.id.foodType_drink);
        fruit = findViewById(R.id.foodType_fruit);

        vegie.setOnClickListener(this);
        meat.setOnClickListener(this);
        drink.setOnClickListener(this);
        fruit.setOnClickListener(this);
    }

    //reset all text as unselected
    private void setSelected(){
        vegie.setSelected(false);
        meat.setSelected(false);
        drink.setSelected(false);
        fruit.setSelected(false);
    }

    //hid all Fragments
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragmentVegie != null)fragmentTransaction.hide(fragmentVegie);
        if(fragmentMeat != null)fragmentTransaction.hide(fragmentMeat);
        if(fragmentDrink != null)fragmentTransaction.hide(fragmentDrink);
        if(fragmentFruit != null)fragmentTransaction.hide(fragmentFruit);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);

        switch (v.getId()){
            case R.id.foodType_vegie:
                setSelected();
                vegie.setSelected(true);
                fragmentVegie = new FragmentVegie();
                fTransaction.add(R.id.ly_content,fragmentVegie);
                fTransaction.show(fragmentVegie);
                break;
            case R.id.foodType_meat:
                setSelected();
                meat.setSelected(true);
                fragmentMeat = new FragmentMeat();
                fTransaction.add(R.id.ly_content,fragmentMeat);
                fTransaction.show(fragmentMeat);
                break;
            case R.id.foodType_drink:
                setSelected();
                drink.setSelected(true);
                fragmentDrink = new FragmentDrink();
                fTransaction.add(R.id.ly_content,fragmentDrink);
                fTransaction.show(fragmentDrink);
                break;
            case R.id.foodType_fruit:
                setSelected();
                fruit.setSelected(true);
                fragmentFruit = new FragmentFruit();
                fTransaction.add(R.id.ly_content,fragmentFruit);
                fTransaction.show(fragmentFruit);
                break;
        }
        fTransaction.commit();
    }

}
