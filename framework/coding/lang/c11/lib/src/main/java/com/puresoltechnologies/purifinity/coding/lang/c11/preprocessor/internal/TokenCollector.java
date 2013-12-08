package com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor.internal;

import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.WalkingAction;
import com.puresoltechnologies.parser.impl.lexer.Token;
import com.puresoltechnologies.parser.impl.lexer.TokenStream;
import com.puresoltechnologies.parser.impl.parser.ParserTree;

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
