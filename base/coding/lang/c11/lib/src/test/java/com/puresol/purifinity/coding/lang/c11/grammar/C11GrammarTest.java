package com.puresol.purifinity.coding.lang.c11.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.InputStream;
import java.net.URL;
import java.util.GregorianCalendar;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarConverter;
import com.puresol.purifinity.uhura.grammar.GrammarFile;

public class C11GrammarTest {

	@BeforeClass
	public static void initialize() {
		URL url = C11Grammar.class.getResource(C11Grammar.GRAMMAR_RESOURCE);
		assertNotNull(url);
	}

	@Test
	public void testUncachesGrammarFile() throws Exception {
		InputStream stream = C11Grammar.class
				.getResourceAsStream(C11Grammar.GRAMMAR_RESOURCE);
		try {
			GrammarFile file = new GrammarFile(stream);
			try {
				GrammarConverter grammarConverter = new GrammarConverter(
						file.getAST());
				Grammar grammar = grammarConverter.getGrammar();
				assertNotNull(grammar);
			} finally {
				file.close();
			}
		} finally {
			stream.close();
		}
	}

	@Test
	public void testInstance() throws Exception {
		// This test is ignored and need to be resolved later.
		Assume.assumeTrue(new GregorianCalendar(2013, 6, 1)
				.before(GregorianCalendar.getInstance()));
		C11Grammar c11Grammar = C11Grammar.getInstance();
		assertNotNull(c11Grammar);
		Grammar grammar = C11Grammar.getGrammar();
		assertNotNull(grammar);
		assertNotNull(c11Grammar.getLexer());
		assumeTrue(new GregorianCalendar(2013, 07, 01).before(GregorianCalendar
				.getInstance()));
		assertNotNull(c11Grammar.getParser());
	}

	@Test
	public void testPrint() {
		Grammar grammar = C11Grammar.getInstance();
		System.out.println(grammar);
	}

}
