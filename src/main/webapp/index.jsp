<%@ page import="java.util.*" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.RecipeGetter" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.ArraylistToXML" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.util.Set" %>
<html>
<body>

<h1>Party-Planer!</h1>

<div>
    <form  methode="post"  action="#">
        <label >
            Essen
            <input type="text" name="food">
        </label>
        <label >
            Cocktail
            <input type="text" name="drink">
        </label>
        <input type="submit" id="btn1" name="btn1">
    </form>
</div>


<%
        String essen = request.getParameter("food");
        String drink = request.getParameter("drink");

        JSONObject jsonA = null;
        JSONObject jsonB = null;

        RecipeGetter recipe = new RecipeGetter();
        ArraylistToXML xml = new ArraylistToXML();
        //out.println("------------out.print--------");
        //out.println(request.getParameter("food"));
        //out.println(drink);
    ArrayList<String> stringsOfJSON = new ArrayList<>();
    ArrayList<String> reciepeList = new ArrayList<>();
        if (essen != null) {
            jsonA = xml.arraylistToJSON(recipe.getDataFromAPIFood(essen));
            jsonB = xml.arraylistToJSON(recipe.getDataFromAPIDrinks(drink));
            Set<String> keys = jsonB.keySet();
            int recipeCount=jsonA.length();
            for(String key: keys){
                jsonA.put("Recipe"+recipeCount,jsonB.get(key));
                recipeCount++;
            }
            Set<String> theKeys1 = jsonA.keySet();
            for(int i=0;i<theKeys1.size();i++){
                StringBuilder tempBuilder = new StringBuilder();
                JSONObject tempObject = jsonA.getJSONObject((String)theKeys1.toArray()[i]);
                Set<String> tempKeys = tempObject.keySet();
                tempBuilder.append("\n");
                tempBuilder.append(tempObject.get("name"));
                tempBuilder.append("\n");
                //tempKeys.remove("recipe");
                //tempKeys.remove("name");
                for(int j=0;j<tempKeys.size();j++){
                    if(tempKeys.toArray()[j]!="recipe"&&tempKeys.toArray()[j]!="name") {
                        tempBuilder.append(tempKeys.toArray()[j]);
                        tempBuilder.append(":");
                        tempBuilder.append(tempObject.get((String) tempKeys.toArray()[j]));
                        tempBuilder.append("\n");
                    }
                }
                tempBuilder.append("\n");
                stringsOfJSON.add(tempBuilder.toString());
                StringBuilder recipeBuilder = new StringBuilder();
                recipeBuilder.append(tempObject.get("name"));
                recipeBuilder.append(":");
                recipeBuilder.append("\n");
                recipeBuilder.append(tempObject.get("recipe").toString());
                recipeBuilder.append("\n\n");
                reciepeList.add(recipeBuilder.toString());
            }
            //out.print(jsonA);
        }

        if (drink != null) {
            jsonB = xml.arraylistToJSON(recipe.getDataFromAPIDrinks(drink));

            //out.println("Drink Json:" + jsonB.toString());
        }

%>
<div>
<textarea id="foodArea" name='dummyText' style="margin: 0px; width: 680px; height: 400px"><%=stringsOfJSON%>

    </textarea>
</div>
<div>
    <textarea id="drinkArea" name='dummyText' style="margin: 0px; width: 680px; height: 400px"><%=reciepeList%>

    </textarea>
</div>

</body>
</html>
