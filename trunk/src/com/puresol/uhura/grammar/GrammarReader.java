package com.puresol.uhura.grammar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

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
 * This reader reads the grammar files and generates a Properties field with all
 * grammar properties, a TokenDefinitionSet with all token definitions found and
 * a ProductionSet with all BNF productions converted from the EBNF in the
 * grammar file.
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
	private Map<String, Visibility> tokenVisibility = new HashMap<String, Visibility>();

	/**
	 * Constructor for file reading.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public GrammarReader(File file) throws IOException {
		this(new FileInputStream(file));
	}

	/**
	 * Constructor for InputStream reading.
	 * 
	 * @param inputStream
	 */
	public GrammarReader(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}

	/**
	 * Constructor taking a reader for reading the grammar.
	 * 
	 * @param reader
	 */
	public GrammarReader(Reader reader) {
		this.reader = reader;
	}

	/**
	 * This method returns the read grammar.
	 * 
	 * @return
	 */
	public Grammar getGrammar() {
		return readGrammar;
	}

	/**
	 * This method returns the syntax tree from the read grammar file to
	 * retrieve additional information if needed.
	 * 
	 * @return
	 */
	public AST getAST() {
		return ast;
	}

	/**
	 * This is the central starting point for the reading process itself. It's
	 * used as a callable.
	 */
	@Override
	public Boolean call() throws IOException, GrammarException {
		read();
		return true;
	}

	/**
	 * This is the central reading routine which starts all sub routines like
	 * lexer, parser and converter.
	 * 
	 * @throws IOException
	 * @throws GrammarException
	 */
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
			convert();
			logger.debug("done.");
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new IOException(e.getMessage());
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = "Error while reading grammar file at: "
					+ e.getToken().getMetaData() + ": " + e.getToken();
			logger.error(errorMsg);
			throw new IOException(errorMsg);
		}
	}

	/**
	 * This method does the parsing and reacts appropriately to any exceptions.
	 * 
	 * @param tokenStream
	 * @throws ParserException
	 */
	private void parse(TokenStream tokenStream) throws ParserException {
		try {
			Parser parser = new SLR1Parser(uhuraGrammar);
			ast = parser.parse(tokenStream);
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * This method converts the read AST from grammar file into a final grammar
	 * ready to be used.
	 * 
	 * @throws GrammarException
	 */
	private void convert() throws GrammarException {
		convertOptions();
		convertTokenDefinitionSet();
		convertProductionsSet();
		readGrammar = new Grammar(options, tokenDefinitions, productions);
	}

	/**
	 * This method converts the options part of the AST.
	 */
	private void convertOptions() {
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

	/**
	 * This method converts the token definitions to the token definition set.
	 * 
	 * @throws GrammarException
	 */
	private void convertTokenDefinitionSet() throws GrammarException {
		tokenVisibility.clear();
		Map<String, List<AST>> helpers = getHelpers();
		Map<String, List<AST>> tokens = getTokens();
		convertTokenDefinitionSet(helpers, tokens);
	}

	/**
	 * This method reads all helpers from AST and returns a map with all of
	 * them.
	 * 
	 * @return
	 */
	private Map<String, List<AST>> getHelpers() {
		try {
			Map<String, List<AST>> helpers = new HashMap<String, List<AST>>();
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

	/**
	 * This method reads all tokens from AST and returns a map with all of them.
	 * 
	 * @return
	 */
	private Map<String, List<AST>> getTokens() throws GrammarException {
		try {
			Map<String, List<AST>> tokens = new HashMap<String, List<AST>>();
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
				if (visibilityAST.hasChild("HIDE")) {
					tokenVisibility.put(identifier, Visibility.HIDDEN);
				} else if (visibilityAST.hasChild("IGNORE")) {
						tokenVisibility.put(identifier, Visibility.IGNORED);
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

	/**
	 * This method starts and controls the process for final conversion of all
	 * tokens from the token and helper skeletons.
	 * 
	 * @param helpers
	 * @param tokens
	 * @throws GrammarException
	 */
	private void convertTokenDefinitionSet(Map<String, List<AST>> helpers,
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

	/**
	 * This is the method which merges all tokens with their helpers.
	 * 
	 * @param tokenName
	 * @param helpers
	 * @param tokens
	 * @return
	 * @throws GrammarException
	 */
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

	/**
	 * This method converts all productions from the AST into a ProductionSet.
	 */
	private void convertProductionsSet() {
		try {
			productions = new ProductionSet();
			AST productionsTree = ast.getChild("Productions");
			AST productionDefinitions = productionsTree
					.getChild("ProductionDefinitions");
			for (AST productionDefinition : productionDefinitions
					.getChildren("ProductionDefinition")) {
				convertProductionGroup(productionDefinition);
			}
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * This method converts a production group (bind together by the vertical
	 * bar '|') into a set of productions.
	 * 
	 * @param productionDefinition
	 * @throws GrammarException
	 */
	private void convertProductionGroup(AST productionDefinition)
			throws GrammarException {
		try {
			String productionName = productionDefinition.getChild("IDENTIFIER")
					.getText();
			AST productionConstructions = productionDefinition
					.getChild("ProductionConstructions");
			convertSingleProductions(productionName, productionConstructions);
		} catch (ASTException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * This method converts the list of productions from a group with a given
	 * name into a set of productions.
	 * 
	 * @param productionName
	 * @param productionConstructions
	 * @throws ASTException
	 * @throws GrammarException
	 */
	private void convertSingleProductions(String productionName,
			AST productionConstructions) throws ASTException, GrammarException {
		for (AST productionConstruction : productionConstructions
				.getChildren("ProductionConstruction")) {
			convertSingleProduction(productionName, productionConstruction);
		}
	}

	/**
	 * This method converts a single production from a list of productions
	 * within a production group into a single production. Some additional
	 * productions might be created during the way due to construction grouping
	 * and quantifiers.
	 * 
	 * The alternative name for a production is processed here, too.
	 * 
	 * @param productionName
	 * @param productionConstruction
	 * @throws ASTException
	 * @throws GrammarException
	 */
	private void convertSingleProduction(String productionName,
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
		List<Construction> constructions = new ArrayList<Construction>();
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
			return generateExtraQuantifierRules(productionPart, quantity);
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
					terminal = new Terminal(tokenDefinition.getName());
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
			String newIdentifier = createNewIdentifier(productionPart, "group");
			convertSingleProductions(newIdentifier, productionConstructions);
			return new NonTerminal(newIdentifier);
		} else {
			throw new ASTException("Invalid node '" + productionPart + "'!");
		}
	}

	private Construction generateExtraQuantifierRules(AST productionPart,
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

	/**
	 * This method creates a new BNF for a construction which is marked with '?'
	 * to be use zero or one time.
	 * 
	 * @param productionPart
	 * @return
	 * @throws ASTException
	 */
	private Construction generateOptionalProduction(AST productionPart)
			throws ASTException {
		String newIdentifier = createNewIdentifier(productionPart, "opt");
		try {
			Construction construction = createConstruction(productionPart);

			Production production = new Production(newIdentifier);
			production.addConstruction(construction);
			production.setNode(false);
			production.setStackingAllowed(false);
			productions.add(production);

			production = new Production(newIdentifier);
			production.setNode(false);
			production.setStackingAllowed(false);
			productions.add(production);

			return new NonTerminal(newIdentifier);
		} catch (GrammarException e) {
			throw new ASTException("Could not generate extra rules '"
					+ newIdentifier + "'!");
		}
	}

	/**
	 * This method generates a new construction identifier for an automatically
	 * generated BNF production for optionals, optional lists and lists.
	 * 
	 * @param productionPart
	 * @param suffix
	 * @return
	 * @throws ASTException
	 */
	private String createNewIdentifier(AST productionPart, String suffix)
			throws ASTException {
		String identifier = "";
		if (productionPart.hasChild("IDENTIFIER")) {
			identifier = productionPart.getChild("IDENTIFIER").getText();
		} else if (productionPart.hasChild("STRING_LITERAL")) {
			identifier = productionPart.getChild("STRING_LITERAL").getText();
		}
		int id = 1;
		String newIdentifier = createIdentifierName(identifier, suffix, id);
		while (productions.has(newIdentifier)) {
			id++;
			newIdentifier = createIdentifierName(identifier, suffix, id);
		}
		return newIdentifier;
	}

	private String createIdentifierName(String identifier, String suffix, int id) {
		return identifier + "_" + suffix + "_autogen_" + id;
	}

	/**
	 * This method creates a new BNF for a construction which is marked with '*'
	 * to be use zero or more times.
	 * 
	 * @param productionPart
	 * @return
	 * @throws ASTException
	 */
	private Construction generateOptionalList(AST productionPart)
			throws ASTException {
		String newIdentifier = createNewIdentifier(productionPart, "optlist");
		try {
			Construction construction = createConstruction(productionPart);

			Production production = new Production(newIdentifier);
			production.addConstruction(new NonTerminal(newIdentifier));
			production.addConstruction(construction);
			production.setNode(false);
			production.setStackingAllowed(false);
			productions.add(production);

			production = new Production(newIdentifier);
			production.setNode(false);
			production.setStackingAllowed(false);
			productions.add(production);

			return new NonTerminal(newIdentifier);
		} catch (GrammarException e) {
			throw new ASTException("Could not generate extra rules '"
					+ newIdentifier + "'!");
		}
	}

	/**
	 * This method creates a new BNF for a construction which is marked with '+'
	 * to be use one or more times.
	 * 
	 * @param productionPart
	 * @return
	 * @throws ASTException
	 */
	private Construction generateList(AST productionPart) throws ASTException {
		String newIdentifier = createNewIdentifier(productionPart, "list");
		try {
			Construction construction = createConstruction(productionPart);

			Production production = new Production(newIdentifier);
			production.addConstruction(new NonTerminal(newIdentifier));
			production.addConstruction(construction);
			production.setNode(false);
			production.setStackingAllowed(false);
			productions.add(production);

			production = new Production(newIdentifier);
			production.addConstruction(construction);
			production.setNode(false);
			production.setStackingAllowed(false);
			productions.add(production);

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
			for (AST option : optionalOptions.getSubTrees("Option")) {
				if (option.hasChild("NODE")) {
					production.setNode(Boolean.valueOf(option.getChild(
							"BOOLEAN_LITERAL").getText()));
				}
				if (option.hasChild("STACK")) {
					production.setStackingAllowed(Boolean.valueOf(option
							.getChild("BOOLEAN_LITERAL").getText()));
				}
			}
		}
	}
}
