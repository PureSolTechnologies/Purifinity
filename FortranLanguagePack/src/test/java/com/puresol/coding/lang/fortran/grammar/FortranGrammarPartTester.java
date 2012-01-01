package com.puresol.coding.lang.fortran.grammar;

import java.io.File;
import java.io.IOException;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarPartTester;
import com.puresol.utils.PersistenceException;

public class FortranGrammarPartTester extends GrammarPartTester {

	public static final File PARSER_DIRECTORY = new File(
			"test/com/puresol/coding/lang/fortran/parsers");

	public static boolean test(String production, String text) {
		try {
			Grammar grammar = FortranGrammar.getInstance().getGrammar();
			final String PARSER_NAME = production + "-parser";
			return test(PARSER_DIRECTORY, PARSER_NAME, grammar, production,
					text);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
	}

}
