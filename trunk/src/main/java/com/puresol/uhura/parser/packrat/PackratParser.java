package com.puresol.uhura.parser.packrat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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

	private final Set<TokenDefinition> hiddenAndIgnoredTokens = new LinkedHashSet<TokenDefinition>();
	private final Map<Integer, Map<Production, ParserStackElement>> memo = new HashMap<Integer, Map<Production, ParserStackElement>>();
	private final Map<Integer, Map<Production, ParserStatus>> states = new HashMap<Integer, Map<Production, ParserStatus>>();
	private final Boolean ignoredLeading;

	private String text;
	private String name;

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
			this.text = text;
			this.name = name;
			ParserTree parserTree = new ParserTree(name);
			ParserProgress progress = parse(parserTree, "_START_", 0, 0, 1);
			if (progress.getDeltaPosition() != text.length())
				throw new ParserException("Could not parse the input string!");
			return parserTree;
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

	private ParserProgress parse(ParserTree parserTree, String productionName,
			int position, int id, int line) throws ParserException,
			TreeException {
		List<Production> productions = getGrammar().getProductions().get(
				productionName);
		for (Production production : productions) {
			ParserProgress progress = processProduction(parserTree, production,
					position, id, line);
			if (progress.succeeded() || (foundRecursion(production, position))) {
				return progress;
			}
		}
		return ParserProgress.failure();
	}

	private boolean foundRecursion(Production production, int position) {
		ParserStatus status = getStatus(production, position);
		return (status.getCounter() == 0)
				&& (status.getStatus() == ParserStatus.IN_PROCESS);
	}

	private ParserProgress processProduction(ParserTree parserTree,
			Production production, int position, int id, int line)
			throws TreeException, ParserException {
		ParserStatus status = getStatus(production, position);
		System.out.println(status);
		System.out.println(production);
		System.out.println("pos: " + position + ", id: " + id);
		switch (status.getStatus()) {
		case ParserStatus.FAILED:
			/*
			 * Parsing this production on the current position already failed,
			 * so we do not need to try it again and return null to show we do
			 * not proceed here successfully.
			 */
			return ParserProgress.failure();
		case ParserStatus.SUCCEEDED:
			/*
			 * Parsing this production on this position already succeeded and
			 * the result can be reused.
			 */
			ParserStackElement stackElement = getMemo(production, position);
			parserTree.addChild(stackElement.getParserTree());
			return stackElement.getProgress();
		case ParserStatus.IN_PROCESS:
			/*
			 * We are processing here without knowing to be in a recursion. We
			 * need to set the counter to zero (decrease by one) to signal the
			 * detected recursion.
			 */
			status.decCounter();
			if (status.getCounter() == 0)
				return ParserProgress.failure();
			throw new ParserException("Parser implementation error!");
		case ParserStatus.IN_RECURSION:
			/*
			 * This production is already in progress at this position, so we
			 * find ourself within an recursion! We have to deal with it
			 * gently... ;-)
			 */
			status.decCounter();

			if (status.getCounter() == 0)
				return ParserProgress.failure();
			if (status.getCounter() < 0)
				throw new ParserException("Parser implementation error!");
			/*
			 * we are in a recursion, but did not reach the max depth therefore,
			 * let's continue...
			 */
			break;
		case ParserStatus.NOT_PROCESSED:
			/*
			 * This production was never processed at this position before, so
			 * we do nothing and proceed with parsing...
			 */
			status = new ParserStatus(ParserStatus.IN_PROCESS);
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
		ParserTree newParserTree = new ParserTree(production);
		ParserProgress progress = _processProduction(newParserTree, production,
				position, id, line);
		if (progress.succeeded()) {
			setMemo(production, position, new ParserStackElement(newParserTree,
					progress));
			setStatus(production, position, new ParserStatus(
					ParserStatus.SUCCEEDED));
			parserTree.addChild(newParserTree);
			return progress;
		}

		if ((status.getCounter() == 0)
				&& (status.getStatus() == ParserStatus.IN_PROCESS)) {
			System.out.println("Found recursion!!!");
			newParserTree = new ParserTree(production);
			progress = processProductionRecursion(newParserTree, production,
					position, id, line);
			if (progress.succeeded()) {
				setMemo(production, position, new ParserStackElement(
						newParserTree, progress));
				setStatus(production, position, new ParserStatus(
						ParserStatus.SUCCEEDED));
				parserTree.addChild(newParserTree);
				return progress;
			}
		}

		setStatus(production, position, new ParserStatus(ParserStatus.FAILED));
		return ParserProgress.failure();
	}

	private ParserProgress processProductionRecursion(ParserTree parserTree,
			Production production, int position, int id, int line)
			throws TreeException, ParserException {
		boolean finished = false;
		int counter = 0;
		ParserTree tree = null;
		ParserProgress progress = ParserProgress.failure();
		do {
			counter++;
			setStatus(production, position, new ParserStatus(
					ParserStatus.IN_RECURSION, counter));
			ParserTree newTree = new ParserTree(production);
			ParserProgress newProgress = _processProduction(newTree,
					production, position, id, line);
			if (newProgress.succeeded()) {
				progress = newProgress;
				tree = newTree;
			} else {
				finished = true;
			}
		} while (!finished);
		if (progress.succeeded()) {
			parserTree.addChild(tree);
		}
		return progress;
	}

	private ParserProgress _processProduction(ParserTree parserTree,
			Production production, int position, int id, int line)
			throws TreeException, ParserException {
		ParserProgress progress = ParserProgress.none();
		for (Construction construction : production.getConstructions()) {
			if (ignoredLeading) {
				ParserProgress newProgress = processIgnoredTokens(parserTree,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				progress.add(newProgress);
			}
			if (construction.isNonTerminal()) {
				ParserProgress newProgress = processNonTerminal(parserTree,
						(NonTerminal) construction,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				if (newProgress.succeeded()
						&& (!foundRecursion(production,
								position + progress.getDeltaPosition()))) {
					progress.add(newProgress);
				} else {
					return ParserProgress.failure();
				}
			} else {
				ParserProgress newProgress = processTerminal(parserTree,
						(Terminal) construction,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				if (newProgress.madeProgress()) {
					progress.add(newProgress);
				} else {
					return ParserProgress.failure();
				}
			}
			if (!ignoredLeading) {
				ParserProgress newProgress = processIgnoredTokens(parserTree,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				progress.add(newProgress);
			}
		}
		return progress;
	}

	private ParserProgress processNonTerminal(ParserTree parseTree,
			NonTerminal nonTerminal, int position, int id, int line)
			throws ParserException, TreeException {
		return parse(parseTree, nonTerminal.getName(), position, id, line);
	}

	private ParserProgress processTerminal(ParserTree parserTree,
			Terminal terminal, int position, int id, int line)
			throws TreeException {
		TokenDefinitionSet tokenDefinitions = getGrammar()
				.getTokenDefinitions();
		TokenDefinition tokenDefinition = tokenDefinitions
				.getDefinition(terminal.getName());
		return processTokenDefinition(parserTree, tokenDefinition, position,
				id, line);
	}

	ParserProgress processIgnoredTokens(ParserTree parserTree, int position,
			int id, int line) throws TreeException {
		ParserProgress progress = ParserProgress.none();
		ParserProgress newProgress = ParserProgress.none();
		do {
			for (TokenDefinition tokenDefinition : hiddenAndIgnoredTokens) {
				newProgress = processTokenDefinition(parserTree,
						tokenDefinition,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				if (newProgress.madeProgress()) {
					progress.add(newProgress);
					break;
				}
			}
		} while (newProgress.madeProgress());
		return progress;
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
	private ParserProgress processTokenDefinition(ParserTree parserTree,
			TokenDefinition tokenDefinition, int position, int id, int line)
			throws TreeException {
		Matcher matcher = tokenDefinition.getPattern().matcher(
				text.substring(position));
		if (!matcher.find()) {
			return ParserProgress.none();
		}
		String match = matcher.group();
		int lineBreakNum = TextUtils.countLineBreaks(match);
		Token token = new Token(tokenDefinition.getName(), match,
				tokenDefinition.getVisibility(), new TokenMetaData(name, id,
						position, line, lineBreakNum + 1));
		parserTree.addChild(new ParserTree(token));

		return ParserProgress.success(match.length(), 1, lineBreakNum);
	}

	private ParserStatus getStatus(Production production, int position) {
		Map<Production, ParserStatus> map = states.get(position);
		if (map == null) {
			return new ParserStatus(ParserStatus.NOT_PROCESSED);
		}
		ParserStatus status = map.get(production);
		if (status == null)
			return new ParserStatus(ParserStatus.NOT_PROCESSED);
		return status;
	}

	private void setStatus(Production production, int position,
			ParserStatus status) {
		Map<Production, ParserStatus> map = states.get(position);
		if (map == null) {
			map = new HashMap<Production, ParserStatus>();
			states.put(position, map);
		}
		map.put(production, status);
	}

	private ParserStackElement getMemo(Production production, int position) {
		Map<Production, ParserStackElement> map = memo.get(position);
		if (map == null) {
			return null;
		}
		return map.get(production);
	}

	private void setMemo(Production production, int position,
			ParserStackElement stackElement) {
		Map<Production, ParserStackElement> map = memo.get(position);
		if (map == null) {
			map = new HashMap<Production, ParserStackElement>();
			memo.put(position, map);
		}
		map.put(production, stackElement);
	}

	@Override
	public void generateInspectionInformation(File directory)
			throws IOException, GrammarException {
		// TODO nothing to do here!? check that!
	}

}
