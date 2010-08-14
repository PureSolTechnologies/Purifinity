package com.puresol.uhura.parser;

import java.util.concurrent.Callable;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.lexer.TokenStream;

public interface Parser extends Callable<SyntaxTree> {

	public void setTokenStream(TokenStream tokenStream);

	public void setProductions(ProductionSet ruleSet);

}
