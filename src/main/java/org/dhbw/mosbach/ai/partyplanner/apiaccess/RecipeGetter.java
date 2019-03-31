package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipeGetter {

    //Methode zum Abfragen von Informatuionen von der MealDB
    public ArrayList<ArrayList<ArrayList<String>>> getDataFromAPIFood(String food) {
        String baseUriMeal = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        ArrayList<ArrayList<ArrayList<String>>> fullIngridientsFood = new ArrayList<>();
        String foodList[] = splitString(food);
        for (String tempFood : foodList) {
            if (!tempFood.equals("")) {
                fullIngridientsFood.add(getIngredientString(getJSONArrayFromApi(baseUriMeal, tempFood, "meals"),"strMeal"));
            } else {
                ArrayList<ArrayList<String>> emptyList = new ArrayList<>();
                ArrayList<String> tempName = new ArrayList<>();
                tempName.add(tempFood);
                emptyList.add(tempName);
                fullIngridientsFood.add(emptyList);
            }
        }
        return fullIngridientsFood;
    }

    //Methode zum Abfragen von Informatuionen von der CocktailDB
    public ArrayList<ArrayList<ArrayList<String>>> getDataFromAPIDrinks(String drinks) {
        String baseUriCocktail = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
        ArrayList<ArrayList<ArrayList<String>>> fullIngridientsDrinks = new ArrayList<>();
        String drinkList[] = splitString(drinks);
        for (String tempDrinks : drinkList) {
            if(!tempDrinks.equals("")) {
                fullIngridientsDrinks.add(getIngredientString(getJSONArrayFromApi(baseUriCocktail, tempDrinks, "drinks"),"strDrink"));
            }
            else{
                ArrayList<ArrayList<String>> emptyList = new ArrayList<>();
                ArrayList<String> tempName = new ArrayList<>();
                tempName.add(tempDrinks);
                emptyList.add(tempName);
                fullIngridientsDrinks.add(emptyList);
            }
        }

        return fullIngridientsDrinks;
    }

    //Methode zum Aufteilen des Strings zu einzelnen Gerichtnamen
    private String[] splitString(String unsplitString){
        unsplitString=unsplitString.replaceAll("\\s+","%20");
        int countComma=1;
        for(int i=0;i<unsplitString.length();i++){
            if(unsplitString.charAt(i)==','){
                countComma++;
            }
        }
        String[] splitString = unsplitString.split(",", countComma);
        return splitString;
    }

    //Allgemeine Methode zur Abfrage von einem JSON-Objekt von einer API
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
            JSONObject tempJSON = new JSONObject();
            tempJSON.put("name",food);
            foodJson.put(tempJSON);
            System.out.println(e);
        }
        return foodJson;
    }

    //Auslesen der Daten aus einem JSON-Array und erstellung einer Liste mit allen Infos
    private ArrayList<ArrayList<String>> getIngredientString(JSONArray foodJson,String foodOrDrinkName){
        ArrayList<ArrayList<String>> ingredientsList = new ArrayList<>();
        if((foodJson.getJSONObject(0).length()>1)) {
            ArrayList<String> ingredients = new ArrayList<>();
            ArrayList<String> measurements = new ArrayList<>();
            ArrayList<String> instructions = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            instructions.add(foodJson.getJSONObject(0).get("strInstructions").toString());
            name.add(foodJson.getJSONObject(0).get(foodOrDrinkName).toString());
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
            ingredientsList.add(name);
            ingredientsList.add(instructions);
        }
        else{
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(foodJson.getJSONObject(0).get("name").toString());
            ingredientsList.add(tempList);
        }
        return  ingredientsList;
    }

    //Anfrage an eine API per InputStream und erstellung eines JSON-Objekts aus den Informationen
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

    //Auslesen der Informationen aus einem Buffered Reader
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}