/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.lang.java.JavaLexer;
import com.puresol.coding.lang.java.JavaParser;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaParserTest extends TestCase {

    @Test
    public void test() {
	JavaParser parser = null;
	try {
	    // DefaultPreConditioner conditioner =
	    // new DefaultPreConditioner(
	    // new File(
	    // "test/com/puresol/coding/lang/java/samples/RandomNumbers.java"));
	    DefaultPreConditioner conditioner =
		    new DefaultPreConditioner(
			    new File(
				    "test/com/puresol/coding/lang/java/JavaParserTest.java"));
	    TokenStream tokenStream = conditioner.getTokenStream();
	    JavaLexer lexer = new JavaLexer(tokenStream);
	    TokenStream tokenStream2 = lexer.getTokenStream();
	    for (Token token : tokenStream2.getTokens()) {
		System.out.println(token.toString());
	    }
	    parser = new JavaParser(tokenStream2);
	    parser.scan();
	    for (CodeRange codeRange : parser.getCodeRanges()) {
		System.out.println(codeRange.toString());
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
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ParserException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
