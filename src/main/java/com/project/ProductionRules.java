package com.project;

import lombok.*;

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

    public int type() {


        //type 3
        if (
                leftIsVn()
                        && (rightSideNumber() <= 2)
                        && (RightSideVnNumber() <= 1 && RightSideVtNumber() <= 1)
        ) return 3;
        //start with ` in S
        if (
                leftSideNumber() == 1
                        && leftIsVn()
        ) return 2;

        if (
                condType1()
                        &&leftSideNumber()<=rightSideNumber()

        ) return 1;

        return 0;
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


}
