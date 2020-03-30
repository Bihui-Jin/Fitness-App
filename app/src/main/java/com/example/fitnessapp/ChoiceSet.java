package com.example.fitnessapp;

public class ChoiceSet {
    private static boolean chestChosen = false;
    private static boolean backChosen = false;

    public static void doChest(){
        chestChosen = true;
    }


    public static void doBack(){
        backChosen = true;
    }

    public static void undo(){
        chestChosen = false;
        backChosen = false;
    }

    public static boolean getChest(){
        return chestChosen;
    }

    public static boolean getBack(){
        return backChosen;
    }
}
