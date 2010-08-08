package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.analysis.SourceCodeLexer;
import com.puresol.coding.lang.java.Java;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.lexer.NoMatchingTokenDefinitionFound;

public class JavaGrammarTester {

	public static boolean valid(String string,
			Class<? extends Parser> grammarPart) {
		return new JavaGrammarTester(string, grammarPart).valid();
	}

	public static boolean invalid(String string,
			Class<? extends Parser> grammarPart) {
		return new JavaGrammarTester(string, grammarPart).invalid();
	}

	private final String string;
	private final Class<? extends Parser> grammarPart;

	private JavaGrammarTester(String string, Class<? extends Parser> grammarPart) {
		this.string = string;
		this.grammarPart = grammarPart;
	}

	private boolean valid() {
		try {
			SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
					getClass().getSimpleName(), string);
			Parser parser = AbstractAnalyser.createParserInstance(grammarPart,
					lexer.getTokenStream());
			parser.scan();
			return parser.getText().equals(string);
		} catch (LexerException e) {
			e.printStackTrace();
		} catch (PartDoesNotMatchException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (NoMatchingTokenDefinitionFound e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean invalid() {
		try {
			SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
					getClass().getSimpleName(), string);
			Parser parser = AbstractAnalyser.createParserInstance(grammarPart,
					lexer.getTokenStream());
			parser.scan();
			return !parser.getText().equals(string);
		} catch (LexerException e) {
		} catch (PartDoesNotMatchException e) {
		} catch (ParserException e) {
		} catch (NoMatchingTokenDefinitionFound e) {
		}
		return true; // was expected!
	}
}
