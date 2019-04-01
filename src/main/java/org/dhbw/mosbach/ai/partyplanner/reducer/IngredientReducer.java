package org.dhbw.mosbach.ai.partyplanner.reducer;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;

import java.util.List;

/**
 * @author Pascal Röcker Collects the same ingredients and adds them together
 */
public class IngredientReducer implements IReducer<Ingredient>
{
    @Override
    public List<Ingredient> reduce(List<Ingredient> listToReduce)
    {
        List<Ingredient> newIngredients = Lists.newArrayList();
        while (listToReduce.size()!=0)
        {
            Ingredient ingredient = listToReduce.get(0);
            int i=1;
            while (i<listToReduce.size()){
                if(ingredientequals(listToReduce.get(i),ingredient.getName(),ingredient.getMeasure())){
                    ingredient.setAmount(ingredient.getAmount()+listToReduce.get(i).getAmount());
                    listToReduce.remove(i);
                }else{
                    i++;
                }

            }
            newIngredients.add(ingredient);
        }
        return newIngredients;
    }

    private boolean ingredientequals(Ingredient ingredient,
        String name,
        String measure)
    {
        return name.equals(ingredient.getName()) && measure.equals(ingredient.getMeasure());
    }
}
