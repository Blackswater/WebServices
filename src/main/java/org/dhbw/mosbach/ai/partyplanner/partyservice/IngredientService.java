package org.dhbw.mosbach.ai.partyplanner.partyservice;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.apiaccess.IRecipeapiAccess;
import org.dhbw.mosbach.ai.partyplanner.apiaccess.RecipeGetter;
import org.dhbw.mosbach.ai.partyplanner.apiaccess.Recipeapiaccess;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.ItemType;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
@Path("/ingredients")
public class IngredientService implements IIngredientsService {
    private IRecipeapiAccess recipeapiAccess=new Recipeapiaccess();
    @Override
    public List<Item> getItems(List<Item> list) {
        return recipeapiAccess.searchapi(list);
    }

    @Override
    public List<Item> getItems(DrinkFoodPOJO pojo) {
        List<Item> items=toItemList(pojo.drinks,pojo.food);
        return getItems(items);
    }

    @Override
    public List<Ingredient> getIngredients(DrinkFoodPOJO pojo) {
        List<Item> items=toItemList(pojo.drinks,pojo.food);
        return getIngredients(items);
    }

    @Override
    public List<Ingredient> getIngredients(List<Item> list) {
        List<Ingredient> ingredients= Lists.newArrayList();
        recipeapiAccess.searchapi(list).forEach(item -> ingredients.addAll(item.getIngredients()));
        return ingredients;
    }

    private List<Item> toItemList(List<String> drinks, List<String> food){
        List<Item> items=new ArrayList<>();
        drinks.forEach(drink -> items.add(new Item(drink,ItemType.Drink,1)));
        food.forEach(drink -> items.add(new Item(drink,ItemType.Food,1)));
        return items;
    }
}
