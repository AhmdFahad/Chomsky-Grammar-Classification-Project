package com.ahmad;

import static org.junit.Assert.*;

public class ProductionRulesTest {

    @org.junit.Test
    public void typeThree() {
       assertEquals(ChomskyGrammarType.typeThree,new ProductionRules("S","aS").type());
       assertEquals(ChomskyGrammarType.typeThree,new ProductionRules("S","b").type());
       assertEquals(ChomskyGrammarType.typeThree,new ProductionRules("S","a").type());
       assertEquals(ChomskyGrammarType.typeThree,new ProductionRules("S","Sa").type());
       assertEquals(ChomskyGrammarType.typeThree,new ProductionRules("A","aA").type());
    }
    @org.junit.Test
    public void typeTwo() {
        assertEquals(ChomskyGrammarType.typeTwo,new ProductionRules("S","aSaSSS").type());
        assertEquals(ChomskyGrammarType.typeTwo,new ProductionRules("S","bbSSSb").type());
        assertEquals(ChomskyGrammarType.typeTwo,new ProductionRules("A","aAssSS").type());
    }
    @org.junit.Test
    public void typeOne() {
        assertEquals(ChomskyGrammarType.typeOne,new ProductionRules("aS","aSaSSS").type());
        assertEquals(ChomskyGrammarType.typeOne,new ProductionRules("Ab","bbSSSb").type());
        assertEquals(ChomskyGrammarType.typeOne,new ProductionRules("aAa","aAssSSa").type());
    }
}