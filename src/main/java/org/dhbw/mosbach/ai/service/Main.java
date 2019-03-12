package org.dhbw.mosbach.ai.service;

import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        getData dataGetter = new getData();
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> theFoodList=dataGetter.getDataFromAPI("Lasagne,sdfsgsd","dfgdfg");
        System.out.println();
    }
}
