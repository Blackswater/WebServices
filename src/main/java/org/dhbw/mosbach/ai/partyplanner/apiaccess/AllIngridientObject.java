package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import java.util.ArrayList;

public class AllIngridientObject {
    private String alreadyMarkedIngridient="";
    private ArrayList<String> alreadyMarkedIngridientAmount = new ArrayList<>();

    public String getAlreadyMarkedIngridient() {
        return alreadyMarkedIngridient;
    }

    public void setAlreadyMarkedIngridient(String alreadyMarkedIngridient) {
        this.alreadyMarkedIngridient = alreadyMarkedIngridient;
    }

    public ArrayList<String> getAlreadyMarkedIngridientAmount() {
        return alreadyMarkedIngridientAmount;
    }

    public void setAlreadyMarkedIngridientAmount(ArrayList<String> alreadyMarkedIngridientAmount) {
        this.alreadyMarkedIngridientAmount = alreadyMarkedIngridientAmount;
    }
}
