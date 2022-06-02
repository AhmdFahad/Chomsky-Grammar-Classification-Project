package com.project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String x="{S->bS|`,F->Sb}";
        ArrayList<ProductionRules> arr=ProductionRules.readSetOfProductionRule(x);
        for (ProductionRules p :
                arr) {
            System.out.println(p.getLeftSideOfProductionRules()+"->"+p.getRightSideOfProductionRules());
        }
        System.out.println(ProductionRules.grammarType(arr,"F"));
    }

}

