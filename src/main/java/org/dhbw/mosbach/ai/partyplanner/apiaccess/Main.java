package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        RecipeGetter dataGetter = new RecipeGetter();
        ArrayList<ArrayList<ArrayList<String>>> theFoodList=dataGetter.getDataFromAPIFood("Lasagne,Szechuan Beef,Pork Cassoulet,sdfsgsd,Spaghetti");
        //KANN MEHRERE ERGEBNISSE PRO ESSEN/TRINKEN GEBEN!!
        ArrayList<ArrayList<ArrayList<String>>> theDrinkList=dataGetter.getDataFromAPIDrinks("Vodka");
        IngridientCalculator calculator = new IngridientCalculator();
        AllIngridientList allIngreidients = calculator.calculateAmounts(theFoodList);
        ArraylistToXML theXMLMaker = new ArraylistToXML();
        JSONObject theJSON = theXMLMaker.arraylistToJSON(theFoodList);
        String xml = XML.toString(theJSON);
        System.out.println(xml);

    }
}
