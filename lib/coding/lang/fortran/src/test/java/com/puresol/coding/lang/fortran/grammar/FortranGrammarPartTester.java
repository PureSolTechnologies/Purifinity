package com.puresol.coding.lang.fortran.grammar;

import java.io.File;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarPartTester;

public class FortranGrammarPartTester extends GrammarPartTester {

    public static final File PARSER_DIRECTORY = new File(
	    "test/com/puresol/coding/lang/fortran/parsers");

    public static boolean test(String production, String text) {
	Grammar grammar = FortranGrammar.getInstance();
	final String PARSER_NAME = production + "-parser";
	return test(PARSER_DIRECTORY, PARSER_NAME, grammar, production, text);
    }

}
