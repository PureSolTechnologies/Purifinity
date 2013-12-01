package com.puresoltechnologies.purifinity.coding.lang.commons;

import java.io.IOException;
import java.util.Properties;

import com.puresoltechnologies.purifinity.coding.lang.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.grammar.production.ProductionSet;
import com.puresoltechnologies.purifinity.uhura.grammar.token.TokenDefinitionSet;
import com.puresoltechnologies.purifinity.uhura.lexer.Lexer;
import com.puresoltechnologies.purifinity.uhura.parser.Parser;

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
