import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class getData {
    public void getDataFromAPI(int apiID,String food) {
        String baseUriMeal = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        String baseUriCocktail = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> measurements = new ArrayList<>();
        try {
            StringBuilder builder = new StringBuilder();
            switch (apiID){
                case 0:
                    builder.append(baseUriMeal);
                    break;
                case 1:
                    builder.append(baseUriCocktail);
                    break;
            }
            builder.append(food);
            String apiUri = builder.toString();
            JSONObject jsonObj = readJsonFromUrl(apiUri);
            JSONArray part = new JSONArray();
            switch (apiID){
                case 0:
                    part = jsonObj.getJSONArray("meals");
                    break;
                case 1:
                    part = jsonObj.getJSONArray("drinks");
                    break;
            }
            int number=1;
            while(true){
                try{//Try catch notwendig, da eine JSONException ausgelöst wird wenn kein Element mehr gefunden werden kann
                    String ingredientID= "strIngredient"+number;
                    String ingredient = part.getJSONObject(0).get(ingredientID).toString();
                    if(!ingredient.equals("")) {
                        ingredients.add(ingredient);
                        String measurementID= "strMeasure"+number;
                        String measurement = part.getJSONObject(0).get(measurementID).toString();
                        measurements.add(measurement);
                    }
                    number++;
                }
                catch (JSONException e){
                    break;
                }
            }
        } catch (IOException e) {
            // Fehler behandeln
            System.out.println(e);
        }
        System.out.println(ingredients.get(0));
    }

        public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                return json;
            } finally {
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