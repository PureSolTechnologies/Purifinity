package com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.token.Visibility;
import com.puresoltechnologies.parser.impl.lexer.Token;
import com.puresoltechnologies.parser.impl.lexer.TokenMetaData;
import com.puresoltechnologies.parser.impl.lexer.TokenStream;
import com.puresoltechnologies.parser.impl.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor.DefinedMacros;

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
