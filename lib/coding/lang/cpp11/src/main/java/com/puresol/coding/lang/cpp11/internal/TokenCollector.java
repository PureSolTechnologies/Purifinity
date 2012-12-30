package com.puresol.coding.lang.cpp11.internal;

import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserTree;

public class TokenCollector implements TreeVisitor<ParserTree> {

    public static TokenStream collect(ParserTree tree) {
	TokenCollector visitor = new TokenCollector();
	TreeWalker<ParserTree> walker = new TreeWalker<ParserTree>(tree);
	walker.walk(visitor);
	return visitor.tokenStream;
    }

    private final TokenStream tokenStream;

    private TokenCollector() {
	tokenStream = new TokenStream();
    }

    @Override
    public WalkingAction visit(ParserTree tree) {
	Token token = tree.getToken();
	if (token != null) {
	    tokenStream.add(token);
	}
	return WalkingAction.PROCEED;
    }

}
