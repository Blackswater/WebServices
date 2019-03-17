package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import java.util.ArrayList;

public class IngridientCalculator {

    public AllIngridientList calculateAmounts(ArrayList<ArrayList<ArrayList<String>>> ingredientList){
        AllIngridientList allIngridients = new AllIngridientList();
        ArrayList<String> alreadyMarkedIngridient=new ArrayList<>();
        ArrayList<ArrayList<String>> alreadyMarkedIngridientAmount = new ArrayList<>();
        for(int i=0;i<ingredientList.size();i++) {//Rezept Nummer
            if (ingredientList.get(i).size() != 0) {
                for (String tempIngridient : ingredientList.get(i).get(0)) {//An der 0. Stelle jeweils die Zustatennamen
                    if (!alreadyMarkedIngridient.contains(tempIngridient)) {
                        ArrayList<String> tempIngridientAmount = new ArrayList<>();
                        tempIngridientAmount.add(ingredientList.get(i).get(1).get(ingredientList.get(i).get(0).indexOf(tempIngridient)));
                        alreadyMarkedIngridient.add(tempIngridient);
                        alreadyMarkedIngridientAmount.add(tempIngridientAmount);
                    } else {//Schon vorhanden
                        alreadyMarkedIngridientAmount.get(alreadyMarkedIngridient.indexOf(tempIngridient)).add(ingredientList.get(i).get(1).get(ingredientList.get(i).get(0).indexOf(tempIngridient)));
                    }
                }
            }
        }
        allIngridients.setAlreadyMarkedIngridient(alreadyMarkedIngridient);
        allIngridients.setAlreadyMarkedIngridientAmount(alreadyMarkedIngridientAmount);
        System.out.println();
        return allIngridients;
    }


}
