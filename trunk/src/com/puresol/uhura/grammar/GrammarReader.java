package com.puresol.uhura.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenDefinition;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserRule;
import com.puresol.uhura.parser.lr1.LR1Parser;

public class GrammarReader implements Callable<Boolean> {

	private final List<TokenDefinition> lexerRules = new ArrayList<TokenDefinition>();
	private final List<ParserRule> parserRules = new ArrayList<ParserRule>();
	private final Properties options = new Properties();
	private final Reader reader;

	public GrammarReader(File file) throws IOException {
		this(new FileInputStream(file));
	}

	public GrammarReader(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}

	public GrammarReader(Reader reader) {
		this.reader = reader;
	}

	@Override
	public Boolean call() throws LexerException, ParserException {
		read();
		return true;
	}

	private void read() throws ParserException, LexerException {
		Lexer lexer = new RegExpLexer(new Properties());
		lexer.scan(reader, new GrammarTokenDefinitionSet());
		TokenStream tokenStream = lexer.getTokenStream();
		parse(tokenStream);
	}

	private SyntaxTree parse(TokenStream tokenStream) throws ParserException {
		Parser parser = new LR1Parser(new Properties());
		parser.setTokenStream(tokenStream);
		parser.setRules(new GrammarParserRuleSet());
		try {
			return parser.call();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParserException(e.getMessage());
		}
	}

	public List<TokenDefinition> getLexerRules() {
		return lexerRules;
	}

	public List<ParserRule> getParserRules() {
		return parserRules;
	}

	public Properties getOptions() {
		return options;
	}
}
