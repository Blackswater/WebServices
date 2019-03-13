package org.dhbw.mosbach.ai.service;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        RecipeGetter dataGetter = new RecipeGetter();
        ArrayList<ArrayList<ArrayList<String>>> theFoodList=dataGetter.getDataFromAPIFood("Lasagne,Szechuan Beef,Pork Cassoulet,sdfsgsd,Spaghetti");
        ArrayList<ArrayList<ArrayList<String>>> theDrinkList=dataGetter.getDataFromAPIDrinks("Vodka,");
        IngridientCalculator calculator = new IngridientCalculator();
        calculator.calculateAmounts(theFoodList);
        System.out.println();
    }
}
