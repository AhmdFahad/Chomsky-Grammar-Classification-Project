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

    public boolean isVt(char c){
        return c >= 97 && c <= 122;
    }
    public boolean isVn(char c){
        return c >= 65 && c <= 90;
    }
}
