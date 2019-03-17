package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import java.util.ArrayList;

public class AllIngridientList {
    ArrayList<String> alreadyMarkedIngridient=new ArrayList<>();
    ArrayList<ArrayList<String>> alreadyMarkedIngridientAmount = new ArrayList<>();

    public ArrayList<String> getAlreadyMarkedIngridient() {
        return alreadyMarkedIngridient;
    }

    public void setAlreadyMarkedIngridient(ArrayList<String> alreadyMarkedIngridient) {
        this.alreadyMarkedIngridient = alreadyMarkedIngridient;
    }

    public ArrayList<ArrayList<String>> getAlreadyMarkedIngridientAmount() {
        return alreadyMarkedIngridientAmount;
    }

    public void setAlreadyMarkedIngridientAmount(ArrayList<ArrayList<String>> alreadyMarkedIngridientAmount) {
        this.alreadyMarkedIngridientAmount = alreadyMarkedIngridientAmount;
    }
}
