package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

public class Recipeapiaccess implements IRecipeapiAccess {
    private String urlFood = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    private String urlDrink = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";


    public Recipeapiaccess() {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};

        // Install the all-trusting trust manager

        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, null);
            // Create an ssl socket factory with our all-trusting manager
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((urlHostName, session) -> true);
            // be authentic
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("", "".toCharArray());
                }
            });
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> searchapi(List<Item> items) {
        List<Item> newItems = Lists.newArrayList();
        for (Item current : items) {
            Item newItem = current;
            if (current.getType() != null) {
                newItem = updateItem(current);
            }
            newItems.add(newItem);
        }
        return newItems;
    }

    private Item updateItem(Item current) {
        String db = "";
        String itemtype="";
        switch (current.getType()) {
            case Drink:
                db = this.urlDrink;
                itemtype="drinks";
                break;
            case Food:
                db = this.urlFood;
                itemtype="meals";
                break;
            case Miscellaneous:
                return current;
        }
        JSONObject json = getJSONArrayFromApi(db, current.getName(),itemtype);
        if (json != null) {
            if (json.has("strInstructions"))
                current.setRecipe(json.get("strInstructions").toString());
            if (json.has("strDrink"))
                current.setName(json.get("strDrink").toString());
            int number = 1;
            List<Ingredient> ingredients = Lists.newArrayList();
            while (true)
                try {//Try catch notwendig, da eine JSONException ausgelöst wird wenn kein Element mehr gefunden werden kann
                    String ingredientID = "strIngredient" + number;
                    String ingredientname = json.get(ingredientID).toString();
                    if (!ingredientname.equals("") && !ingredientname.equals("null")) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setName(ingredientname);
                        String measurement = json.get("strMeasure" + number).toString();
                        ingredient.setMeasure(measurement);
                        ingredients.add(ingredient);
                    }
                    number++;
                } catch (JSONException e) {
                    break;
                }
            current.setIngredients(ingredients);
        }
        return current;
    }

    private JSONObject getJSONArrayFromApi(String uri, String food,String foodType) {
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject;
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(uri);
            builder.append(food);

            InputStream is = new URL(builder.toString().replaceAll(" ","%20")).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            int cp;
            while ((cp = rd.read()) != -1) sb.append((char) cp);
            is.close();
            jsonObject = new JSONObject(sb.toString()).getJSONArray(foodType).getJSONObject(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }
}
