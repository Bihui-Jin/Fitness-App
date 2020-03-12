package com.example.fitnessapp.Controller;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnessapp.utils.MyFragment;
import com.example.fitnessapp.R;
import com.example.fitnessapp.view.FragmentVegie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NutritionAdd extends AppCompatActivity implements View.OnClickListener {

    private TextView vrgie,meat,drink,fruit;

    private MyFragment fgVegie, fgMeat,fgDrink,fgFruit;
    private FragmentManager fManager;

    private ImageView broccoli, carrot, lettuce, potatoes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
        setContentView(R.layout.nutrition_add);
        fManager = getSupportFragmentManager();
        bindViews();


        vrgie.performClick(); // default
    }

    //UI components initialize & event binding
    private void bindViews() {
        vrgie = findViewById(R.id.foodType_vegie);
        broccoli = findViewById(R.id.broccoli);
        carrot = findViewById(R.id.carrot);
        lettuce = findViewById(R.id.lettuce);
        potatoes = findViewById(R.id.potatoes);

        meat = findViewById(R.id.foodType_meat);

        drink = findViewById(R.id.foodType_drink);

        fruit = findViewById(R.id.foodType_fruit);

        FrameLayout ly_content = findViewById(R.id.ly_content);



        vrgie.setOnClickListener(this);
        meat.setOnClickListener(this);
        drink.setOnClickListener(this);
        fruit.setOnClickListener(this);
    }

    //reset all text as unselected
    private void setSelected(){
        vrgie.setSelected(false);
        meat.setSelected(false);
        drink.setSelected(false);
        fruit.setSelected(false);
    }

    //hid all Fragments
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fgVegie != null)fragmentTransaction.hide(fgVegie);
        if(fgMeat != null)fragmentTransaction.hide(fgMeat);
        if(fgDrink != null)fragmentTransaction.hide(fgDrink);
        if(fgFruit != null)fragmentTransaction.hide(fgFruit);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);

        switch (v.getId()){
            case R.id.foodType_vegie:
                setSelected();
                vrgie.setSelected(true);
                FragmentVegie fragmentVegie = new FragmentVegie();
                fTransaction.add(R.id.ly_content,fragmentVegie);
                fTransaction.show(fragmentVegie);
                break;
            case R.id.foodType_meat:
                setSelected();
                meat.setSelected(true);
                if(fgMeat == null){
                    fgMeat = new MyFragment("Second meat Fragment");
                    fTransaction.add(R.id.ly_content,fgMeat);
                }else{
                    fTransaction.show(fgMeat);
                }
                break;
            case R.id.foodType_drink:
                setSelected();
                drink.setSelected(true);
                if(fgDrink == null){
                    fgDrink = new MyFragment("Third drink Fragment");
                    fTransaction.add(R.id.ly_content,fgDrink);
                }else{
                    fTransaction.show(fgDrink);
                }
                break;
            case R.id.foodType_fruit:
                setSelected();
                fruit.setSelected(true);
                if(fgFruit == null){
                    fgFruit = new MyFragment("Fourth fruit Fragment");
                    fTransaction.add(R.id.ly_content,fgFruit);
                }else{
                    fTransaction.show(fgFruit);
                }
                break;
        }
        fTransaction.commit();
    }

}
