package com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.internal;

import com.puresoltechnologies.parsers.lexer.Token;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.WalkingAction;

public class TokenCollector implements TreeVisitor<ParseTreeNode> {

	private final TokenStream tokenStream = new TokenStream();
	private final StringBuffer stringBuffer = new StringBuffer();

	public TokenStream getTokenStream() {
		return tokenStream;
	}

	public StringBuffer getStringBuffer() {
		return stringBuffer;
	}

	@Override
	public WalkingAction visit(ParseTreeNode tree) {
		Token token = tree.getToken();
		if (token != null) {
			tokenStream.add(token);
			stringBuffer.append(token.getText());
		}
		return WalkingAction.PROCEED;
	}

}
