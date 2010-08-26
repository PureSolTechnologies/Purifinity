package com.puresol.uhura.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTException;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TextConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
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

	private final Grammar uhuraGrammar;
	private Grammar readGrammar = null;
	private final Reader reader;
	private AST ast = null;

	private ConcurrentMap<String, Visibility> tokenVisibility = new ConcurrentHashMap<String, Visibility>();

	public GrammarReader(File file) throws IOException {
		this(new FileInputStream(file));
	}

	public GrammarReader(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}

	public GrammarReader(Reader reader) {
		this.reader = reader;
		uhuraGrammar = UhuraGrammar.getGrammar();
	}

	@Override
	public Boolean call() throws IOException, GrammarException {
		read();
		return true;
	}

	private void read() throws IOException, GrammarException {
		Lexer lexer = null;
		try {
			lexer = new RegExpLexer(new Properties());
			lexer.scan(reader, uhuraGrammar.getTokenDefinitions());
			TokenStream tokenStream = lexer.getTokenStream();
			ast = parse(tokenStream);
			convertToGrammer();
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

	private AST parse(TokenStream tokenStream) throws ParserException {
		try {
			Parser parser = new SLR1Parser(new Properties(), uhuraGrammar);
			parser.setTokenStream(tokenStream);
			return parser.call();
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertToGrammer() throws GrammarException {
		Properties options = convertToOptions();
		TokenDefinitionSet tokenDefinitions = convertToTokenDefinitionSet();
		ProductionSet productions = convertToProductionsSet(tokenDefinitions);
		readGrammar = new Grammar(options, tokenDefinitions, productions);
	}

	private Properties convertToOptions() {
		try {
			AST optionsTree = ast.getChild("Options");
			List<AST> grammarOptions = optionsTree.getSubTrees("GrammarOption");
			Properties options = new Properties();
			for (AST grammarOption : grammarOptions) {
				String name = grammarOption.getSubTrees("PropertiesIdentifier")
						.get(0).getText();
				String value = grammarOption.getSubTrees("Literal").get(0)
						.getText();
				if (value.startsWith("'") || value.startsWith("\"")) {
					value = value.substring(1, value.length() - 1);
				}
				options.put(name, value);
			}
			return options;
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private TokenDefinitionSet convertToTokenDefinitionSet()
			throws GrammarException {
		Map<String, List<AST>> helpers = getHelpers();
		Map<String, List<AST>> tokens = getTokens();
		TokenDefinitionSet tokenDefinitions = convertToTokenDefinitionSet(
				helpers, tokens);
		return tokenDefinitions;
	}

	private Map<String, List<AST>> getHelpers() {
		try {
			Map<String, List<AST>> helpers = new ConcurrentHashMap<String, List<AST>>();
			AST helperTree = ast.getChild("Helper");
			List<AST> helperDefinitions = helperTree
					.getSubTrees("HelperDefinition");
			for (AST helperDefinition : helperDefinitions) {
				String identifier = helperDefinition.getChild("IDENTIFIER")
						.getText();
				List<AST> tokenParts = helperDefinition
						.getSubTrees("TokenPart");
				helpers.put(identifier, tokenParts);
			}
			return helpers;
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private Map<String, List<AST>> getTokens() throws GrammarException {
		try {
			Map<String, List<AST>> tokens = new ConcurrentHashMap<String, List<AST>>();
			AST helperTree = ast.getChild("Tokens");
			List<AST> tokenDefinitions = helperTree
					.getSubTrees("TokenDefinition");
			for (AST tokenDefinition : tokenDefinitions) {
				// identifier...
				String identifier = tokenDefinition.getChild("IDENTIFIER")
						.getText();
				// token parts...
				List<AST> tokenParts = tokenDefinition.getSubTrees("TokenPart");
				tokens.put(identifier, tokenParts);
				// visibility...
				AST visibilityAST = tokenDefinition.getChild(
						"OptionalVisibility").getChild("IDENTIFIER");
				if (visibilityAST != null) {
					Visibility visibility = Visibility.fromName(visibilityAST
							.getText());
					if (visibility == null) {
						throw new GrammarException(
								"Invalid visibility attribute for '"
										+ tokenDefinition.toString() + "'!");
					}
					tokenVisibility.put(identifier, visibility);
				}
			}
			return tokens;
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private TokenDefinitionSet convertToTokenDefinitionSet(
			Map<String, List<AST>> helpers, Map<String, List<AST>> tokens)
			throws GrammarException {
		TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
		for (String tokenName : tokens.keySet()) {
			TokenDefinition tokenDefinition = getTokenDefinition(tokenName,
					helpers, tokens);
			tokenDefinitions.addDefinition(tokenDefinition);
		}
		return tokenDefinitions;
	}

	private TokenDefinition getTokenDefinition(String tokenName,
			Map<String, List<AST>> helpers, Map<String, List<AST>> tokens)
			throws GrammarException {
		try {
			StringBuffer pattern = new StringBuffer();
			List<AST> trees = tokens.get(tokenName);
			if (trees == null) {
				trees = helpers.get(tokenName);
			}
			if (trees == null) {
				throw new GrammarException("Unknown token '" + tokenName + "'.");
			}
			for (AST tree : trees) {
				if (tree.hasChild("STRING_LITERAL")) {
					String text = tree.getChild("STRING_LITERAL").getText();
					// text = text.replaceAll("\\\\n", "\\n");
					// text = text.replaceAll("\\\\r", "\\r");
					text = text.replaceAll("\\\\\\\\", "\\\\");
					pattern.append(text.substring(1, text.length() - 1));
				} else {
					String text = tree.getChild("IDENTIFIER").getText();
					text = getTokenDefinition(text, helpers, tokens)
							.getPattern().toString();
					pattern.append(text);
				}
				if (tree.hasChild("OptionalQuantifier")) {
					pattern.append(tree.getChild("OptionalQuantifier")
							.getText());
				}
			}
			if (tokenVisibility.get(tokenName) != null) {
				return new TokenDefinition(tokenName, pattern.toString(),
						tokenVisibility.get(tokenName));
			}
			return new TokenDefinition(tokenName, pattern.toString());
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private ProductionSet convertToProductionsSet(
			TokenDefinitionSet tokenDefinitions) {
		try {
			ProductionSet productions = new ProductionSet();
			AST productionsTree;
			productionsTree = ast.getChild("Productions");
			for (AST productionDefinition : productionsTree
					.getSubTrees("ProductionDefinition")) {
				productions.add(getProduction(productionDefinition,
						tokenDefinitions));
			}
			return productions;
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private List<Production> getProduction(AST productionDefinition,
			TokenDefinitionSet tokenDefinitions) {
		try {
			List<Production> productions = new CopyOnWriteArrayList<Production>();
			String productionName = productionDefinition.getChild("IDENTIFIER")
					.getText();
			for (AST productionConstruction : productionDefinition
					.getSubTrees("ProductionConstruction")) {
				if (!productionConstruction.getParent().getName()
						.equals("ProductionConstructions")) {
					// only top constructions are used for sub trees...
					continue;
				}
				AST alternativeIdentifier = productionConstruction.getChild(
						"AlternativeIdentifier").getChild("IDENTIFIER");
				Production production;
				if (alternativeIdentifier == null) {
					production = new Production(productionName);
				} else {
					production = new Production(productionName,
							alternativeIdentifier.getText());
				}
				for (AST productionPart : productionConstruction
						.getSubTrees("ProductionPart")) {
					if (productionPart.hasChild("IDENTIFIER")) {
						String identifier = productionPart.getChild(
								"IDENTIFIER").getText();
						if (tokenDefinitions.getDefinition(identifier) != null) {
							production.addElement(new TokenConstruction(
									identifier));
						} else {
							production.addElement(new ProductionConstruction(
									identifier));
						}
					} else {
						String text = productionPart.getChild("STRING_LITERAL")
								.getText();
						text = text.substring(1, text.length() - 1);
						production.addElement(new TextConstruction(text));
					}
				}
				productions.add(production);
			}
			return productions;
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	public Grammar getGrammar() {
		return readGrammar;
	}

	public AST getSyntaxTree() {
		return ast;
	}
}
