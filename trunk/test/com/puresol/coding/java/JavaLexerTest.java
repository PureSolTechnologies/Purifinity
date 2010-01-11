package com.puresol.coding.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.puresol.coding.java.JavaKeywords;
import com.puresol.coding.java.JavaSymbols;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Lexer;
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
	    Lexer lexer = new Lexer(tokenStream);
	    lexer.addDefinitions(JavaKeywords.class);
	    lexer.addDefinitions(JavaSymbols.class);
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
