package com.puresol.uhura.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

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

	private static final Logger logger = Logger.getLogger(GrammarReader.class);

	private final TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
	private final ProductionSet productions = new ProductionSet();
	private final Grammar grammar;
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
		grammar = UhuraGrammar.getGrammar();
	}

	@Override
	public Boolean call() throws IOException {
		read();
		return true;
	}

	private void read() throws IOException {
		Lexer lexer = null;
		try {
			lexer = new RegExpLexer(new Properties());
			lexer.scan(reader, grammar.getTokenDefinitions());
			TokenStream tokenStream = lexer.getTokenStream();
			syntaxTree = parse(tokenStream);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new IOException(e.getMessage());
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			logger.error("Error while reading grammar file at: "
					+ lexer.getMetaInformation().getMetaData(e.getToken()));
			throw new IOException("Error while reading grammar file at: "
					+ lexer.getMetaInformation().getMetaData(e.getToken()));
		}
	}

	private SyntaxTree parse(TokenStream tokenStream) throws ParserException {
		try {
			Parser parser = new SLR1Parser(new Properties(), grammar);
			parser.setTokenStream(tokenStream);
			return parser.call();
		} catch (GrammarException e) {
			e.printStackTrace();
			throw new RuntimeException();
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
