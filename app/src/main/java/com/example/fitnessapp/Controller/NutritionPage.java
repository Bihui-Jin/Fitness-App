package com.example.fitnessapp.Controller;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.fitnessapp.view.Column;
import com.example.fitnessapp.R;
import com.example.fitnessapp.utils.Images;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionPage extends AppCompatActivity {

    private Column calories;
    private Column carbos;
    private Column proteins;
    private Column fats;
    private ImageView food1, food2, food3;

    private List<Images> imgList = new ArrayList<>();
    private int chkFlag = 3; //check all 1s
    private int flag = 1; //check 3 screen images
    private int[] images = {R.drawable.lettuce, R.drawable.broccoli, R.drawable.carrot, R.drawable.potatoes};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_page);

        calories = findViewById(R.id.column_calories);
        carbos = findViewById(R.id.column_carbos);
        proteins = findViewById(R.id.column_proteins);
        fats = findViewById(R.id.column_fats);

        food1 = findViewById(R.id.nutritionFood1);
        food2 = findViewById(R.id.nutritionFood2);
        food3 = findViewById(R.id.nutritionFood3);
        ImageView refresh, back, addFood;
        refresh = findViewById(R.id.refreshNutrition);

        back = findViewById(R.id.backNutrition);
        addFood = findViewById(R.id.addNutrition);

        //add attributes to each food
        imgConvert(0,17,3.3,1.2,0.3);
        imgConvert(1,35,7.2,2.4,0.4);
        imgConvert(2, 35, 8.2, 0.8,0.2);
        imgConvert(3,93,21,2.5,0.1);

        // cannot use this directly, need logic to check the recommended foods and set useTime to 1   ****************
        food1.setImageResource(images[0]);
        imgUpdate(0);
        food2.setImageResource(images[1]);
        imgUpdate(1);
        food3.setImageResource(images[2]);
        imgUpdate(2);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgReplace();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NutritionPage.super.onBackPressed();
            }
        });
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nutritionAdd = new Intent(getApplicationContext(), NutritionAdd.class);
                startActivity(nutritionAdd);
                finish();
            }
        });
        initAllViews();
    }

    private void initAllViews() {
        SharedPreferences sp = getSharedPreferences("FoodIntake", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        int index,calo = 0, carbo=0,protein=0,fat=0,cCalo,cCarbo,cProtine,cFat;
        double pieces;
        String date, cDate;
        Calendar c = Calendar.getInstance();
        cDate = Integer.toString(c.get(Calendar.YEAR)) +","+ Integer.toString(c.get(Calendar.MONTH))+","+ Integer.toString(c.get(Calendar.DATE));


        for(int i=0; i< images.length;i++){
            String s = sp.getString(Integer.toString(i),"null");
           // testing
            Log.d("savedTakein", s);
            if(! "null".equals(s)){
                assert s != null;
                String[] strs = s.split(",",3);
                index = Integer.parseInt(strs[0]);
                pieces = Double.parseDouble(strs[1]);
                date = strs[2];
                Log.d("test", "loop1");
                if(date.equals(cDate)){
                    calo = (int) Math.round (imgList.get(index).getCalo() * pieces) + calo;
                    carbo = (int) Math.round (imgList.get(index).getCarbo() * pieces) + carbo;
                    protein = (int) Math.round (imgList.get(index).getProtein() * pieces) + protein;
                    fat = (int) Math.round (imgList.get(index).getFat() * pieces + fat);
                    Log.d("calo", Integer.toString(calo));
                    Log.d("carbo", Integer.toString(carbo));
                    Log.d("protein", Integer.toString(protein));
                    Log.d("fat", Integer.toString(fat));

                    ed.putString("temp", Integer.toString(calo)+","+ Integer.toString(carbo)+","+ Integer.toString(protein)+","+ Integer.toString(fat));
                    ed.apply();
                }
            }
        }

        String data = sp.getString(cDate,"null");
        String temp = sp.getString("temp", "null");
        //testing
        Log.d("savedData", data);

        if("null".equals(data) && "null".equals(temp)){
            calories.setData(0, 10);
            carbos.setData(0, 10);
            proteins.setData(0, 10);
            fats.setData(0, 10);
            Log.d("initial", "all zeros");
        }else{
            String[] strs = temp.split(",");
            cCalo = Integer.parseInt(strs[0]);
            cCarbo = Integer.parseInt(strs[1]);
            cProtine = Integer.parseInt(strs[2]);
            cFat = Integer.parseInt(strs[3]);
            if(calo != 0) {
                cCalo=+ calo;
                cCarbo=+ carbo;
                cProtine =+ protein;
                cFat =+ fat;
            }
            ed.putString(cDate, Integer.toString(calo)+","+ Integer.toString(carbo)+","+ Integer.toString(protein)+","+ Integer.toString(fat));
            ed.apply();

            int lcm = max(cCalo,cCarbo,cProtine,cFat);
            calories.setData(cCalo, lcm);
            carbos.setData(cCarbo, lcm);
            proteins.setData(cProtine, lcm);
            fats.setData(cFat, lcm);

            Log.d("calo", Integer.toString(cCalo));
            Log.d("carbo", Integer.toString(cCarbo));
            Log.d("protein", Integer.toString(cProtine));
            Log.d("fat", Integer.toString(cFat));
            Log.d("gcd", Integer.toString(lcm));

        }
    }

    public void imgConvert(int img, int calo, double carbo, double protein, double fat){
            Images image = new Images();
            image.setIndex(img);
            image.setUsedTimes(0);
            image.setCalo(calo);
            image.setCarbo(carbo);
            image.setProtein(protein);
            image.setFat(fat);

            imgList.add(img,image);
    }

    public Integer imgCheck(int img){
        Images image = new Images();
        image.setIndex(img);
        image.setCalo(imgList.get(img).getCalo());
        image.setCarbo(imgList.get(img).getCarbo());
        image.setProtein(imgList.get(img).getProtein());
        image.setFat(imgList.get(img).getFat());


        if(imgList.get(img).getUsedTimes() == 0){//if existing some that haven't been used, set usedTimes to 1 and return img
            image.setUsedTimes(1);
            imgList.set(img,image);
            chkFlag++;
            Log.d("img", Integer.toString(img));
            Log.d("flag", Integer.toString(chkFlag));
            return img;
        }else if(chkFlag == imgList.size()){//if all have been used, set all usedTimes to 0 and return -1
            for(int i=0; i< imgList.size();i++){
                Images images = new Images();
                images.setIndex(imgList.get(i).getIndex());
                images.setCalo(imgList.get(i).getCalo());
                images.setCarbo(imgList.get(i).getCarbo());
                images.setProtein(imgList.get(i).getProtein());
                images.setFat(imgList.get(i).getFat());
                images.setUsedTimes(0);
                imgList.set(i,images);
                Log.d("reset", Integer.toString(i));

            }
            Log.d("resetReturn", Integer.toString(-1));
            chkFlag =0;
            return -1;
        }
        Log.d("error", Integer.toString(-2));
        return -2; // never should return -2 otherwise bug here!

    }

    public void imgUpdate (int img){ //update useTimes to 1
        Images image = new Images();
        image.setIndex(img);
        image.setCalo(imgList.get(img).getCalo());
        image.setCarbo(imgList.get(img).getCarbo());
        image.setProtein(imgList.get(img).getProtein());
        image.setFat(imgList.get(img).getFat());
        image.setUsedTimes(1);
        imgList.set(img,image);
    }

    public void imgReplace(){
        if(flag < 4){
            Log.d("flag1", Integer.toString(chkFlag));
            for(int i=0; i<images.length;i++){
                int temp = imgCheck(i);
                if(temp >= 0){
                    //food img set
                    if(food1.toString().contains(Integer.toString(flag))){
                        imgUpdate(temp);
                        food1.setImageResource(images[temp]);
                    }else if(food2.toString().contains(Integer.toString(flag))){
                        imgUpdate(temp);
                        food2.setImageResource(images[temp]);
                    }else if(food3.toString().contains(Integer.toString(flag))){
                        imgUpdate(temp);
                        food3.setImageResource(images[temp]);
                    }
                    flag++;
                }else if(temp == -1){
                    food1.setImageResource(images[0]);
                    imgUpdate(0);
                    food2.setImageResource(images[1]);
                    imgUpdate(1);
                    food3.setImageResource(images[2]);
                    imgUpdate(2);

                    flag = 4;
                }
            }
        }
    }

    public int max(int a, int b, int c, int d){
        int temp =0;
        if(a>=b) temp =a;
        else temp=b;
        if(temp<=c) temp=c;
        if(temp<=d) temp =d;
        return temp;
    }

}
