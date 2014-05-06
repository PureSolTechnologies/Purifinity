package com.puresoltechnologies.purifinity.framework.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.token.Visibility;
import com.puresoltechnologies.parsers.impl.lexer.Token;
import com.puresoltechnologies.parsers.impl.lexer.TokenMetaData;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.framework.lang.c11.preprocessor.DefinedMacros;

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