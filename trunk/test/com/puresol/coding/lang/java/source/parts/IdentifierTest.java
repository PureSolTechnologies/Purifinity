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

public class IdentifierTest extends TestCase {

    @Test
    public void testVariableName() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testVariable");
	    Identifier methodCall = new Identifier();
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
    public void testSuperDotVariable() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "super.testVariable");
	    Identifier methodCall = new Identifier();
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
    public void testThisDotVariable() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "this.testVariable");
	    Identifier methodCall = new Identifier();
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
    public void testDotDotVariable() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "com.puresol.testVariable");
	    Identifier methodCall = new Identifier();
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
    public void testsuperDotMethodCall() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "super.test()");
	    Identifier methodCall = new Identifier();
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
