package com.puresol.uhura.parser.packrat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;

import com.puresol.trees.TreeException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.TextUtils;

/**
 * This is a complete implementation of a packrat parser.
 * 
 * <b>This class is not thread safe!</b>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PackratParser implements Serializable {

	private static final long serialVersionUID = -2004344389320369178L;

	private final Set<TokenDefinition> hiddenAndIgnoredTokens = new LinkedHashSet<TokenDefinition>();
	private final Map<Integer, Map<Production, ParserProgress>> memo = new HashMap<Integer, Map<Production, ParserProgress>>();
	private final Map<Integer, Map<Production, ParserStatus>> states = new HashMap<Integer, Map<Production, ParserStatus>>();
	private final Boolean ignoredLeading;

	private final Grammar grammar;
	private String text = "";
	private String name = "";

	public PackratParser(Grammar grammar) {
		super();
		this.grammar = grammar;
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
		for (TokenDefinition tokenDefinition : grammar.getTokenDefinitions()
				.getDefinitions()) {
			if ((tokenDefinition.getVisibility() == Visibility.HIDDEN)
					|| (tokenDefinition.getVisibility() == Visibility.IGNORED)) {
				hiddenAndIgnoredTokens.add(tokenDefinition);
			}
		}
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
			ParserProgress progress = parse("_START_", 0, 0, 1);
			if (progress.getDeltaPosition() != text.length())
				throw new ParserException("Could not parse the input string!");
			return progress.getTree();
		} catch (TreeException e) {
			throw new ParserException(e.getMessage(), e);
		}
	}

	private ParserProgress parse(String productionName, int position, int id,
			int line) throws ParserException, TreeException {
		ParserProgress myProgress = ParserProgress.none();
		for (Production production : grammar.getProductions().get(
				productionName)) {
			ParserProgress progress = processProduction(production, position,
					id, line);
			if (progress.compareTo(myProgress) > 0)
				myProgress = progress;
		}
		return myProgress;
	}

	private ParserProgress processProduction(Production production,
			int position, int id, int line) throws TreeException,
			ParserException {
		{
			ParserStatus status = getStatus(production, position);
			switch (status.getStatus()) {
			case ParserStatus.FAILED:
				/*
				 * Parsing this production on the current position already
				 * failed, so we do not need to try it again and return null to
				 * show we do not proceed here successfully.
				 */
				return ParserProgress.failure();
			case ParserStatus.SUCCEEDED:
				/*
				 * Parsing this production on this position already succeeded
				 * and the result can be reused.
				 */
				return getMemo(production, position);
			case ParserStatus.IN_PROCESS:
				/*
				 * Found left recursion and need to abort here...
				 */
				return ParserProgress.failure();
			case ParserStatus.NOT_PROCESSED:
				/*
				 * This production was never processed at this position before,
				 * so we do nothing and proceed with parsing...
				 */
				break;
			default:
				/*
				 * Ok, here is something wrong now and we need to throw an
				 * exception...
				 */
				throw new ParserException("Unknown status '"
						+ status.getStatus() + "'!");
			}
		}
		setStatus(production, position, new ParserStatus(ParserStatus.FAILED));
		setMemo(production, position, ParserProgress.failure());

		ParserProgress progress;
		ParserProgress memo = ParserProgress.failure();
		do {
			progress = parseProduction(production, position, id, line);
			if (progress.succeeded()) {
				if (progress.getDeltaPosition() > memo.getDeltaPosition()) {
					memo = progress;
					setMemo(production, position, memo);
					setStatus(production, position, new ParserStatus(
							ParserStatus.SUCCEEDED));
				} else {
					break;
				}
			}
		} while (progress.succeeded());
		return getMemo(production, position);
	}

	private ParserProgress parseProduction(Production production, int position,
			int id, int line) throws TreeException, ParserException {
		ParserTree node = new ParserTree(production);
		ParserProgress progress = ParserProgress.success(0, 0, 0, node);
		for (Construction construction : production.getConstructions()) {
			if (ignoredLeading) {
				ParserProgress newProgress = processIgnoredTokens(node,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				progress.add(newProgress);
			}
			if (construction.isNonTerminal()) {
				ParserProgress newProgress = processNonTerminal(
						(NonTerminal) construction,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				if (newProgress.madeProgress()) {
					node.addChild(newProgress.getTree());
					progress.add(newProgress);
				} else if (newProgress.failed())
					return ParserProgress.failure();
			} else {
				ParserProgress newProgress = processTerminal(node,
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
				ParserProgress newProgress = processIgnoredTokens(node,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				progress.add(newProgress);
			}
		}
		return progress;
	}

	private ParserProgress processNonTerminal(NonTerminal nonTerminal,
			int position, int id, int line) throws ParserException,
			TreeException {
		return parse(nonTerminal.getName(), position, id, line);
	}

	private ParserProgress processTerminal(ParserTree node, Terminal terminal,
			int position, int id, int line) throws TreeException {
		TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
		TokenDefinition tokenDefinition = tokenDefinitions
				.getDefinition(terminal.getName());
		return processTokenDefinition(node, tokenDefinition, position, id, line);
	}

	ParserProgress processIgnoredTokens(ParserTree node, int position, int id,
			int line) throws TreeException {
		ParserProgress progress = ParserProgress.success(0, 0, 0, node);
		ParserProgress newProgress = ParserProgress.none();
		do {
			for (TokenDefinition tokenDefinition : hiddenAndIgnoredTokens) {
				newProgress = processTokenDefinition(node, tokenDefinition,
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
	private ParserProgress processTokenDefinition(ParserTree node,
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
		ParserTree myTree = new ParserTree(token);
		node.addChild(myTree);
		return ParserProgress.success(match.length(), 1, lineBreakNum, myTree);
	}

	private ParserStatus getStatus(Production production, int position) {
		Map<Production, ParserStatus> map = states.get(position);
		if (map == null) {
			map = new HashMap<Production, ParserStatus>();
			states.put(position, map);
		}
		ParserStatus status = map.get(production);
		if (status == null) {
			status = new ParserStatus(ParserStatus.NOT_PROCESSED);
			map.put(production, status);
		}
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

	private ParserProgress getMemo(Production production, int position) {
		Map<Production, ParserProgress> map = memo.get(position);
		if (map == null) {
			return null;
		}
		return map.get(production);
	}

	private void setMemo(Production production, int position,
			ParserProgress stackElement) {
		Map<Production, ParserProgress> map = memo.get(position);
		if (map == null) {
			map = new HashMap<Production, ParserProgress>();
			memo.put(position, map);
		}
		map.put(production, stackElement);
	}
}
