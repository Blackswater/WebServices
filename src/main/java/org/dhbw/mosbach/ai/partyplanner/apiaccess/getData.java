package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
public class getData {

    public ArrayList<ArrayList<ArrayList<ArrayList<String>>>> getDataFromAPI(String food,String drinks) {
        String baseUriMeal = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        String baseUriCocktail = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> wholeList = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> fullIngridientsFood = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> fullIngridientsDrinks = new ArrayList<>();
        int countFood=1;
        int countDrinks=1;
        for(int i=0;i<food.length();i++){
            if(food.charAt(i)==','){
                countFood++;
            }
        }
        for(int i=0;i<drinks.length();i++){
            if(drinks.charAt(i)==','){
                countDrinks++;
            }
        }
        String foodList[] = food.split(",",countFood);
        String drinkList[] = drinks.split(",", countDrinks);
        for (String tempFood : foodList) {
            if (!tempFood.equals("")) {
                fullIngridientsFood.add(getIngredientString(getJSONArrayFromApi(baseUriMeal, tempFood, "meals")));
            } else {
                ArrayList<ArrayList<String>> emptyList = new ArrayList<>();
                fullIngridientsFood.add(emptyList);
            }
        }
        wholeList.add(fullIngridientsFood);
        for (String tempDrinks : drinkList) {
            if(!tempDrinks.equals("")) {
                fullIngridientsDrinks.add(getIngredientString(getJSONArrayFromApi(baseUriCocktail, tempDrinks, "drinks")));
            }
            else{
                ArrayList<ArrayList<String>> emptyList = new ArrayList<>();
                fullIngridientsDrinks.add(emptyList);
            }
        }
        wholeList.add(fullIngridientsDrinks);

        return wholeList;
    }

    private JSONArray getJSONArrayFromApi(String uri,String food,String foodType){
        JSONArray foodJson = new JSONArray();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(uri);
            builder.append(food);
            String apiUri = builder.toString();
            JSONObject jsonObjFromAPI = readJsonFromUrl(apiUri);
            foodJson = jsonObjFromAPI.getJSONArray(foodType);
        } catch (IOException | JSONException e) {
            // Fehler behandeln
            System.out.println(e);
            return new JSONArray();
        }
        return foodJson;
    }

    private ArrayList<ArrayList<String>> getIngredientString(JSONArray foodJson){
        ArrayList<ArrayList<String>> ingredientsList = new ArrayList<>();
        if(!(foodJson.length()==0)) {
            ArrayList<String> ingredients = new ArrayList<>();
            ArrayList<String> measurements = new ArrayList<>();
            ArrayList<String> instructions = new ArrayList<>();
            instructions.add(foodJson.getJSONObject(0).get("strInstructions").toString());
            int number = 1;
            while (true) {
                try {//Try catch notwendig, da eine JSONException ausgelöst wird wenn kein Element mehr gefunden werden kann
                    String ingredientID = "strIngredient" + number;
                    String ingredient = foodJson.getJSONObject(0).get(ingredientID).toString();
                    if (!ingredient.equals("") && !ingredient.equals("null")) {
                        ingredients.add(ingredient);
                        String measurementID = "strMeasure" + number;
                        String measurement = foodJson.getJSONObject(0).get(measurementID).toString();
                        measurements.add(measurement);
                    }
                    number++;
                } catch (JSONException e) {
                    break;
                }
            }
            ingredientsList.add(ingredients);
            ingredientsList.add(measurements);
            ingredientsList.add(instructions);
        }
        return  ingredientsList;
    }

        private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                return new JSONObject(jsonText);
            }
            finally {
                is.close();
            }
        }
        private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}