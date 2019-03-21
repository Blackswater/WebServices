<%@ page import="java.util.*" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.RecipeGetter" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.ArraylistToXML" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Party-Planer</title>
    <!-- Das neueste kompilierte und minimierte CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optionales Theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Das neueste kompilierte und minimierte JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapse" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only"> Navigation ein-/ausblenden</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>

            </button>
            <a class="navbar-brand" href="#">Partyplaner!</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Party planen<span class="sr-only">(aktuell)</span></a></li>
                <li><a href="https://www.google.com/maps/">Google Maps</a></li>
            </ul>

        </div>
    </div>
</nav>

<div class="container">

    <div class="col-sm-8">
        <div class="col-xs-12 col-md-8">
            <form methode="post" action="#">
                <div class="form-group">
                    <label for="essen">
                        Essen
                        <input id="essen" type="text" name="food" class="form-control"
                               placeholder="Bitten Essen eingeben...">
                    </label>
                    <label for="drinks">
                        Cocktail
                        <input id="drinks" type="text" name="drink" class="form-control"
                               placeholder="Bitten Cocktail eingeben...">
                    </label>
                </div>

                <div class="col-xs-6 col-md-4">
                    <input type="submit" id="btn1" name="btn1" class="btn pull-right">

                </div>
            </form>
        </div>
    </div>
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
<div class="col-cs-12 col-sm6 col-md-8">
    <label >
        Zutaten:
    </label>
    <p><%=stringsOfJSON%>
    </p>



</div>
<div class="col-cs-12 col-sm6 col-md-8">
    <label>
        Zubereitung:
    </label>
    <p><%=reciepeList%>

    </p>

</div>
</div>

</body>
</html>
