<%@ page import="java.util.ArrayList" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.RecipeGetter" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.dhbw.mosbach.ai.partyplanner.apiaccess.ArraylistToXML" %>
<html>
<body>

<h2>Hello World!</h2>

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
        out.println("------------out.print--------");
        out.println(request.getParameter("food"));
        out.println(drink);
        if (essen != null) {
            jsonA = xml.arraylistToJSON(recipe.getDataFromAPIFood(essen));

            out.print(jsonA);
        }

        if (drink != null) {
            jsonB = xml.arraylistToJSON(recipe.getDataFromAPIDrinks(drink));

            out.println("Drink Json:" + jsonB.toString());
        }

%>
<div>
<textarea id="foodArea" name='dummyText' style="margin: 0px; width: 680px; height: 400px"><%=jsonA%>

    </textarea>
</div>
<div>
    <textarea id="drinkArea" name='dummyText' style="margin: 0px; width: 680px; height: 400px"><%=jsonB%>

    </textarea>
</div>

</body>
</html>
