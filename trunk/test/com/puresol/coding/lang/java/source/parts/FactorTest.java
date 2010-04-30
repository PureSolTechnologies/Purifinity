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

public class FactorTest extends TestCase {

    @Test
    public void testConstant() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "1.234");
	    Factor factor = new Factor();
	    DependencyInjection.inject(factor, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    factor.scan();
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
    public void testParens() {
	try {
	    Factor factor = new Factor();
	    JavaLexer lexer = new JavaLexer("test", "( 1 )");
	    DependencyInjection.inject(factor, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    factor.scan();
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ParserException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (NoMatchingTokenDefinitionFound e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

}
