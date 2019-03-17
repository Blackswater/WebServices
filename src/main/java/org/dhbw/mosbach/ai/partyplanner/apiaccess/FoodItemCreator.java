package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.ItemType;

import java.util.ArrayList;

public class FoodItemCreator {

    public ArrayList<Item> createItems(ArrayList<ArrayList<ArrayList<String>>> theFoodList,ArrayList<ArrayList<ArrayList<String>>> theDrinkList){
        ArrayList<Item> theItemList=new ArrayList<>();
        theItemList.addAll(itemlistOfArrayList(theFoodList));
        theItemList.addAll(itemlistOfArrayList(theDrinkList));
        return theItemList;
    }

    private ArrayList<Item> itemlistOfArrayList(ArrayList<ArrayList<ArrayList<String>>> theList){
        ArrayList<Item> theItemList=new ArrayList<>();
        for(ArrayList<ArrayList<String>> tempList:theList){
            if(tempList.size()>1) {
                Item tempItem = new Item();
                tempItem.setName(tempList.get(2).get(0));
                tempItem.setType(ItemType.Food);
                tempItem.setRecipe(tempList.get(3).get(0));
                ArrayList<Ingredient> tempIngredientList = new ArrayList<>();
                for (int i = 0; i < tempList.get(0).size(); i++) {
                    Ingredient tempIngredient = new Ingredient();
                    tempIngredient.setName(tempList.get(0).get(i));
                    tempIngredient.setMeasure(tempList.get(1).get(i));
                    tempIngredientList.add(tempIngredient);
                }
                tempItem.setIngredients(tempIngredientList);
                theItemList.add(tempItem);
            }
            else{
                Item tempItem = new Item();
                tempItem.setName(tempList.get(0).get(0));
                tempItem.setType(ItemType.Miscellaneous);
                theItemList.add(tempItem);
            }
        }
        return theItemList;
    }

}
