package org.dhbw.mosbach.ai.partyplanner;

import org.dhbw.mosbach.ai.partyplanner.apiaccess.getData;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        getData dataGetter = new getData();
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> theFoodList=dataGetter.getDataFromAPI("Lasagne,,sdfsgsd,Spaghetti","");
        System.out.println();
    }
}
