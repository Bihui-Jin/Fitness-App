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
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionPage extends AppCompatActivity {

    private Column calories;
    private Column carbos;
    private Column proteins;
    private Column fats;

    private List<Images> imgList = new ArrayList<>();
    private int chkFlag = 3; //check all 1s
    private int flag = 1; //check 3 screen images
    private int[] images = {R.drawable.lettuce, R.drawable.broccoli, R.drawable.carrot, R.drawable.potatoes, R.drawable.spinach, R.drawable.tomato,
                            R.drawable.burger, R.drawable.chickenleg, R.drawable.chickenbreast, R.drawable.egg, R.drawable.sausage, R.drawable.steak,
                            R.drawable.milk, R.drawable.wine, R.drawable.greentea, R.drawable.blacktea, R.drawable.beer, R.drawable.coke,
                            R.drawable.avocado, R.drawable.apple, R.drawable.pear, R.drawable.orange, R.drawable.banana, R.drawable.grape};
    private ImageView recomImage1, recomImage2, recomImage3, recomImage4, recomImage5, recomImage6, recomImage7, recomImage8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_page);

        calories = findViewById(R.id.column_calories);
        carbos = findViewById(R.id.column_carbos);
        proteins = findViewById(R.id.column_proteins);
        fats = findViewById(R.id.column_fats);

        recomImage1 = findViewById(R.id.recomImage1);
        recomImage2 = findViewById(R.id.recomImage2);
        recomImage3 = findViewById(R.id.recomImage3);
        recomImage4 = findViewById(R.id.recomImage4);
        recomImage5 = findViewById(R.id.recomImage5);
        recomImage6 = findViewById(R.id.recomImage6);
        recomImage7 = findViewById(R.id.recomImage7);
        recomImage8 = findViewById(R.id.recomImage8);
        ImageView back, addFood;

        back = findViewById(R.id.backNutrition);
        addFood = findViewById(R.id.addNutrition);

        //add attributes to each food
        //vegie / 100g
        imgConvert(0,17,3.3,1.2,0.3);
        imgConvert(1,35,7.2,2.4,0.4);
        imgConvert(2, 35, 8.2, 0.8,0.2);
        imgConvert(3,93,21,2.5,0.1);
        imgConvert(4, 60, 7, 3.5,2);
        imgConvert(5,5,1,0,0);
        //meat / 100g
        imgConvert(6,202,15,15.5,9.1);
        imgConvert(7,230,9,15,15);
        imgConvert(8, 111, 0, 20.2,0);
        imgConvert(9,70,1,6,5);
        imgConvert(10, 395, 0, 14,37.2);
        imgConvert(11,214,0,18.8,14.7);
        //drink / 100g
        imgConvert(12,64,4.6,4.3,2.1);
        imgConvert(13,120,7,0,0);
        imgConvert(14, 0, 0.3, 20.2,0);
        imgConvert(15,1,0.7,0.7,0.7);
        imgConvert(16, 50, 13.4, 0,0);
        imgConvert(17,37,9.7,18.8,14.7);
        //fruit / 100g
        imgConvert(18,159,7.1,3.5,15.9);
        imgConvert(19,57,15,0,0);
        imgConvert(20, 97, 25.2, 0.4,0);
        imgConvert(21,49,12.1,0.2,0.2);
        imgConvert(22, 89, 22.8, 1.1,0.3);
        imgConvert(23,59,18.1,0.72,0.16);
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

        int index, calo=0, carbo=0,protein=0,fat=0;
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
                Log.d("test", "loop"+Integer.toString(i));
                if(date.equals(cDate)){
                    calo += (int) Math.round (imgList.get(index).getCalo() * pieces);
                    carbo += (int) Math.round (imgList.get(index).getCarbo() * pieces);
                    protein += (int) Math.round (imgList.get(index).getProtein() * pieces);
                    fat += (int) Math.round (imgList.get(index).getFat() * pieces);

                    Log.d("calo", Integer.toString(calo));
                    Log.d("carbo", Integer.toString(carbo));
                    Log.d("protein", Integer.toString(protein));
                    Log.d("fat", Integer.toString(fat));

                    ed.putString(cDate, Integer.toString(calo)+","+ Integer.toString(carbo)+","+ Integer.toString(protein)+","+ Integer.toString(fat));
                    ed.apply();
                }
            }
        }

        String data = sp.getString(cDate,"null");
        //testing
        Log.d("savedData", data);

        int lcm = max(calo,carbo,protein,fat)+25;
        calories.setData(calo, lcm);
        carbos.setData(carbo, lcm);
        proteins.setData(protein, lcm);
        fats.setData(fat, lcm);

        Log.d("calo", Integer.toString(calo));
        Log.d("carbo", Integer.toString(carbo));
        Log.d("protein", Integer.toString(protein));
        Log.d("fat", Integer.toString(fat));
        Log.d("gcd", Integer.toString(lcm));

        //recommend food images
        recomImage1.setVisibility(View.GONE);
        recomImage2.setVisibility(View.GONE);
        recomImage3.setVisibility(View.GONE);
        recomImage4.setVisibility(View.GONE);
        recomImage5.setVisibility(View.GONE);
        recomImage6.setVisibility(View.GONE);
        recomImage7.setVisibility(View.GONE);
        recomImage8.setVisibility(View.GONE);
        if(calo<2280 && (carbo<290 || fat<53 || protein<76)){
            ArrayList recomImg = new ArrayList();
            for(int i=0; i<images.length; i++){
                if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getCarbo()+carbo)<=290)&&((imgList.get(i).getProtein()+protein)<=76)&&((imgList.get(i).getFat()+fat)<=53)){
                    recomImg.add(i);
                }
            }
            imageSet(recomImg);

        }else if(calo<2280 && (carbo>290 || fat>53 || protein>76)){
            ArrayList recomImg = new ArrayList();
            if(carbo>290&&fat>53&&protein>76){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(carbo>290&&fat>53){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getProtein()+protein)<=76)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(fat>53&&protein>76){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getCarbo()+carbo)<=76)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(carbo>290&&protein>76){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getFat()+fat)<=76)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(carbo>290){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getProtein()+protein)<=76)&&((imgList.get(i).getFat()+fat)<=53)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(fat>53){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getCarbo()+carbo)<=290)&&((imgList.get(i).getProtein()+protein)<=76)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else {
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCalo()+calo)<=2280)&&((imgList.get(i).getCarbo()+carbo)<=290)&&((imgList.get(i).getFat()+fat)<=53)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }

        }else if(calo>2280 && (carbo<290 || fat<53 || protein<76)){
            ArrayList recomImg = new ArrayList();
            if(carbo<290&&fat<53&&protein<76){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCarbo()+carbo)<=290)&&((imgList.get(i).getProtein()+protein)<=76)&&((imgList.get(i).getFat()+fat)<=53)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(carbo<290&&fat<53){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCarbo()+carbo)<=290)&&((imgList.get(i).getFat()+fat)<=53)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(fat<53&&protein<76){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getProtein()+protein)<=76)&&((imgList.get(i).getFat()+fat)<=53)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(carbo<290&&protein<76){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCarbo()+carbo)<=290)&&((imgList.get(i).getProtein()+protein)<=76)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(carbo<290){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getCarbo()+carbo)<=290)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else if(fat<53){
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getFat()+fat)<=53)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }else {
                for(int i=0; i<images.length; i++){
                    if(((imgList.get(i).getProtein()+protein)<=76)){
                        recomImg.add(i);
                    }
                }
                imageSet(recomImg);
            }
        }else{
            recomImage1.setVisibility(View.VISIBLE);
            recomImage1.setBackgroundResource(R.drawable.congrats);
            recomImage2.setVisibility(View.VISIBLE);
            recomImage2.setBackgroundResource(R.drawable.you);
            recomImage3.setVisibility(View.VISIBLE);
            recomImage3.setBackgroundResource(R.drawable.have);
            recomImage4.setVisibility(View.VISIBLE);
            recomImage4.setBackgroundResource(R.drawable.taken);
            recomImage5.setVisibility(View.VISIBLE);
            recomImage5.setBackgroundResource(R.drawable.enough);
            recomImage6.setVisibility(View.VISIBLE);
            recomImage6.setBackgroundResource(R.drawable.food);
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

    public void imageSet(ArrayList recomImg){
        HashSet<Integer> hs = new HashSet<Integer>();
        if(recomImg.size()>=8){
            Random random = new Random();
            for(;;){
                int s = random.nextInt(recomImg.size());
                hs.add(s);
                if(hs.size()==8) break;
            }
            Integer[] randImg = hs.toArray(new Integer[hs.size()]);
            recomImage1.setVisibility(View.VISIBLE);
            recomImage1.setBackgroundResource(images[randImg[0]]);
            recomImage2.setVisibility(View.VISIBLE);
            recomImage2.setBackgroundResource(images[randImg[1]]);
            recomImage3.setVisibility(View.VISIBLE);
            recomImage3.setBackgroundResource(images[randImg[2]]);
            recomImage4.setVisibility(View.VISIBLE);
            recomImage4.setBackgroundResource(images[randImg[3]]);
            recomImage5.setVisibility(View.VISIBLE);
            recomImage5.setBackgroundResource(images[randImg[4]]);
            recomImage6.setVisibility(View.VISIBLE);
            recomImage6.setBackgroundResource(images[randImg[5]]);
            recomImage7.setVisibility(View.VISIBLE);
            recomImage7.setBackgroundResource(images[randImg[6]]);
            recomImage8.setVisibility(View.VISIBLE);
            recomImage8.setBackgroundResource(images[randImg[7]]);
        }else{
            Random random = new Random();
            for(;;){
                int s = random.nextInt(recomImg.size());
                hs.add(s);
                if(hs.size()==recomImg.size()) break;
            }
            Integer[] randImg = hs.toArray(new Integer[hs.size()]);

            if(recomImg.size() == 0|| recomImg.isEmpty()){

            }else if(recomImg.size() == 1){

                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);

            }else if(recomImg.size() == 2){
                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);
                recomImage2.setVisibility(View.VISIBLE);
                recomImage2.setBackgroundResource(images[randImg[1]]);

            }else if(recomImg.size() == 3){
                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);
                recomImage2.setVisibility(View.VISIBLE);
                recomImage2.setBackgroundResource(images[randImg[1]]);
                recomImage3.setVisibility(View.VISIBLE);
                recomImage3.setBackgroundResource(images[randImg[2]]);

            }else if(recomImg.size() == 4){
                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);
                recomImage2.setVisibility(View.VISIBLE);
                recomImage2.setBackgroundResource(images[randImg[1]]);
                recomImage3.setVisibility(View.VISIBLE);
                recomImage3.setBackgroundResource(images[randImg[2]]);
                recomImage4.setVisibility(View.VISIBLE);
                recomImage4.setBackgroundResource(images[randImg[3]]);

            }else if(recomImg.size() == 5){
                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);
                recomImage2.setVisibility(View.VISIBLE);
                recomImage2.setBackgroundResource(images[randImg[1]]);
                recomImage3.setVisibility(View.VISIBLE);
                recomImage3.setBackgroundResource(images[randImg[2]]);
                recomImage4.setVisibility(View.VISIBLE);
                recomImage4.setBackgroundResource(images[randImg[3]]);
                recomImage5.setVisibility(View.VISIBLE);
                recomImage5.setBackgroundResource(images[randImg[4]]);

            }else if(recomImg.size() == 6){
                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);
                recomImage2.setVisibility(View.VISIBLE);
                recomImage2.setBackgroundResource(images[randImg[1]]);
                recomImage3.setVisibility(View.VISIBLE);
                recomImage3.setBackgroundResource(images[randImg[2]]);
                recomImage4.setVisibility(View.VISIBLE);
                recomImage4.setBackgroundResource(images[randImg[3]]);
                recomImage5.setVisibility(View.VISIBLE);
                recomImage5.setBackgroundResource(images[randImg[4]]);
                recomImage6.setVisibility(View.VISIBLE);
                recomImage6.setBackgroundResource(images[randImg[5]]);

            }else if(recomImg.size() == 7){
                recomImage1.setVisibility(View.VISIBLE);
                recomImage1.setBackgroundResource(images[randImg[0]]);
                recomImage2.setVisibility(View.VISIBLE);
                recomImage2.setBackgroundResource(images[randImg[1]]);
                recomImage3.setVisibility(View.VISIBLE);
                recomImage3.setBackgroundResource(images[randImg[2]]);
                recomImage4.setVisibility(View.VISIBLE);
                recomImage4.setBackgroundResource(images[randImg[3]]);
                recomImage5.setVisibility(View.VISIBLE);
                recomImage5.setBackgroundResource(images[randImg[4]]);
                recomImage6.setVisibility(View.VISIBLE);
                recomImage6.setBackgroundResource(images[randImg[5]]);
                recomImage7.setVisibility(View.VISIBLE);
                recomImage7.setBackgroundResource(images[randImg[6]]);

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
