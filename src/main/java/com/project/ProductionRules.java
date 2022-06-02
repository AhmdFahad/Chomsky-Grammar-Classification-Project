package com.project;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductionRules {
    // leftSide -> RightSide
    private String leftSideOfProductionRules;
    private String rightSideOfProductionRules;

    public int leftSideNumber() {
        return leftSideOfProductionRules.length();
    }

    public int rightSideNumber() {
        return rightSideOfProductionRules.length();
    }

    //a b c
    public int leftSideVtNumber() {
        int number = 0;
        for (char c : leftSideOfProductionRules.toCharArray()) {
            if (isVt(c)) number++;
        }
        return number;
    }

    public int RightSideVtNumber() {
        int number = 0;
        for (char c : rightSideOfProductionRules.toCharArray()) {
            if (isVt(c)) number++;
        }
        return number;
    }

    //A B S C
    public int leftSideVnNumber() {
        int number = 0;
        for (char c : leftSideOfProductionRules.toCharArray()) {
            if (isVn(c)) number++;
        }
        return number;
    }

    public int RightSideVnNumber() {
        int number = 0;
        for (char c : rightSideOfProductionRules.toCharArray()) {
            if (isVn(c)) number++;
        }
        return number;
    }

    //cheek if leftSide is A
    public boolean leftIsVn() {
        return leftSideNumber() == 1 && isVn(leftSideOfProductionRules.charAt(0));
    }

    public boolean isVt(char c) {
        return c >= 97 && c <= 122;
    }

    public boolean isVn(char c) {
        return c >= 65 && c <= 90;
    }

    public ChomskyGrammarType type() {

        //type 3
        if (
                leftIsVn()
                        && (rightSideNumber() <= 2)
                        && (RightSideVnNumber() <= 1 && RightSideVtNumber() <= 1)
        ) return ChomskyGrammarType.typeThree;
        //start with ` in S
        if (
                leftSideNumber() == 1
                        && leftIsVn()
        ) return ChomskyGrammarType.typeTwo;

        if (
                condType1()
                        && leftSideNumber() <= rightSideNumber()

        ) return ChomskyGrammarType.typeOne;

        return ChomskyGrammarType.typeZero;
    }

    public boolean condType1() {
        String left = getLeftSideOfProductionRules();
        String right = getRightSideOfProductionRules();
        //Alpha one and two
        String $1l, $2l;
        String $1r, $2r;


        for (int i = 0; i < left.length(); i++) {

            if (isVn(left.charAt(i))) {
                $1l = left.substring(0, i);
                $2l = left.substring(i + 1);
                $1r = right.substring(0, i);
                $2r = right.substring(right.length() - $2l.length());
                //System.out.println("Alpha one : "+$1l+" == "+$1r+"\t Alpha two :  "+$2l+" == "+$2r);
                if (!($1l.length() + $2l.length() == right.length()))
                    if ($1l.equals($1r) && $2l.equals($2r)) return true;

            }

        }
        return false;
    }

    public static ChomskyGrammarType grammarType(ArrayList<ProductionRules> arr) {
        ChomskyGrammarType flag = ChomskyGrammarType.typeZero;
        for (ProductionRules p :
                arr) {
            if (p.type() == ChomskyGrammarType.typeZero) return ChomskyGrammarType.typeZero;
            if (p.type() == ChomskyGrammarType.typeOne) flag = ChomskyGrammarType.typeOne;
            if (p.type() == ChomskyGrammarType.typeTwo && flag != ChomskyGrammarType.typeOne)
                flag = ChomskyGrammarType.typeTwo;
            if (p.type() == ChomskyGrammarType.typeThree && (flag != ChomskyGrammarType.typeOne && flag != ChomskyGrammarType.typeTwo))
                flag = ChomskyGrammarType.typeThree;
        }
        if (flag.equals(ChomskyGrammarType.typeTwo)) {
            for (ProductionRules p :
                    arr) {
                if (p.getLeftSideOfProductionRules().equals("S") && p.getRightSideOfProductionRules().equals("`"))
                    flag = ChomskyGrammarType.typeOne;
            }
        }

        return flag;
    }

    public static ChomskyGrammarType grammarType(ArrayList<ProductionRules> arr, String start) {
        ChomskyGrammarType flag = ChomskyGrammarType.typeZero;
        for (ProductionRules p :
                arr) {
            if (p.type() == ChomskyGrammarType.typeZero) return ChomskyGrammarType.typeZero;
            if (p.type() == ChomskyGrammarType.typeOne) flag = ChomskyGrammarType.typeOne;
            if (p.type() == ChomskyGrammarType.typeTwo && flag != ChomskyGrammarType.typeOne)
                flag = ChomskyGrammarType.typeTwo;
            if (p.type() == ChomskyGrammarType.typeThree && (flag != ChomskyGrammarType.typeOne && flag != ChomskyGrammarType.typeTwo))
                flag = ChomskyGrammarType.typeThree;
        }
        if (flag.equals(ChomskyGrammarType.typeTwo)) {
            for (ProductionRules p :
                    arr) {
                if (p.getLeftSideOfProductionRules().equals(start) && p.getRightSideOfProductionRules().equals("`"))
                    flag = ChomskyGrammarType.typeOne;
            }
        }

        return flag;
    }

    //{S -> b,  }
    public static ArrayList<ProductionRules> readSetOfProductionRule(String set) {
        ArrayList<ProductionRules> arr = new ArrayList<>();
        if (set.charAt(0) != '{' || set.charAt(set.length() - 1) != '}') System.out.println("Wrong input");
        String leftSide = "";
        String rightSide = "";

        boolean leftDone = false, rightDone = false;

        for (int i = 1; i < set.length(); i++) {
            char c = set.charAt(i);
            if (c == '-') leftDone = true;
            if (c == ',' || c == '}') rightDone = true;
            if (leftDone != true) {
                leftSide += c;
            } else if (rightDone != true && c !='-' && c !='>' && c!='|') {
                rightSide += c;
            }


            if (c == '|') {

                arr.add(new ProductionRules(leftSide, rightSide));
                rightSide = "";
                rightDone = false;
            }

            if (leftDone && rightDone) {
                arr.add(new ProductionRules(leftSide, rightSide));
                leftSide = "";
                rightSide = "";
                leftDone = false;
                rightDone = false;
            }

        }

        return arr;
    }

}

