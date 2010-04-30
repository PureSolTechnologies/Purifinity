package com.puresol.coding.lang.java.source.parts;

import org.junit.Test;

import com.puresol.coding.lang.java.JavaLexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.utils.di.DependencyInjection;
import com.puresol.utils.di.Injection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MethodCallTest extends TestCase {

    @Test
    public void testWithoutParameters() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testCall()");
	    MethodCall methodCall = new MethodCall();
	    DependencyInjection.inject(methodCall, Injection.named(
		    "TokenStream", lexer.getTokenStream()));
	    methodCall.scan();
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ParserException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (NoMatchingTokenDefinitionFound e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

    @Test
    public void testWithOneParameter() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testCall(1)");
	    MethodCall methodCall = new MethodCall();
	    DependencyInjection.inject(methodCall, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    methodCall.scan();
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ParserException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (NoMatchingTokenDefinitionFound e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
