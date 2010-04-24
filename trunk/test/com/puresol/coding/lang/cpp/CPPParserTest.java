/**
 * Test //
 */
package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.di.DIClassBuilder;
import com.puresol.utils.di.Injection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CPPParserTest extends TestCase {

    @Test
    public void test() {
	CPPParser parser = null;
	try {
	    DefaultPreConditioner conditioner = new DefaultPreConditioner(
		    new File("test"),
		    new File(
			    "/com/puresol/coding/lang/java/samples/RandomNumbers.java"));
	    // DefaultPreConditioner conditioner =
	    // new DefaultPreConditioner(
	    // new File(
	    // "test/com/puresol/coding/lang/java/JavaParserTest.java"));
	    TokenStream tokenStream = conditioner.getTokenStream();
	    CPPLexer lexer = new CPPLexer(tokenStream);
	    TokenStream tokenStream2 = lexer.getTokenStream();
	    for (Token token : tokenStream2.getTokens()) {
		System.out.println(token.toString());
	    }
	    parser = DIClassBuilder.forInjections(
		    Injection.named("TokenStream", tokenStream2))
		    .createInstance(CPPParser.class);
	    parser.scan();
	    for (CodeRange codeRange : parser.getChildCodeRanges()) {
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
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ClassInstantiationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
