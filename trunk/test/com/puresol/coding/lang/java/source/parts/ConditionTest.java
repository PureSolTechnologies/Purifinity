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

public class ConditionTest extends TestCase {

    @Test
    public void testSimpleCondition() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "a > b");
	    Condition term = new Condition();
	    DependencyInjection.inject(term, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    term.scan();
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
    public void testNotCondition() {
	try {
	    JavaLexer lexer = new JavaLexer("test", "!a");
	    Condition term = new Condition();
	    DependencyInjection.inject(term, Injection.named("TokenStream",
		    lexer.getTokenStream()));
	    term.scan();
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
