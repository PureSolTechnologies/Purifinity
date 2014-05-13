package com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.token.Visibility;
import com.puresoltechnologies.parsers.lexer.Token;
import com.puresoltechnologies.parsers.lexer.TokenMetaData;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.DefinedMacros;

public class DefinedMacrosTest {

	@Test
	public void testDefineAndUndefine() {
		DefinedMacros macros = new DefinedMacros();
		assertFalse(macros.isDefined("Test"));
		TokenStream tokenStream = new TokenStream();
		tokenStream.add(new Token("Identifier", "Hallo", Visibility.VISIBLE,
				new TokenMetaData(new FixedCodeLocation("Hallo"), 1, 1, 0)));
		macros.define("Test", tokenStream);
		assertTrue(macros.isDefined("Test"));
		assertTrue(macros.undefine("Test"));
		assertFalse(macros.isDefined("Test"));
	}

}
