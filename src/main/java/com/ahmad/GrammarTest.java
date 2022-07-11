package com.ahmad;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrammarTest {

    @Test
    public void TypeTwo(){
        Grammar grammar=new Grammar();
        grammar.add(new ProductionRules("S","aBa"));
        grammar.add(new ProductionRules("A","a"));
        grammar.add(new ProductionRules("S","A"));
        grammar.add(new ProductionRules("A","Aaaa"));
        assertEquals(ChomskyGrammarType.typeTwo,grammar.grammarType());
    }
}