package com.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pRule = "{S -> Ab|a,A - > a|`}";


        ProductionRules[] productionRules = new ProductionRules[4];
        productionRules[0] = new ProductionRules("S", "Ab");
        productionRules[1] = new ProductionRules("SS", "aAA");
        productionRules[2] = new ProductionRules("A", "ab");
        productionRules[3] = new ProductionRules("B", "aA");
        for (ProductionRules productionRule : productionRules) {
            System.out.println(Grammar.type(productionRule));
        }


    }
}

class Grammar {

    public static int type(ProductionRules productionRules) {
        //type 3
        if (
                productionRules.leftIsVn()
                && (productionRules.rightSideNumber() <= 2)
                && (productionRules.RightSideVnNumber() <= 1 && productionRules.RightSideVtNumber() <= 1)
        ) return 3;
        //start with ` in S
        if (
                productionRules.leftSideNumber() == 1
                        && productionRules.leftIsVn()
        )return 2;



        return 0;
    }
    public static boolean condType1(ProductionRules productionRules){
        String left=productionRules.getLeftSideOfProductionRules();
        String right=productionRules.getRightSideOfProductionRules();


        return false;
    }


}
