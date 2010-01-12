package com.puresol.coding.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class JavaLexerTest {

    @Test
    public void testLexer() {
	try {
	    DefaultPreConditioner conditioner =
		    new DefaultPreConditioner(
			    new File(
				    "test/com/puresol/coding/java/JavaLexerTest.java"));
	    TokenStream tokenStream = conditioner.getTokenStream();
	    JavaLexer lexer = new JavaLexer(tokenStream);
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
	}
    }
}
