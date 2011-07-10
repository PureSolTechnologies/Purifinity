package com.puresol.uhura.parser.packrat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;

import com.puresol.trees.TreeException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.parser.AbstractParser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.TextUtils;

/**
 * This is a complete implementation of a packrat parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PackratParser extends AbstractParser {

	private static final long serialVersionUID = -2004344389320369178L;

	private class ParseStatus {
		public static final int NOT_PROCESSED = 0;
		public static final int SUCCEEDED = 1;
		public static final int IN_PROCESS = 2;
		public static final int IN_RECURSION = 3;
		public static final int FAILED = 4;

		private final int status;
		private int counter = 1;

		public ParseStatus(int status) {
			super();
			this.status = status;
		}

		public ParseStatus(int status, int counter) {
			super();
			this.status = status;
			this.counter = counter;
		}

		public int getStatus() {
			return status;
		}

		public void decCounter() {
			counter--;
		}

		public int getCounter() {
			return counter;
		}
	}

	private class ParserStackElement {
		private final int position;
		private final int id;
		private final int line;
		private final ParserTree parserTree;

		public ParserStackElement(int position, int id, int length,
				ParserTree parserTree) {
			super();
			this.position = position;
			this.id = id;
			this.line = length;
			this.parserTree = parserTree;
		}

		public int getPosition() {
			return position;
		}

		public int getId() {
			return id;
		}

		public int getLine() {
			return line;
		}

		public ParserTree getParserTree() {
			return parserTree;
		}

	}

	private final Set<TokenDefinition> hiddenAndIgnoredTokens = new LinkedHashSet<TokenDefinition>();
	private final Map<Integer, Map<Production, ParserTree>> memo = new HashMap<Integer, Map<Production, ParserTree>>();
	private final Map<Integer, Map<Production, ParseStatus>> states = new HashMap<Integer, Map<Production, ParseStatus>>();
	private final Stack<ParserStackElement> parserStack = new Stack<ParserStackElement>();
	private final Boolean ignoredLeading;

	private String text;
	private String name;
	private int position = 0;
	private int id = 0;
	private int line = 1;

	public PackratParser(Grammar grammar) {
		super(grammar);
		extractHiddenAndIgnoredTokensFromGrammar();
		Properties options = grammar.getOptions();
		ignoredLeading = Boolean.valueOf(options.getProperty(
				"grammar.ignored-leading", "true"));

	}

	/**
	 * This method extracts all token definitions which are to be ignored or
	 * hidden to process them separately and to put them into special locations
	 * into the parser tree.
	 */
	private void extractHiddenAndIgnoredTokensFromGrammar() {
		for (TokenDefinition tokenDefinition : getGrammar()
				.getTokenDefinitions().getDefinitions()) {
			if ((tokenDefinition.getVisibility() == Visibility.HIDDEN)
					|| (tokenDefinition.getVisibility() == Visibility.IGNORED)) {
				hiddenAndIgnoredTokens.add(tokenDefinition);
			}
		}

	}

	/**
	 * This is the overridden part of the parser interface. A packrat parser
	 * does not need a lexer, therefore it can be used as a normal string input.
	 * In fact, this method adds all string within all tokens to a complete
	 * string and passes everything on to the actual parsing process.
	 */
	@Override
	public ParserTree parse(LexerResult lexerResult) throws ParserException {
		return parse(lexerResultToString(lexerResult), lexerResult
				.getTokenStream().getName());
	}

	/**
	 * This is the actual parser start. Work is performed on StringBuffer
	 * object.
	 * 
	 * @param buffer
	 * @return
	 * @throws ParserException
	 */
	public ParserTree parse(String text, String name) throws ParserException {
		try {
			memo.clear();
			states.clear();
			parserStack.clear();
			this.text = text;
			this.name = name;
			this.position = 0;
			this.id = 0;
			this.line = 1;
			ParserTree tree = parse("_START_");
			if ((tree == null) || (position != text.length()))
				throw new ParserException("Could not parse the input string!");
			return tree;
		} catch (TreeException e) {
			throw new ParserException(e.getMessage(), e);
		}
	}

	/**
	 * This method converts the lexer result into a String.
	 * 
	 * @param lexerResult
	 * @return
	 */
	private String lexerResultToString(LexerResult lexerResult) {
		StringBuffer builder = new StringBuffer();
		for (Token token : lexerResult.getTokenStream()) {
			builder.append(token.getText());
		}
		return builder.toString();
	}

	private ParserTree parse(String productionName) throws ParserException,
			TreeException {
		List<Production> productions = getGrammar().getProductions().get(
				productionName);
		for (Production production : productions) {
			ParserTree tree = processProduction(production);
			if (tree != null) {
				return tree;
			}
		}
		return null;
	}

	private ParserTree processProduction(Production production)
			throws TreeException, ParserException {
		ParseStatus status = getStatus(production, position);
		switch (status.getStatus()) {
		case ParseStatus.FAILED:
			/*
			 * Parsing this production on the current position already failed,
			 * so we do not need to try it again and return null to show we do
			 * not proceed here successfully.
			 */
			return null;
		case ParseStatus.SUCCEEDED:
			/*
			 * Parsing this production on this position already succeeded and
			 * the result can be reused.
			 */
			return getMemo(production, position);
		case ParseStatus.IN_RECURSION:
		case ParseStatus.IN_PROCESS:
			/*
			 * This production is already in progress at this position, so we
			 * find ourself within an recursion! We have to deal with it
			 * gently... ;-)
			 */
			status.decCounter();

			if (status.getCounter() == 0)
				return null;
			if (status.getCounter() < 0)
				throw new ParserException("Parser implementation error!");
			/*
			 * we are in a recursion, but did not reach the max depth therefore,
			 * let's continue...
			 */
			break;
		case ParseStatus.NOT_PROCESSED:
			/*
			 * This production was never processed at this position before, so
			 * we do nothing and proceed with parsing...
			 */
			status = new ParseStatus(ParseStatus.IN_PROCESS);
			setStatus(production, position, status);
			break;
		default:
			/*
			 * Ok, here is something wrong now and we need to throw an
			 * exception...
			 */
			throw new ParserException("Unknown status '" + status.getStatus()
					+ "'!");
		}
		int savedPosition = position;
		ParserTree parserTree = _processProduction(production);
		if (parserTree != null) {
			setMemo(production, savedPosition, parserTree);
			setStatus(production, savedPosition, new ParseStatus(
					ParseStatus.SUCCEEDED));
		} else {
			if ((status.getCounter() == 0)
					&& (status.getStatus() == ParseStatus.IN_PROCESS)) {
				System.out.println("Found recursion!!!");
				parserTree = processProductionRecursion(production);
				if (parserTree == null) {
					setStatus(production, savedPosition, new ParseStatus(
							ParseStatus.FAILED));
				} else {
					setStatus(production, savedPosition, new ParseStatus(
							ParseStatus.SUCCEEDED));
				}
			} else {
				setStatus(production, savedPosition, new ParseStatus(
						ParseStatus.FAILED));
			}
		}
		return parserTree;
	}

	private ParserTree processProductionRecursion(Production production)
			throws TreeException, ParserException {
		boolean finished = false;
		int counter = 1;
		ParserTree parserTree = null;
		do {
			pushStatus(parserTree);
			counter++;
			setStatus(production, position, new ParseStatus(
					ParseStatus.IN_RECURSION, counter));
			parserTree = _processProduction(production);
			if (parserTree == null) {
				parserTree = popStatus();
				finished = true;
			} else {
				popStatus();
			}
		} while (!finished);
		return parserTree;
	}

	private ParserTree _processProduction(Production production)
			throws TreeException, ParserException {
		ParserTree parseTree = new ParserTree(production);
		for (Construction construction : production.getConstructions()) {
			if (ignoredLeading) {
				processIgnoredTokens(parseTree);
			}
			if (construction.isNonTerminal()) {
				if (!processNonTerminal(parseTree, (NonTerminal) construction))
					return null;
			} else {
				if (!processTerminal(parseTree, (Terminal) construction))
					return null;
			}
			if (!ignoredLeading) {
				processIgnoredTokens(parseTree);
			}
		}
		return parseTree;
	}

	private boolean processNonTerminal(ParserTree parseTree,
			NonTerminal nonTerminal) throws ParserException, TreeException {
		ParserTree tree = parse(nonTerminal.getName());
		if (tree == null) {
			return false;
		}
		parseTree.addChild(tree);
		return true;
	}

	private boolean processTerminal(ParserTree parserTree, Terminal terminal)
			throws TreeException {
		TokenDefinitionSet tokenDefinitions = getGrammar()
				.getTokenDefinitions();
		TokenDefinition tokenDefinition = tokenDefinitions
				.getDefinition(terminal.getName());
		return processTokenDefinition(parserTree, tokenDefinition);
	}

	private void processIgnoredTokens(ParserTree parserTree)
			throws TreeException {
		boolean changed = false;
		do {
			changed = false;
			for (TokenDefinition tokenDefinition : hiddenAndIgnoredTokens) {
				if (processTokenDefinition(parserTree, tokenDefinition)) {
					changed = true;
					break;
				}
			}
		} while (changed);
	}

	/**
	 * This method tries to process a single token definition. If this can be
	 * done, true is returned, a new parser tree child is added and all internal
	 * states are updated like position, id and line.
	 * 
	 * @param parserTree
	 * @param tokenDefinition
	 * @return
	 * @throws TreeException
	 */
	private boolean processTokenDefinition(ParserTree parserTree,
			TokenDefinition tokenDefinition) throws TreeException {
		Matcher matcher = tokenDefinition.getPattern().matcher(
				text.substring(position));
		if (!matcher.find())
			return false;
		String match = matcher.group();
		int lineBreakNum = TextUtils.countLineBreaks(match);
		Token token = new Token(tokenDefinition.getName(), match,
				tokenDefinition.getVisibility(), new TokenMetaData(name, id,
						position, line, lineBreakNum + 1));
		parserTree.addChild(new ParserTree(token));
		position += match.length();
		id++;
		line += lineBreakNum;
		return true;
	}

	/**
	 * This method pushes a parser tree onto the stack with a current parser
	 * tree and all status information like position, id and line.
	 * 
	 * @param parserTree
	 */
	private void pushStatus(ParserTree parserTree) {
		parserStack
				.push(new ParserStackElement(position, id, line, parserTree));
	}

	/**
	 * This method pops a parser tree from the stack and restores all status
	 * information like position, id and line.
	 * 
	 * @return
	 */
	private ParserTree popStatus() {
		ParserStackElement parserStatus = parserStack.pop();
		position = parserStatus.getPosition();
		id = parserStatus.getId();
		line = parserStatus.getLine();
		return parserStatus.getParserTree();
	}

	private ParseStatus getStatus(Production production, int position) {

		Map<Production, ParseStatus> map = states.get(position);
		if (map == null) {
			return new ParseStatus(ParseStatus.NOT_PROCESSED);
		}
		ParseStatus status = map.get(production);
		if (status == null)
			return new ParseStatus(ParseStatus.NOT_PROCESSED);
		return status;
	}

	private void setStatus(Production production, int position,
			ParseStatus status) {
		Map<Production, ParseStatus> map = states.get(position);
		if (map == null) {
			map = new HashMap<Production, ParseStatus>();
			states.put(position, map);
		}
		map.put(production, status);
	}

	private ParserTree getMemo(Production production, int position) {
		Map<Production, ParserTree> map = memo.get(position);
		if (map == null) {
			return null;
		}
		return map.get(production);
	}

	private void setMemo(Production production, int position,
			ParserTree parseTree) {
		Map<Production, ParserTree> map = memo.get(position);
		if (map == null) {
			map = new HashMap<Production, ParserTree>();
			memo.put(position, map);
		}
		map.put(production, parseTree);
	}

	@Override
	public void generateInspectionInformation(File directory)
			throws IOException, GrammarException {
		// TODO nothing to do here!? check that!
	}

}
