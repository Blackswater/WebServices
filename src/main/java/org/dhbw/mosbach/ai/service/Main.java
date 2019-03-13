package org.dhbw.mosbach.ai.service;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        getData dataGetter = new getData();
        ArrayList<ArrayList<ArrayList<String>>> theFoodList=dataGetter.getDataFromAPIFood("Lasagne,,sdfsgsd,Spaghetti");
        ArrayList<ArrayList<ArrayList<String>>> theDrinkList=dataGetter.getDataFromAPIDrinks("Vodka");
        System.out.println();
    }
}
