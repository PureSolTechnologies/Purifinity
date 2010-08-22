package com.puresol.uhura.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.Callable;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.uhura.UhuraGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.lr.SLR1Parser;

public class GrammarReader implements Callable<Boolean> {

	private final TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
	private final ProductionSet productions = new ProductionSet();
	private final UhuraGrammar grammar;
	private final Properties options = new Properties();
	private final Reader reader;
	private SyntaxTree syntaxTree;

	public GrammarReader(File file) throws IOException {
		this(new FileInputStream(file));
	}

	public GrammarReader(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}

	public GrammarReader(Reader reader) {
		this.reader = reader;
		try {
			grammar = new UhuraGrammar();
		} catch (GrammarException e) {
			throw new RuntimeException(
					"Uhura grammar file grammar is not working!");
		}
	}

	@Override
	public Boolean call() throws LexerException, ParserException {
		read();
		return true;
	}

	private void read() throws ParserException, LexerException {
		Lexer lexer = new RegExpLexer(new Properties());
		lexer.scan(reader, grammar.getTokenDefinitions());
		TokenStream tokenStream = lexer.getTokenStream();
		syntaxTree = parse(tokenStream);
	}

	private SyntaxTree parse(TokenStream tokenStream) throws ParserException {
		Parser parser = new SLR1Parser(new Properties(), grammar);
		parser.setTokenStream(tokenStream);
		try {
			return parser.call();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParserException(e.getMessage());
		}
	}

	public Grammar getGrammar() {
		return grammar;
	}

	public TokenDefinitionSet getLexerRules() {
		return tokenDefinitions;
	}

	public ProductionSet getParserRules() {
		return productions;
	}

	public Properties getOptions() {
		return options;
	}

	public SyntaxTree getSyntaxTree() {
		return syntaxTree;
	}
}
