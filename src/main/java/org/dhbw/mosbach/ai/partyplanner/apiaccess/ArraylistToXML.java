package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.json.JSONObject;

import java.util.ArrayList;

public class ArraylistToXML {

    public JSONObject arraylistToJSON(ArrayList<ArrayList<ArrayList<String>>> theList){
        JSONObject theResult = new JSONObject();
        int recipeNumber=0;
        for(int i=0;i<theList.size();i++) {//für jedes Rezept ein JSON-Objekt
            if(theList.get(i).size()!=0) {
                JSONObject tempResult = new JSONObject();
                tempResult.put("name",theList.get(i).get(2).get(0));
                for (int j = 0; j < theList.get(i).get(0).size(); j++) {
                    tempResult.put(theList.get(i).get(0).get(j), theList.get(i).get(1).get(j));
                }
                tempResult.put("recipe", theList.get(i).get(3).get(0));
                theResult.put("Recipe"+recipeNumber, tempResult);
                recipeNumber++;
            }
        }
        System.out.println();
        return  theResult;
    }

}
