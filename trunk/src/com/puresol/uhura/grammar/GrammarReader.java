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

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Terminal;
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

/**
 * This class is for reading Nyota Uhura grammar files. The grammar file is read
 * and interpreted. The resulting grammar can be used for processing with Nyota
 * Uhura afterwards.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarReader implements Callable<Boolean> {

	private static final Logger logger = Logger.getLogger(GrammarReader.class);

	private final Grammar uhuraGrammar = UhuraGrammar.getGrammar();;
	private final Reader reader;

	private Properties options = null;
	private TokenDefinitionSet tokenDefinitions = null;
	private ProductionSet productions = null;
	private Grammar readGrammar = null;
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
	}

	public Grammar getGrammar() {
		return readGrammar;
	}

	public AST getSyntaxTree() {
		return ast;
	}

	@Override
	public Boolean call() throws IOException, GrammarException {
		read();
		return true;
	}

	private void read() throws IOException, GrammarException {
		Lexer lexer = null;
		try {
			logger.debug("Read grammar file:");
			logger.debug("Starting lexer...");
			lexer = new RegExpLexer(uhuraGrammar);
			TokenStream tokenStream = lexer.lex(reader);
			logger.debug("Starting parser...");
			parse(tokenStream);
			logger.debug("Convert AST into grammar...");
			convertToGrammer();
			logger.debug("done.");
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

	private void parse(TokenStream tokenStream) throws ParserException {
		try {
			Parser parser = new SLR1Parser(uhuraGrammar);
			ast = parser.parse(tokenStream);
			if (logger.isTraceEnabled()) {
				new TreePrinter(System.out).println(ast);
			}
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertToGrammer() throws GrammarException {
		convertToOptions();
		convertToTokenDefinitionSet();
		convertToProductionsSet();
		readGrammar = new Grammar(options, tokenDefinitions, productions);
	}

	private void convertToOptions() {
		try {
			options = new Properties();
			AST optionsTree = ast.getChild("Options");
			for (AST grammarOption : optionsTree.getSubTrees("GrammarOption")) {
				String name = grammarOption.getSubTrees("PropertiesIdentifier")
						.get(0).getText();
				String value = grammarOption.getSubTrees("Literal").get(0)
						.getText();
				if (value.startsWith("'") || value.startsWith("\"")) {
					value = value.substring(1, value.length() - 1);
				}
				options.put(name, value);
			}
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertToTokenDefinitionSet() throws GrammarException {
		tokenVisibility.clear();
		Map<String, List<AST>> helpers = getHelpers();
		Map<String, List<AST>> tokens = getTokens();
		convertToTokenDefinitionSet(helpers, tokens);
	}

	private Map<String, List<AST>> getHelpers() {
		try {
			Map<String, List<AST>> helpers = new ConcurrentHashMap<String, List<AST>>();
			AST helperTree = ast.getChild("Helper");
			AST helperDefinitions = helperTree.getChild("HelperDefinitions");
			for (AST helperDefinition : helperDefinitions
					.getChildren("HelperDefinition")) {
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
			AST tokensTree = ast.getChild("Tokens");
			AST tokenDefinitions = tokensTree.getChild("TokenDefinitions");
			for (AST tokenDefinition : tokenDefinitions
					.getChildren("TokenDefinition")) {
				// identifier...
				String identifier = tokenDefinition.getChild("IDENTIFIER")
						.getText();
				// token parts...
				List<AST> tokenParts = tokenDefinition.getSubTrees("TokenPart");
				tokens.put(identifier, tokenParts);
				// visibility...
				AST visibilityAST = tokenDefinition
						.getChild("OptionalVisibility");
				if (visibilityAST.hasChild("HIDDEN")) {
					tokenVisibility.put(identifier, Visibility.HIDDEN);
				} else {
					tokenVisibility.put(identifier, Visibility.VISIBLE);
				}
			}
			return tokens;
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertToTokenDefinitionSet(Map<String, List<AST>> helpers,
			Map<String, List<AST>> tokens) throws GrammarException {
		try {
			tokenDefinitions = new TokenDefinitionSet();
			for (AST tokenDefinitionAST : ast.getChild("Tokens")
					.getChild("TokenDefinitions")
					.getChildren("TokenDefinition")) {
				tokenDefinitions.addDefinition(getTokenDefinition(
						tokenDefinitionAST.getChild("IDENTIFIER").getText(),
						helpers, tokens));
			}
		} catch (ASTException e) {
			logger.error(e.getMessage(), e);
			throw new GrammarException(e.getMessage());
		}
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
					text = getTokenDefinition(text, helpers, tokens).getText();
					pattern.append(text);
				}
				if (tree.hasChild("OptionalQuantifier")) {
					pattern.append(tree.getChild("OptionalQuantifier")
							.getText());
				}
			}
			boolean ignoreCase = Boolean.valueOf((String) options
					.get("grammar.ignore-case"));
			if (tokenVisibility.get(tokenName) != null) {
				return new TokenDefinition(tokenName, pattern.toString(),
						tokenVisibility.get(tokenName), ignoreCase);
			} else {
				return new TokenDefinition(tokenName, pattern.toString(),
						ignoreCase);
			}
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertToProductionsSet() {
		try {
			productions = new ProductionSet();
			AST productionsTree = ast.getChild("Productions");
			AST productionDefinitions = productionsTree
					.getChild("ProductionDefinitions");
			for (AST productionDefinition : productionDefinitions
					.getChildren("ProductionDefinition")) {
				convertProduction(productionDefinition);
			}
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertProduction(AST productionDefinition)
			throws GrammarException {
		try {
			String productionName = productionDefinition.getChild("IDENTIFIER")
					.getText();
			AST productionConstructions = productionDefinition
					.getChild("ProductionConstructions");
			convertProductionConstructions(productionName,
					productionConstructions);
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void convertProductionConstructions(String productionName,
			AST productionConstructions) throws ASTException, GrammarException {
		for (AST productionConstruction : productionConstructions
				.getChildren("ProductionConstruction")) {
			convertProductionConstruction(productionName,
					productionConstruction);
		}
	}

	private void convertProductionConstruction(String productionName,
			AST productionConstruction) throws ASTException, GrammarException {
		AST alternativeIdentifier = productionConstruction
				.getChild("AlternativeIdentifier");
		Production production;
		if (alternativeIdentifier == null) {
			production = new Production(productionName);
		} else {
			AST alternativeIdentifierName = alternativeIdentifier
					.getChild("IDENTIFIER");
			if (alternativeIdentifierName == null) {
				production = new Production(productionName);
			} else {
				production = new Production(productionName,
						alternativeIdentifierName.getText());
			}
		}
		production
				.addAllConstructions(getConstructions(productionConstruction));
		addOptions(production, productionConstruction);
		productions.add(production);
	}

	private List<Construction> getConstructions(AST productionConstruction)
			throws ASTException, GrammarException {
		List<Construction> constructions = new CopyOnWriteArrayList<Construction>();
		AST productionParts = productionConstruction
				.getChild("ProductionParts");
		if (productionParts != null) {
			for (AST productionPart : productionParts
					.getChildren("ProductionPart")) {
				constructions.add(getConstruction(productionPart,
						tokenDefinitions));
			}
		}
		return constructions;
	}

	private Construction getConstruction(AST productionPart,
			TokenDefinitionSet tokenDefinitions) throws ASTException,
			GrammarException {
		Quantity quantity = Quantity.fromSymbol(productionPart.getChild(
				"OptionalQuantifier").getText());
		if (quantity == Quantity.EXPECT) {
			return createConstruction(productionPart);
		} else {
			return generateExtraRules(productionPart, quantity);
		}
	}

	private Construction createConstruction(AST productionPart)
			throws ASTException, GrammarException {
		if (productionPart.hasChild("IDENTIFIER")) {
			String identifier = productionPart.getChild("IDENTIFIER").getText();
			if (tokenDefinitions.getDefinition(identifier) != null) {
				return new Terminal(identifier);
			} else {
				return new NonTerminal(identifier);
			}
		} else if (productionPart.hasChild("STRING_LITERAL")) {
			AST stringLiteral = productionPart.getChild("STRING_LITERAL");
			String text = stringLiteral.getText();
			text = text.substring(1, text.length() - 1);
			Terminal terminal = null;
			for (TokenDefinition tokenDefinition : tokenDefinitions
					.getDefinitions()) {
				if (tokenDefinition.getPattern().matcher(text).matches()) {
					if (terminal != null) {
						throw new GrammarException(
								"Token text '"
										+ text
										+ "' satisfies several token definitions and is therfore ambiguous!");
					}
					terminal = new Terminal(tokenDefinition.getName(), text);
				}
			}
			if (terminal == null) {
				throw new GrammarException("Token text '" + text
						+ "' does not satisfy any token definition!");
			}
			return terminal;
		} else if (productionPart.hasChild("ProductionConstructions")) {
			AST productionConstructions = productionPart
					.getChild("ProductionConstructions");
			String newIdentifier = createNewIdentifier(productionPart,
					"_group_autogen");
			convertProductionConstructions(newIdentifier,
					productionConstructions);
			return new NonTerminal(newIdentifier);
		} else {
			throw new ASTException("Invalid node '" + productionPart + "'!");
		}
	}

	private Construction generateExtraRules(AST productionPart,
			Quantity quantity) throws ASTException {
		if (quantity == Quantity.ACCEPT) {
			return generateOptionalProduction(productionPart);
		} else if (quantity == Quantity.ACCEPT_MANY) {
			return generateOptionalList(productionPart);
		} else if (quantity == Quantity.EXPECT_MANY) {
			return generateList(productionPart);
		}
		throw new ASTException("Impossible to generate extra rules for '"
				+ productionPart + "' with quantity '" + quantity + "'!");
	}

	private Construction generateOptionalProduction(AST productionPart)
			throws ASTException {
		String newIdentifier = createNewIdentifier(productionPart,
				"_opt_autogen");
		try {
			Construction construction = createConstruction(productionPart);
			if (!productions.has(newIdentifier)) {
				Production production = new Production(newIdentifier);
				production.addConstruction(construction);
				productions.add(production);

				production = new Production(newIdentifier);
				productions.add(production);
			}
			return new NonTerminal(newIdentifier);
		} catch (GrammarException e) {
			throw new ASTException("Could not generate extra rules '"
					+ newIdentifier + "'!");
		}
	}

	private String createNewIdentifier(AST productionPart, String suffix)
			throws ASTException {
		String newIdentifier = "";
		if (productionPart.hasChild("IDENTIFIER")) {
			newIdentifier = productionPart.getChild("IDENTIFIER").getText();
		} else if (productionPart.hasChild("STRING_LITERAL")) {
			newIdentifier = productionPart.getChild("STRING_LITERAL").getText();
		}
		int id = 1;
		while (productions.has(newIdentifier + id + suffix)) {
			id++;
		}
		return newIdentifier + id + suffix;
	}

	private Construction generateOptionalList(AST productionPart)
			throws ASTException {
		String newIdentifier = createNewIdentifier(productionPart,
				"_optlist_autogen");
		try {
			Construction construction = createConstruction(productionPart);
			if (!productions.has(newIdentifier)) {
				Production production = new Production(newIdentifier);
				production.addConstruction(new NonTerminal(newIdentifier));
				production.addConstruction(construction);
				productions.add(production);

				production = new Production(newIdentifier);
				productions.add(production);
			}
			return new NonTerminal(newIdentifier);
		} catch (GrammarException e) {
			throw new ASTException("Could not generate extra rules '"
					+ newIdentifier + "'!");
		}
	}

	private Construction generateList(AST productionPart) throws ASTException {
		String newIdentifier = createNewIdentifier(productionPart,
				"_list_autogen");
		try {
			Construction construction = createConstruction(productionPart);
			if (!productions.has(newIdentifier)) {
				Production production = new Production(newIdentifier);
				production.addConstruction(new NonTerminal(newIdentifier));
				production.addConstruction(construction);
				productions.add(production);

				production = new Production(newIdentifier);
				production.addConstruction(construction);
				productions.add(production);
			}
			return new NonTerminal(newIdentifier);
		} catch (GrammarException e) {
			throw new ASTException("Could not generate extra rules '"
					+ newIdentifier + "'!");
		}
	}

	private void addOptions(Production production, AST productionConstruction)
			throws ASTException {
		AST optionalOptions = productionConstruction
				.getChild("OptionalOptions");
		if (optionalOptions != null) {
			for (AST option : optionalOptions.getSubTrees("OptionalOption")) {
				if (option.hasChild("NODE")) {
					production.setNode(Boolean.valueOf(option.getChild(
							"BOOLEAN_LITERAL").getText()));
				}
			}
		}
	}
}
