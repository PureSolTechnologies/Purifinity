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

public class VariableTest extends TestCase {

    @Test
    public void testVariable() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testVariable");
	    Variable variable = new Variable();
	    DependencyInjection.inject(variable, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    variable.scan();
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
    public void testVariableConstArray() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testVariable[42]");
	    Variable variable = new Variable();
	    DependencyInjection.inject(variable, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    variable.scan();
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
    public void testVariableArrayVariable() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testVariable[i]");
	    Variable variable = new Variable();
	    DependencyInjection.inject(variable, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    variable.scan();
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
    public void testVariableArrayTerm() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "testVariable[(i + 2) / 2]");
	    Variable variable = new Variable();
	    DependencyInjection.inject(variable, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    variable.scan();
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
