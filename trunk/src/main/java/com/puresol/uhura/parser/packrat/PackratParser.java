package com.puresol.uhura.parser.packrat;

import java.io.Serializable;
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
	/**
	 * This is the memoization buffer to put all memoized data in.
	 */
	private final Map<Integer, Map<String, MemoEntry>> memo = new HashMap<Integer, Map<String, MemoEntry>>();
	/**
	 * This stack contains all rules which are currently processed and which are
	 * nested. The data is kept in left-recursive data elements to put data in
	 * here if needed for recursion detection and seed growing.
	 */
	private final Stack<LR> lrStack = new Stack<LR>();
	/**
	 * This is a list of all heads on all positions which are currently grown.
	 */
	private final Map<Integer, Head> heads = new HashMap<Integer, Head>();
	private final Boolean ignoredLeading;

	private final Grammar grammar;
	private String text = "";
	private String name = "";
	private int maxPosition = 0;

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
		List<TokenDefinition> tokenDefinitions = grammar.getTokenDefinitions()
				.getDefinitions();
		for (TokenDefinition tokenDefinition : tokenDefinitions) {
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
			lrStack.clear();
			this.text = text;
			this.name = name;
			maxPosition = 0;
			MemoEntry progress = applyRule("_START_", 0, 0, 1);
			if (progress.getDeltaPosition() != text.length()) {
				throw new ParserException(getParserErrorMessage());
			}
			return progress.getTree();
		} catch (TreeException e) {
			throw new ParserException(e.getMessage(), e);
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
	public ParserTree parse(String text, String production, String name)
			throws ParserException {
		try {
			memo.clear();
			lrStack.clear();
			this.text = text;
			this.name = name;
			maxPosition = 0;
			MemoEntry progress = applyRule(production, 0, 0, 1);
			if (progress.getDeltaPosition() != text.length()) {
				throw new ParserException(getParserErrorMessage());
			}
			return progress.getTree();
		} catch (TreeException e) {
			throw new ParserException(e.getMessage(), e);
		}
	}

	/**
	 * This message just generates a parser exception message to be returned
	 * containing the maximum position where the parser could not proceed. This
	 * should be in most cases the position where the error within the text is
	 * located.
	 * 
	 * @return
	 */
	private String getParserErrorMessage() {
		StringBuffer code = new StringBuffer(text);
		code = code.insert(maxPosition, " >><< ");
		String codeString = code.substring(maxPosition - 100 < 0 ? 0
				: maxPosition - 100,
				maxPosition + 100 >= code.length() ? code.length() - 1
						: maxPosition + 100);
		return "Could not parse the input string near " + codeString + "!";
	}

	private void printStackContent() {
		for (int i = 0; i < lrStack.size(); i++) {
			indentLine();
			for (int indent = 0; indent < i + 1; indent++) {
				System.out.print("  ");
			}
			System.out.println(lrStack.get(i).getProduction());
		}
	}

	private void indentLine() {
		for (int indent = 0; indent < lrStack.size(); indent++) {
			System.out.print("  ");
		}
	}

	private void printMessage(String text, int position, int id, int line) {
		indentLine();
		System.out.println(position + " " + id + " " + line + " : " + text);
	}

	/**
	 * This method tries to apply a production at a given position. The
	 * production is given as a name and not as a concrete rule to process all
	 * choices afterwards.
	 * 
	 * @param production
	 * @param position
	 * @return
	 * @throws TreeException
	 * @throws ParserException
	 */
	private MemoEntry applyRule(String production, int position, int id,
			int line) throws TreeException, ParserException {
		MemoEntry m = recall(production, position, id, line);
		printMessage(production, position, id, line);
		// printStackContent();
		if (m.getStatus() == Status.NONE) {
			/*
			 * "Create a new LR and push it onto the rule invocation stack."
			 * 
			 * At this point we found a rule which was never processed at this
			 * position.
			 */
			LR lr = new LR(MemoEntry.failure(), production, null,
					lrStack.size() == 0 ? null : lrStack.peek());
			lrStack.push(lr);
			/*
			 * "Memoize lr, then evaluate R."
			 * 
			 * Put a fail into memoization memory and evaluate the rule
			 * afterwards.
			 */
			m = MemoEntry.failure(lr);
			setMemo(production, position, id, line, m);
			MemoEntry ans = eval(production, position, id, line);
			/*
			 * "Pop lr off the rule invocation stack."
			 * 
			 * The evaluation of this lr is finished now and we can remove it
			 * from stack. This was needed in cases a left recursion whould be
			 * found within the rule.
			 */
			lrStack.pop();
			if (lr.getHead() != null) {
				/*
				 * If a head was set to lr, we found a recursion during
				 * evaluation. We need to set the seed and process with left
				 * recursion evaluation. For that purpose we grow m with ans as
				 * seed.
				 */
				printMessage("Found recursion for: " + production, position,
						id, line);
				lr.setSeed(ans);
				return lrAnswer(production, position, id, line, m);
			} else {
				m.set(ans);
				return ans;
			}
		} else {
			// ??? Pos <-- m.pos
			if (m.getLR() != null) {
				LR lr = m.getLR();
				setupLR(production, lr);
				MemoEntry seed = lr.getSeed();
				return seed;
			} else {
				return m;
			}
		}
	}

	private void setupLR(String production, LR l) throws ParserException {
		if (l == null) {
			throw new ParserException(
					"Error in Parser implementation. There shouldn't be a null pointer!");
		}
		if (l.getHead() == null) {
			l.setHead(new Head(production));
		}
		LR s = lrStack.peek();
		if (s == null) {
			throw new ParserException(
					"Error in Parser implementation. Stack must not be empty here!");
		}
		while ((s.getHead() == null)
				|| (!l.getHead().getProduction()
						.equals(s.getHead().getProduction()))) {
			s.setHead(l.getHead());
			s.getHead().setInvolved(s.getProduction());
			s = s.getNext();
			if (s == null)
				break;
		}
	}

	/**
	 * This method is an extended getMemo function which also takes into account
	 * the seed growing processes which might be underway.
	 * 
	 * @param production
	 *            is the currently processes production.
	 * @param position
	 *            is the current parser position.
	 * @return A new memo entry is returned containing the result of the
	 *         memoization buffer and seed growing status lookup.
	 * @throws TreeException
	 * @throws ParserException
	 */
	private MemoEntry recall(String production, int position, int id, int line)
			throws TreeException, ParserException {
		/*
		 * Retrieve the current memoized item for the production and the head on
		 * the current position.
		 */
		MemoEntry m = getMemo(production, position);
		Head h = heads.get(position);
		/*
		 * "If not growing a seed parse, just return what is stored in the memo
		 * table."
		 */
		if (h == null) {
			return m;
		}
		/*
		 * "Do not evaluate any rule that is not involved in this left recursion."
		 * 
		 * If we have never been at this position (NONE) and the head rule
		 * (production) is not the current production and the involved rules in
		 * the heads also do not fit, then we are on a wrong pass here. We need
		 * to return a failure.
		 */
		if ((m.getStatus() == Status.NONE)
				&& (!production.equals(h.getProduction()))
				&& (!h.getInvolvedSet().contains(production))) {
			return MemoEntry.failure();
		}
		/*
		 * "Allow involved rules to be evaluated, but only once, during a
		 * seed-growing iteration."
		 * 
		 * If we have been here already, we check the eval set for the
		 * containing rule and evaluate it necessary. If the rule is within the
		 * eval set, we need to parse it once.
		 */
		if (h.getEvalSet().contains(production)) {
			h.getEvalSet().remove(production);
			MemoEntry ans = eval(production, position, id, line);
			m.set(ans);
		}
		return m;
	}

	private MemoEntry growLR(String production, int position, int id, int line,
			MemoEntry m, Head head) throws TreeException, ParserException {
		printMessage("Growing: " + production, position, id, line);
		/*
		 * We need to mark that at position a seed growing takes place with the
		 * head rule.
		 */
		heads.put(position, head);
		while (true) {
			/*
			 * Set all involved production into evluation status.
			 */
			head.setEvalSet(head.getInvolvedSet());
			/*
			 * Evaluate production.
			 */
			MemoEntry ans = eval(production, position, id, line);
			if (ans.failed()
					|| (ans.getDeltaPosition() <= m.getDeltaPosition())) {
				break;
			}
			m.set(ans);
		}
		/*
		 * Delete head from head buffer to signal end of seed growing.
		 */
		heads.remove(position);
		printMessage("End of growing: " + production, position, id, line);
		return m;
	}

	private MemoEntry lrAnswer(String production, int position, int id,
			int line, MemoEntry m) throws TreeException, ParserException {
		LR lr = m.getLR();
		Head h = lr.getHead();
		MemoEntry seed = lr.getSeed();
		if (!h.getProduction().equals(production)) {
			return seed;
		} else {
			m.set(seed);
			if (m.getStatus() == Status.NONE) {
				throw new ParserException("Error during parser implementation!");
			}
			if (m.getStatus() == Status.FAILED) {
				return MemoEntry.failure();
			} else {
				return growLR(production, position, id, line, m, h);
			}
		}
	}

	/**
	 * This method evaluates the production given by it's name. The different
	 * choices are tried from the first to the last. The first choice matching
	 * is returned.
	 * 
	 * @param productionName
	 *            is the name of the production to be evaluated.
	 * @param position
	 *            is the position within the stream to evaluate the production
	 *            at.
	 * @return A MemoEntry object is returned containing the result.
	 * @throws ParserException
	 * @throws TreeException
	 */
	private MemoEntry eval(String productionName, int position, int id, int line)
			throws ParserException, TreeException {
		MemoEntry saved = MemoEntry.failure();
		for (Production production : grammar.getProductions().get(
				productionName)) {
			MemoEntry progress = parseProduction(production, position, id, line);
			// if (progress.succeeded())
			// return progress;
			if (progress.compareTo(saved) > 0) {
				saved = progress;
			}
		}
		return saved;
	}

	/**
	 * This method performs the actual parsing by reading the production and
	 * applying token definitions and starting other non terminal parsings.
	 * 
	 * @param production
	 *            is the production to be applied.
	 * @param position
	 *            is the position within the stream to evaluate the production
	 *            at.
	 * @return A MemoEntry object is returned containing the result.
	 * @throws TreeException
	 * @throws ParserException
	 */
	private MemoEntry parseProduction(Production production, int position,
			int id, int line) throws TreeException, ParserException {
		ParserTree node = new ParserTree(production);
		MemoEntry progress = MemoEntry.success(0, 0, 0, node);
		for (Construction construction : production.getConstructions()) {
			if (ignoredLeading) {
				MemoEntry newProgress = processIgnoredTokens(node, position
						+ progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				progress.add(newProgress);
			}
			if (construction.isNonTerminal()) {
				MemoEntry newProgress = processNonTerminal(
						(NonTerminal) construction,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				if (newProgress.succeeded()) {
					node.addChild(newProgress.getTree());
					progress.add(newProgress);
				} else if (newProgress.failed())
					return MemoEntry.failure();
			} else {
				MemoEntry newProgress = processTerminal(node,
						(Terminal) construction,
						position + progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				if (newProgress.succeeded()) {
					progress.add(newProgress);
				} else {
					return MemoEntry.failure();
				}
			}
			if (!ignoredLeading) {
				MemoEntry newProgress = processIgnoredTokens(node, position
						+ progress.getDeltaPosition(),
						id + progress.getDeltaId(),
						line + progress.getDeltaLine());
				progress.add(newProgress);
			}
		}
		return progress;
	}

	/**
	 * This method processes a single non terminal.
	 * 
	 * @param nonTerminal
	 * @param position
	 * @return
	 * @throws ParserException
	 * @throws TreeException
	 */
	private MemoEntry processNonTerminal(NonTerminal nonTerminal, int position,
			int id, int line) throws ParserException, TreeException {
		return applyRule(nonTerminal.getName(), position, id, line);
	}

	/**
	 * This method processes a single terminal. This method uses
	 * processTokenDefinition to do this. The information to be put into that
	 * method is extracted and prepared here.
	 * 
	 * @param node
	 * @param terminal
	 * @param position
	 * @return
	 * @throws TreeException
	 */
	private MemoEntry processTerminal(ParserTree node, Terminal terminal,
			int position, int id, int line) throws TreeException {
		TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
		TokenDefinition tokenDefinition = tokenDefinitions
				.getDefinition(terminal.getName());
		// printMessage(terminal.toString() + "\t" + tokenDefinition, position,
		// id, line);
		return processTokenDefinition(node, tokenDefinition, position, id, line);
	}

	/**
	 * This class reads all hidden and ignored tokens from the text and puts
	 * them into the node as children.
	 * 
	 * Attention: This method is package private for testing purposes!
	 * 
	 * @param node
	 * @param position
	 * @return
	 * @throws TreeException
	 * @throws ParserException
	 */
	MemoEntry processIgnoredTokens(ParserTree node, int position, int id,
			int line) throws TreeException, ParserException {
		MemoEntry progress = MemoEntry.success(0, 0, 0, node);
		MemoEntry newProgress = MemoEntry.none();
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
	private MemoEntry processTokenDefinition(ParserTree node,
			TokenDefinition tokenDefinition, int position, int id, int line)
			throws TreeException {
		Matcher matcher = tokenDefinition.getPattern().matcher(
				text.substring(position));
		if (!matcher.find()) {
			return MemoEntry.none();
		}
		String match = matcher.group();
		int lineBreakNum = TextUtils.countLineBreaks(match);
		Token token = new Token(tokenDefinition.getName(), match,
				tokenDefinition.getVisibility(), new TokenMetaData(name, id,
						position, line, lineBreakNum + 1));
		ParserTree myTree = new ParserTree(token);
		node.addChild(myTree);
		if (maxPosition < position + match.length()) {
			maxPosition = position + match.length();
		}
		// printMessage("'" + match + "'", position, id, line);
		return MemoEntry.success(match.length(), 1, lineBreakNum, myTree);
	}

	/**
	 * This method returns the current memoization element from the buffer. This
	 * element is unprocessed. If the element is not set (null) a NONE element
	 * is put into the buffer and returned.
	 * 
	 * @param production
	 * @param position
	 * @return
	 */
	private MemoEntry getMemo(String production, int position) {
		Map<String, MemoEntry> map = memo.get(position);
		if (map == null) {
			map = new HashMap<String, MemoEntry>();
			memo.put(position, map);
		}
		MemoEntry memo = map.get(production);
		if (memo == null) {
			memo = MemoEntry.none();
			map.put(production, memo);
		}
		return memo;
	}

	/**
	 * This method puts memozation elements into the buffer.
	 * 
	 * @param production
	 * @param position
	 * @param stackElement
	 */
	private void setMemo(String production, int position, int id, int line,
			MemoEntry stackElement) {
		Map<String, MemoEntry> map = memo.get(position);
		if (map == null) {
			map = new HashMap<String, MemoEntry>();
			memo.put(position, map);
		}
		map.put(production, stackElement);
	}
}
