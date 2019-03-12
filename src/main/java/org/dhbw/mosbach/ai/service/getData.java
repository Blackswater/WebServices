package org.dhbw.mosbach.ai.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
public class getData {

    public void getDataFromAPI(String food,String drinks) {
        String baseUriMeal = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        String baseUriCocktail = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
        ArrayList<ArrayList<ArrayList<String>>> fullIngridientsFood = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> fullIngridientsDriks = new ArrayList<>();
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
            fullIngridientsFood.add(getIngredientString(getJSONArrayFromApi(baseUriMeal,tempFood,"meals")));
        }
        for (String tempDrinks : drinkList) {
            fullIngridientsDriks.add(getIngredientString(getJSONArrayFromApi(baseUriCocktail,tempDrinks,"drinks")));
        }
        System.out.println();
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
        } catch (IOException e) {
            // Fehler behandeln
            System.out.println(e);
        }
        return foodJson;
    }

    private ArrayList<ArrayList<String>> getIngredientString(JSONArray foodJson){
        ArrayList<ArrayList<String>> ingredientsList = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> measurements = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add(foodJson.getJSONObject(0).get("strInstructions").toString());
        int number=1;
        while(true){
            try{//Try catch notwendig, da eine JSONException ausgelöst wird wenn kein Element mehr gefunden werden kann
                String ingredientID= "strIngredient"+number;
                String ingredient = foodJson.getJSONObject(0).get(ingredientID).toString();
                if(!ingredient.equals("")&&!ingredient.equals("null")) {
                    ingredients.add(ingredient);
                    String measurementID= "strMeasure"+number;
                    String measurement = foodJson.getJSONObject(0).get(measurementID).toString();
                    measurements.add(measurement);
                }
                number++;
            }
            catch (JSONException e){
                break;
            }
        }
        ingredientsList.add(ingredients);
        ingredientsList.add(measurements);
        ingredientsList.add(instructions);
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