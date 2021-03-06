package com.puresoltechnologies.purifinity.analysis.spi;

import java.io.IOException;
import java.util.Properties;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.grammar.GrammarException;
import com.puresoltechnologies.parsers.grammar.production.ProductionSet;
import com.puresoltechnologies.parsers.grammar.token.TokenDefinitionSet;
import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.parser.Parser;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;

/**
 * This class is an extended {@link Grammar} for language implementations. For
 * language implementations for analysis purpose, some more information is
 * needed to be accessible.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractLanguageGrammar extends Grammar implements
		LanguageGrammar {

	private static final long serialVersionUID = 3959164848195264441L;

	public AbstractLanguageGrammar(Properties options,
			TokenDefinitionSet tokenDefinitions, ProductionSet productions)
			throws GrammarException {
		super(options, tokenDefinitions, productions);
	}

	public abstract Lexer getLexer() throws IOException;

	public abstract Parser getParser() throws IOException;
}
