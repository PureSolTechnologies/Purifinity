package com.puresol.uhura.grammar;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puresol.uhura.parser.ParserTree;

public class GrammarFileTest {

    private InputStream inputStream = null;

    @Before
    public void testInputStreamAndSetup() {
	inputStream = getClass().getResourceAsStream(
		"/com/puresol/uhura/grammar/TestGrammar.g");
	assertNotNull(inputStream);
    }

    @Test
    public void testRead() throws Throwable {
	assertNotNull(inputStream);
	GrammarFile file = new GrammarFile(inputStream);
	try {
	    ParserTree ast = file.getAST();
	    assertNotNull(ast);
	} finally {
	    file.close();
	}
    }

    @After
    public void teardown() throws Throwable {
	inputStream.close();
    }
}