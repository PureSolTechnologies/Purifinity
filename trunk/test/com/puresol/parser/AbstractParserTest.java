package com.puresol.parser;

import org.junit.Test;

import com.puresol.utils.di.DependencyInjection;
import com.puresol.utils.di.Injection;

import junit.framework.TestCase;

public class AbstractParserTest extends TestCase {

    class TestParser extends AbstractParser {

	private static final long serialVersionUID = 7239208305707642143L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
	}
    }

    @Test
    public void testDependencyInjection() {
	try {
	    TokenStream tokenStream = TokenStreamTest.newTestTokenStream();
	    TestParser parser = new TestParser();
	    DependencyInjection.inject(parser, Injection.named("TokenStream",
		    tokenStream));
	    assertSame(tokenStream, parser.getTokenStream());
	    parser.setStartPosition(1);
	    parser.setCurrentPosition(2);
	    Parser newParser = parser
		    .createParserInstance(AbstractParserTest.TestParser.class);
	    assertEquals(parser.getCurrentPosition(), newParser
		    .getStartPosition());
	    assertEquals(parser.getCurrentPosition(), newParser
		    .getCurrentPosition());
	    assertEquals(parser.getCurrentPosition(), newParser
		    .getEndPosition());
	} catch (ParserException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
