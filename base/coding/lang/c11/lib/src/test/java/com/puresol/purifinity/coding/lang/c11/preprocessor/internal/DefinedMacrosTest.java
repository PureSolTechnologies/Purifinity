package com.puresol.purifinity.coding.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.c11.preprocessor.DefinedMacros;
import com.puresol.purifinity.uhura.grammar.token.Visibility;
import com.puresol.purifinity.uhura.lexer.Token;
import com.puresol.purifinity.uhura.lexer.TokenMetaData;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.source.FixedCodeLocation;

public class DefinedMacrosTest {

    @Test
    public void testDefineAndUndefine() {
	DefinedMacros macros = new DefinedMacros();
	assertFalse(macros.isDefined("Test"));
	TokenStream tokenStream = new TokenStream();
	tokenStream.add(new Token("Identifier", "Hallo", Visibility.VISIBLE,
		new TokenMetaData(new FixedCodeLocation("Hallo"), 1, 1)));
	macros.define("Test", tokenStream);
	assertTrue(macros.isDefined("Test"));
	assertTrue(macros.undefine("Test"));
	assertFalse(macros.isDefined("Test"));
    }

}
