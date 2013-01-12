package com.puresol.coding.lang.c11.preprocessor.internal;

import com.puresol.trees.TreeVisitor;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserTree;

public class TokenCollector implements TreeVisitor<ParserTree> {

    private final TokenStream tokenStream = new TokenStream();
    private final StringBuffer stringBuffer = new StringBuffer();

    public TokenStream getTokenStream() {
	return tokenStream;
    }

    public StringBuffer getStringBuffer() {
	return stringBuffer;
    }

    @Override
    public WalkingAction visit(ParserTree tree) {
	Token token = tree.getToken();
	if (token != null) {
	    tokenStream.add(token);
	    stringBuffer.append(token.getText());
	}
	return WalkingAction.PROCEED;
    }

}
