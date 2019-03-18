package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.Set;

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
        JSONObject theJSON2 = theXMLMaker.arraylistToJSON(theDrinkList);
        Set<String> keys = theJSON2.keySet();
        for(String key: keys){
            int recipeNr=theJSON.length();
            theJSON.put("Recipe"+recipeNr,theJSON2.get(key));
        }
        String xml = XML.toString(theJSON);
        System.out.println(xml);
    }
}