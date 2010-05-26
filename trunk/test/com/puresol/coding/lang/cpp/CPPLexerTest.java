package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.coding.SourceCodeLexer;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class CPPLexerTest {

    @Test
    public void testLexer() {
	try {
	    DefaultPreConditioner conditioner = new DefaultPreConditioner(
		    new File("test"), new File(
			    "com/puresol/coding/lang/cpp/samples/String.cpp"));
	    TokenStream tokenStream = conditioner.getTokenStream();
	    SourceCodeLexer lexer = new SourceCodeLexer(
		    CPlusPlus.getInstance(), tokenStream);
	    TokenStream tokenStream2 = lexer.getTokenStream();
	    for (Token token : tokenStream2.getTokens()) {
		System.out.println(token.toString());
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (IOException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (NoMatchingTokenDefinitionFound e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
