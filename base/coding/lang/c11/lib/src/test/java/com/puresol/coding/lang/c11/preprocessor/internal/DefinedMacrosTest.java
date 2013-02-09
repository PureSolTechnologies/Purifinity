package com.puresol.coding.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.c11.preprocessor.DefinedMacros;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.FixedCodeLocation;

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
