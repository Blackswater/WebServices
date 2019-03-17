package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        RecipeGetter dataGetter = new RecipeGetter();
        ArrayList<ArrayList<ArrayList<String>>> theFoodList=dataGetter.getDataFromAPIFood("Lasagne,Szechuan Beef,Pork Cassoulet,sdfsgsd,Spaghetti");
        //KANN MEHRERE ERGEBNISSE PRO ESSEN/TRINKEN GEBEN!!
        ArrayList<ArrayList<ArrayList<String>>> theDrinkList=dataGetter.getDataFromAPIDrinks("Vodka");
        FoodItemCreator theItemCreator = new FoodItemCreator();
        ArrayList<Item> theItemlist = theItemCreator.createItems(theFoodList,theDrinkList);
        AllIngridientCalculator calculator = new AllIngridientCalculator();
        ArrayList<AllIngridientObject> allIngreidients = calculator.calculateAmounts(theFoodList);
        ArraylistToXML theXMLMaker = new ArraylistToXML();
        JSONObject theJSON = theXMLMaker.arraylistToJSON(theFoodList);
        String xml = XML.toString(theJSON);
        System.out.println(xml);
    }
}
