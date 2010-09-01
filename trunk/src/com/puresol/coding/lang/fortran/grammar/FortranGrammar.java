package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;

public class FortranGrammar {

	private static final String RESOURCE = "/com/puresol/coding/lang/fortran/grammar/Fortran2008.g";

	private static final Logger logger = Logger.getLogger(FortranGrammar.class);

	private static Grammar grammar = null;

	public static Grammar get() throws IOException {
		if (grammar == null) {
			readGrammar();
		}
		return grammar;
	}

	private static synchronized void readGrammar() throws IOException {
		try {
			if (grammar == null) {
				InputStream inStream = FortranGrammar.class
						.getResourceAsStream(RESOURCE);
				if (inStream == null) {
					logger.fatal("Could not read resource '" + RESOURCE + "'!");
					throw new IOException("Could not read resource '"
							+ RESOURCE + "'!");
				}
				GrammarReader reader = new GrammarReader(inStream);
				reader.call();
				grammar = reader.getGrammar();
			}
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new IOException(e.getMessage());
		}
	}
}
