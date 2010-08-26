package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;

public class FortranGrammar {

	private static final String RESOURCE = "/com/puresol/coding/lang/fortran/grammar/Fortran2008.g";

	private static final Logger logger = Logger.getLogger(FortranGrammar.class);

	private static Grammar grammar = null;

	public static Grammar get() {
		if (grammar == null) {
			readGrammar();
		}
		return grammar;
	}

	private static synchronized void readGrammar() {
		try {
			if (grammar == null) {
				InputStream inStream = FortranGrammar.class
						.getResourceAsStream(RESOURCE);
				if (inStream == null) {
					logger.fatal("Could not read resource '" + RESOURCE + "'!");
					throw new StrangeSituationException(
							"Could not read resource '" + RESOURCE + "'!");
				}
				GrammarReader reader = new GrammarReader(inStream);
				reader.call();
				grammar = reader.getGrammar();
			}
		} catch (IOException e) {
			logger.fatal(e.getMessage(), e);
			throw new StrangeSituationException(e);
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new StrangeSituationException(e);
		}
	}
}
