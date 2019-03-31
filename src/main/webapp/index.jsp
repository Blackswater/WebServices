<%@ page import="org.json.JSONObject" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.RecipeGetter" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.ArraylistToXML" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="de">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Party-Planer</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">
    <!-- Das neueste kompilierte und minimierte CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optionales Theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Das neueste kompilierte und minimierte JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<!-- Grid row -->
<div class="row">

    <!-- Grid column -->
    <div class="col-md-12 mt-3">
        <div class="container-fluid">
            <ul class="nav grey lighten-4 py-4 font-weight-bold text-center justify-content-center">
                <div class="navbar-header">

                    <a class="navbar-brand blue-text" href="#">Partyplaner!</a>
                </div>
                <li class="nav-item">
                    <a class="nav-link active" href="#!">Party planen</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://www.google.com/maps/">Google maps</a>
                </li>

            </ul>

        </div>
    </div>
    <!-- Grid column -->
</div>

<!-- Grid row -->
<div class="container">

    <div class="col-md-12 d-flex justify-content-center">
        <div class="col-md-12 col-md-8 ">
            <form methode="post" action="#">
                <div class="form-group">
                    <label for="essen">
                        Essen
                    </label>
                    <input id="essen" type="text" name="food" class="form-control" placeholder="Bitten Essen eingeben...">

                    <label for="drinks">
                        Cocktail
                    </label>
                    <input id="drinks" type="text" name="drink" class="form-control" placeholder="Bitten Cocktail eingeben...">

                </div>

                <div class="text-center">
                    <input type="submit" id="btn1" name="btn1" class="btn btn-primary">
                    <br>
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
<div class="container">
    <div class="col d-flex justify-content-center">
        <label>
            Zutaten:
        </label>
        <br>
        <p><%=stringsOfJSON%>
        </p>



    </div>
    <div class="col d-flex justify-content-center">
        <label>
            Zubereitung:
        </label>
        <br>
        <p><%=reciepeList%>

        </p>

    </div>
</div>

<!-- Footer -->

<footer class="page-footer font-normal blue pt-4">
    <div class="container-fluid text-center text-md-left">
        <div class="row">
            <div class="col-md-6 mt-md-0 mt-3">
                <h5 class="text-uppercase">Party-Planer</h5>
                <p>Ich hoffe Sie haben eine gute Party geplant!</p>
            </div>
            <hr class="clearfix w-100 d-md-none pb-3">
            <div class="col-md-3 mb-md-0 mb-3">
                <h5 class="text-uppercase">Links</h5>
                <ul class="list-unstyled">
                    <li>
                        <a href="#">Party planen</a>
                    </li>
                    <li>
                        <a href="https://www.google.com/maps/">Google Maps</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright text-center py-3" style="background-color: #007bff">© 2018 Copyright: <a
            href="#">Party-Planer</a>
    </div>
</footer>



</body>
</html>