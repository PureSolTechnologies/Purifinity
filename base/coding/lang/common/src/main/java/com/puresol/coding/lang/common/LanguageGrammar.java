package com.puresol.coding.lang.common;

import java.io.InputStream;
import java.util.Properties;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;

/**
 * This class is an extended {@link Grammar} for language implementations. For
 * language implementations for analysis purpose, some more information is
 * needed to be accessible.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class LanguageGrammar extends Grammar {

    private static final long serialVersionUID = 3959164848195264441L;

    public LanguageGrammar(Properties options,
	    TokenDefinitionSet tokenDefinitions, ProductionSet productions)
	    throws GrammarException {
	super(options, tokenDefinitions, productions);
    }

    public abstract InputStream getGrammarDefinition();

}
