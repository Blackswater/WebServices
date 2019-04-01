package org.dhbw.mosbach.ai.partyplanner.partyservice;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.apiaccess.IRecipeapiAccess;
import org.dhbw.mosbach.ai.partyplanner.apiaccess.RecipeApiAccess;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.ItemType;
import org.dhbw.mosbach.ai.partyplanner.reducer.IngredientReducer;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Pascal Röcker
 * Basic Ingredientservice
 */
@Path("/ingredients")
public class IngredientService implements IIngredientsService
{
    private IRecipeapiAccess recipeapiAccess = new RecipeApiAccess();

    /**
     *Searchs for Recipies and Ingredients in different apis
     */
    @Override
    public List<Item> getItems(List<Item> list)
    {
        return recipeapiAccess.searchapi(list);
    }


    /**
     *See @method getitems() but with Lists of Stings
     */
    @Override
    public List<Item> getItems(DrinkFoodPOJO pojo)
    {
        List<Item> items = toItemList(pojo.drinks, pojo.food);
        return getItems(items);
    }

    /**
     *See @method getIngredients() but with Lists of Stings
     */
    @Override
    public List<Ingredient> getIngredients(DrinkFoodPOJO pojo)
    {
        List<Item> items = toItemList(pojo.drinks, pojo.food);
        return getIngredients(items);
    }
    /**
     *
     * Requests the items and collects the ingredients in a list
     * List gets checked for duplicates returned
     */
    @Override
    public List<Ingredient> getIngredients(List<Item> list)
    {
        List<Ingredient> ingredients = Lists.newArrayList();
        recipeapiAccess.searchapi(list).forEach(item -> ingredients.addAll(item.getIngredients()));
        return (new IngredientReducer()).reduce(ingredients);
    }

    private List<Item> toItemList(List<String> drinks,
        List<String> food)
    {
        List<Item> items = new ArrayList<>();
        drinks.forEach(drink -> items.add(new Item(drink, ItemType.Drink, 1)));
        food.forEach(drink -> items.add(new Item(drink, ItemType.Food, 1)));
        return items;
    }
}
