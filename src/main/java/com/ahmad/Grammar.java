package com.ahmad;

import java.util.ArrayList;

public class Grammar {
    ArrayList<ProductionRules>setOfProductionRules;

    public ChomskyGrammarType grammarType() {
        ChomskyGrammarType flag = ChomskyGrammarType.typeZero;
        for (ProductionRules p :
                this.setOfProductionRules) {
            if (p.type() == ChomskyGrammarType.typeZero) return ChomskyGrammarType.typeZero;
            if (p.type() == ChomskyGrammarType.typeOne) flag = ChomskyGrammarType.typeOne;
            if (p.type() == ChomskyGrammarType.typeTwo && flag != ChomskyGrammarType.typeOne)
                flag = ChomskyGrammarType.typeTwo;
            if (p.type() == ChomskyGrammarType.typeThree && (flag != ChomskyGrammarType.typeOne && flag != ChomskyGrammarType.typeTwo))
                flag = ChomskyGrammarType.typeThree;
        }
        if (flag.equals(ChomskyGrammarType.typeTwo)) {
            for (ProductionRules p :
                    this.setOfProductionRules) {
                if (!p.getLeftSideOfProductionRules().equals("S") || !p.getRightSideOfProductionRules().equals("`")) {
                    continue;
                }
                flag = ChomskyGrammarType.typeOne;
                break;
            }
        }
        return flag;
    }

    public ChomskyGrammarType grammarType(String start) {
        ChomskyGrammarType flag = ChomskyGrammarType.typeZero;
        for (ProductionRules p :
                this.setOfProductionRules) {
            if (p.type() == ChomskyGrammarType.typeZero) return ChomskyGrammarType.typeZero;
            if (p.type() == ChomskyGrammarType.typeOne) flag = ChomskyGrammarType.typeOne;
            if (p.type() == ChomskyGrammarType.typeTwo && flag != ChomskyGrammarType.typeOne)
                flag = ChomskyGrammarType.typeTwo;
            if (p.type() == ChomskyGrammarType.typeThree && (flag != ChomskyGrammarType.typeOne && flag != ChomskyGrammarType.typeTwo))
                flag = ChomskyGrammarType.typeThree;
        }
        if (flag.equals(ChomskyGrammarType.typeTwo)) {
            for (ProductionRules p :
                    this.setOfProductionRules) {
                if (!p.getLeftSideOfProductionRules().equals(start) || !p.getRightSideOfProductionRules().equals("`")) {
                    continue;
                }
                flag = ChomskyGrammarType.typeOne;
                break;
            }
        }
        return flag;
    }

    public void add(ProductionRules productionRules){
        setOfProductionRules.add(productionRules);
    }
    public Grammar(ArrayList<ProductionRules> setOfProductionRules){
        this.setOfProductionRules= (ArrayList<ProductionRules>) setOfProductionRules.clone();
    }
    public Grammar(){
        setOfProductionRules=new ArrayList<ProductionRules>();
    }

    //{S -> b,  }
    /*public ArrayList<ProductionRules> readGrammar(String set) {
        ArrayList<ProductionRules> arr = new ArrayList<>();
        if (set.charAt(0) != '{' || set.charAt(set.length() - 1) != '}') System.out.println("Wrong input");
        String leftSide = "";
        StringBuilder rightSide = new StringBuilder();

        boolean leftDone = false, rightDone = false;

        for (int i = 1; i < set.length(); i++) {
            char c = set.charAt(i);
            if (c == '-') leftDone = true;
            if (c == ',' || c == '}') rightDone = true;
            if (!leftDone) {
                leftSide += c;
            } else if (!rightDone && c !='-' && c !='>' && c!='|') {
                rightSide.append(c);
            }

            if (c == '|') {

                arr.add(new ProductionRules(leftSide, rightSide.toString()));
                rightSide = new StringBuilder();
                rightDone = false;
            }

            if (leftDone && rightDone) {
                arr.add(new ProductionRules(leftSide, rightSide.toString()));
                leftSide = "";
                rightSide = new StringBuilder();
                leftDone = false;
                rightDone = false;
            }
        }
        return arr;
    }
    *
     */

}
