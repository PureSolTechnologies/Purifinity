package com.puresoltechnologies.purifinity.framework.lang.java7.grammar.parts.chapter0_Tokens;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;

public class FloatingPointLiteralIT {

	@Test
	public void test() throws Exception {
		Lexer lexer = JavaGrammar.getInstance().getLexer();
		TokenStream tokenStream = lexer.lex(new FixedCodeLocation("0.1234")
				.getSourceCode());
		assertEquals(1, tokenStream.size());
		assertEquals("FloatingPointLiteral", tokenStream.get(0).getName());
	}

}
