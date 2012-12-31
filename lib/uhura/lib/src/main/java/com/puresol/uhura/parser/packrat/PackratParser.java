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
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;
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

    /**
     * This is the memo for this parser.
     */
    private final PackratMemo memo = new PackratMemo();

    /**
     * This field contains a list of all token definitions which are to ignored
     * or hidden. This is used by the parser to move all parts away which is at
     * the moment of parsing not of any interest.
     */
    private final Set<TokenDefinition> hiddenAndIgnoredTokens;

    /**
     * This is the rule invocation stack which contains all rules which are
     * currently processed. These rules are nested. The data is kept in
     * left-recursive data elements to put data in here if needed for recursion
     * detection and seed growing. This field contains only one element, but the
     * next elements is referenced.
     */
    private RuleInvocation ruleInvocationStack = null;

    /**
     * This is a list of all heads on all positions which are currently grown.
     */
    private final Map<Integer, Head> heads = new HashMap<Integer, Head>();

    /**
     * This flag specified whether ignored or hidden tokens are put leading to
     * the following instructions or trailing after the latest instructions
     * processed.
     */
    private final Boolean ignoredLeading;

    /**
     * This is the grammer to be used for parsing.
     */
    private final Grammar grammar;

    /**
     * This is the text to be parsed.
     */
    private String text = "";

    /**
     * This field contains the original source code which is used to extract
     * meta information.
     */
    private SourceCode sourceCode = null;

    /**
     * This is a field for tracking the maximum position of the parsing process.
     * When the parsing process aborts, this position is the most likely
     * position where an error is within the input stream.
     */
    private int maxPosition = 0;

    public PackratParser(Grammar grammar) {
	super();
	this.grammar = grammar;
	hiddenAndIgnoredTokens = extractHiddenAndIgnoredTokensFromGrammar();
	Properties options = grammar.getOptions();
	ignoredLeading = Boolean.valueOf(options.getProperty(
		"grammar.ignored-leading", "true"));

    }

    /**
     * This method extracts all token definitions which are to be ignored or
     * hidden to process them separately and to put them into special locations
     * into the parser tree.
     * 
     * @return
     */
    private Set<TokenDefinition> extractHiddenAndIgnoredTokensFromGrammar() {
	Set<TokenDefinition> hiddenAndIgnoredTokens = new LinkedHashSet<TokenDefinition>();
	for (TokenDefinition tokenDefinition : grammar.getTokenDefinitions()
		.getDefinitions()) {
	    Visibility visibility = tokenDefinition.getVisibility();
	    if ((visibility == Visibility.HIDDEN)
		    || (visibility == Visibility.IGNORED)) {
		hiddenAndIgnoredTokens.add(tokenDefinition);
	    }
	}
	return hiddenAndIgnoredTokens;
    }

    /**
     * This is the actual parser start. Work is performed on StringBuffer
     * object.
     * 
     * @param buffer
     * @return
     * @throws ParserException
     */
    public ParserTree parse(SourceCode sourceCode) throws ParserException {
	return parse(sourceCode, "_START_");
    }

    /**
     * This method resets the whole parser and sets the new values for text and
     * name to start the parsing process afterwards.
     * 
     * @param text
     * 
     * @param name
     */
    private void initialize(SourceCode sourceCode) {
	this.sourceCode = sourceCode;
	StringBuffer buffer = new StringBuffer();
	for (SourceCodeLine line : sourceCode.getSource()) {
	    buffer.append(line.getLine());
	}
	this.text = buffer.toString();
	memo.clear();
	ruleInvocationStack = null;
	maxPosition = 0;
    }

    /**
     * This is the actual parser start. After running the parse, a check is
     * applied to check for full parsing or partial parsing. If partial parsing
     * is found, an exception is thrown.
     * 
     * @param buffer
     * @return
     * @throws ParserException
     */
    public ParserTree parse(SourceCode sourceCode, String production)
	    throws ParserException {
	try {
	    initialize(sourceCode);
	    MemoEntry progress = applyRule(production, 0, 0, 1);
	    if (progress.getDeltaPosition() != text.length()) {
		throw new ParserException(getParserErrorMessage());
	    }
	    return (ParserTree) progress.getAnswer();
	} catch (TreeException e) {
	    throw new ParserException(e);
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
		maxPosition + 100 >= code.length() ? code.length()
			: maxPosition + 100);
	return "Could not parse the input string near '" + codeString + "'!";
    }

    private void indentLine() {
	// RuleInvocation invokation = ruleInvocationStack;
	// while (invokation != null) {
	// System.out.print("    ");
	// invokation = invokation.getNext();
	// }
    }

    private void printMessage(String text, int position, int id, int line) {
	// indentLine();
	// System.out.println(position + " " + id + " " + line + " : " + text);
    }

    /**
     * This method tries to apply a production at a given position. The
     * production is given as a name and not as a concrete rule to process all
     * choices afterwards.
     * 
     * @param rule
     * @param position
     * @return
     * @throws TreeException
     * @throws ParserException
     */
    private MemoEntry applyRule(String rule, int position, int id, int line)
	    throws TreeException, ParserException {
	printMessage("applyRule: " + rule, position, id, line);
	MemoEntry m = recall(rule, position, id, line);
	if (m == null) {
	    /*
	     * "Create a new LR and push it onto the rule invocation stack."
	     * 
	     * At this point we found a rule which was never processed at this
	     * position. We start completely virgin here...
	     */
	    LR lr = new LR(MemoEntry.failed(), rule, null);
	    ruleInvocationStack = new RuleInvocation(MemoEntry.failed(), rule,
		    null, ruleInvocationStack);
	    /*
	     * "Memoize lr, then evaluate R."
	     * 
	     * Put a fail into memoization memory and evaluate the rule
	     * afterwards.
	     */
	    m = MemoEntry.create(lr);
	    memo.setMemo(rule, position, id, line, m);
	    final MemoEntry ans = eval(rule, position, id, line);
	    /*
	     * "Pop lr off the rule invocation stack."
	     * 
	     * The evaluation of this lr is finished now and we can remove it
	     * from stack. This was needed in cases a left recursion whould be
	     * found within the rule.
	     */
	    ruleInvocationStack = ruleInvocationStack.getNext();

	    if ((m.getAnswer() instanceof LR)
		    && (((LR) m.getAnswer()).getHead() != null)) {
		/*
		 * If a head was added to lr, we found a recursion during
		 * evaluation. We need to set the seed and process with left
		 * recursion evaluation. For that purpose we grow m with ans as
		 * seed.
		 */
		lr = (LR) m.getAnswer();
		lr.setSeed(ans);
		MemoEntry lrAnswer = lrAnswer(rule, position, id, line, m);
		printMessage("grow LR for '" + rule + "' (" + lrAnswer + ").",
			position, id, line);
		return lrAnswer;
	    } else {
		/*
		 * We finished an evaluation and did not find a recursion. So
		 * the result (independent of the the state) is stored in memo
		 * and returned.
		 */
		m.set(ans);
		printMessage("applied '" + rule + "' (" + ans.getAnswer()
			+ ").", position, id, line);
		return ans;
	    }
	} else {
	    /*
	     * We were here already and with the same production. We either have
	     * a real answer or we found a recursion with or without currently
	     * seed growing...
	     */
	    if ((m.getAnswer() instanceof LR)) {
		/*
		 * There is still a LR object in the memo, so we found a
		 * recursion or an in-progress seed grow. We setup the LR seed
		 * grow and return the current seed.
		 */
		setupLR(rule, (LR) m.getAnswer());
		MemoEntry seed = ((LR) m.getAnswer()).getSeed();
		printMessage("Found recursion or grow in process for '" + rule
			+ "' (" + seed + ").", position, id, line);
		return seed;
	    } else {
		/*
		 * We were already here and we have a real result. So we can
		 * just return the answer.
		 */
		printMessage("already processed '" + rule + "' (" + m + ").",
			position, id, line);
		return m;
	    }
	}
    }

    /**
     * After finding a recursion or a seed grow in process, this method puts all
     * information in place for seed grow. This might be the start information
     * for the growth or the current result in the growing, which means the
     * current result.
     * 
     * @param production
     * @param l
     * @throws ParserException
     */
    private void setupLR(String production, final LR l) throws ParserException {
	/*
	 * If the lr object does not contain a head, we found a new recursion
	 * production. Otherwise we already know the production, but we found
	 * another production which is involved in the recursion.
	 */
	if (l.getHead() == null)
	    l.setHead(new Head(production));
	/*
	 * Go over all heads and...!?
	 */
	RuleInvocation s = ruleInvocationStack;
	while (!l.getHead().getProduction().equals(s.getProduction())) {
	    s.setHead(l.getHead());
	    l.getHead().addInvolved(s.getProduction());
	    s = s.getNext();
	    if (s == null)
		throw new RuntimeException(
			"We should find the head again, when we search the stack.\n"
				+ "We found a recursion and the rule should be there again.");
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
	final MemoEntry m = memo.getMemo(production, position);
	final Head h = heads.get(position);
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
	if ((m == null) && (!h.getProduction().equals(production))
		&& (!h.getInvolvedSet().contains(production))) {
	    return MemoEntry.failed();
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
	    final MemoEntry ans = eval(production, position, id, line);
	    m.set(ans);
	}
	return m;
    }

    private MemoEntry growLR(String production, int position, int id, int line,
	    final MemoEntry m, final Head head) throws TreeException,
	    ParserException {
	printMessage("Growing: " + production, position, id, line);
	/*
	 * We need to mark that at position a seed growing takes place with the
	 * head rule.
	 */
	heads.put(position, head);
	while (true) {
	    /*
	     * Set all involved production into evaluation status.
	     */
	    head.setEvalSet(head.getInvolvedSet());
	    /*
	     * Evaluate production.
	     */
	    final MemoEntry ans = eval(production, position, id, line);
	    if (ans.getAnswer().equals(Status.FAILED)
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
	    int line, final MemoEntry m) throws TreeException, ParserException {
	final LR lr = (LR) m.getAnswer();
	final Head h = lr.getHead();
	MemoEntry seed = lr.getSeed();
	if (!h.getProduction().equals(production)) {
	    return seed;
	} else {
	    m.set(seed);
	    if (m.getAnswer().equals(Status.FAILED)) {
		return MemoEntry.failed();
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
     * <b><i>Attention:</i></b> There was a test to introduce a parse where the
     * alternative with the largest progress is returned. <b>This does not
     * work!</b> During the parsing of a succeeding alternative a lot of states
     * are changed on the way. By trying another alternative, the parser gets
     * confused by some inconsistent information.
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
	MemoEntry maxProgress = MemoEntry.failed();
	for (Production production : grammar.getProductions().get(
		productionName)) {
	    MemoEntry progress = parseProduction(production, position, id, line);
	    if (progress.getAnswer() instanceof ParserTree) {
		if ((maxProgress.getAnswer() == Status.FAILED)
			|| (maxProgress.getDeltaPosition() < progress
				.getDeltaPosition())) {
		    maxProgress = progress;
		}
	    }
	}
	return maxProgress;
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
	    processIgnoredLeadingTokens(node, position, id, line, progress);
	    if (construction.isNonTerminal()) {
		MemoEntry newProgress = applyRule(construction.getName(),
			position + progress.getDeltaPosition(),
			id + progress.getDeltaId(),
			line + progress.getDeltaLine());
		if (newProgress.getAnswer() instanceof ParserTree) {
		    node.addChild((ParserTree) newProgress.getAnswer());
		    progress.add(newProgress);
		} else if (newProgress.getAnswer().equals(Status.FAILED))
		    return MemoEntry.failed();
	    } else {
		MemoEntry newProgress = processTerminal(node,
			(Terminal) construction,
			position + progress.getDeltaPosition(),
			id + progress.getDeltaId(),
			line + progress.getDeltaLine());
		if (newProgress.getAnswer() instanceof ParserTree) {
		    progress.add(newProgress);
		} else {
		    return MemoEntry.failed();
		}
	    }
	    processIgnoredTrailingTokens(node, position, id, line, progress);
	}
	indentLine();
	// System.out.println("Parsed: " + production);
	return progress;
    }

    /**
     * This method processes leading tokens which are either hidden or ignored.
     * The processing only happens if the configuration allows it.
     * 
     * @param node
     * @param position
     * @param id
     * @param line
     * @param progress
     * @throws TreeException
     * @throws ParserException
     */
    private void processIgnoredLeadingTokens(ParserTree node, int position,
	    int id, int line, MemoEntry progress) throws TreeException,
	    ParserException {
	if (ignoredLeading) {
	    processIgnoredTokens(node, position, id, line, progress);
	}
    }

    /**
     * This method processes trailing tokens which are either hidden or ignored.
     * The processing only happens if the configuration allows it.
     * 
     * @param node
     * @param position
     * @param id
     * @param line
     * @param progress
     * @throws TreeException
     * @throws ParserException
     */
    private void processIgnoredTrailingTokens(ParserTree node, int position,
	    int id, int line, MemoEntry progress) throws TreeException,
	    ParserException {
	if (!ignoredLeading) {
	    processIgnoredTokens(node, position, id, line, progress);
	}
    }

    private void processIgnoredTokens(ParserTree node, int position, int id,
	    int line, MemoEntry progress) throws TreeException, ParserException {
	MemoEntry newProgress = processIgnoredTokens(node,
		position + progress.getDeltaPosition(),
		id + progress.getDeltaId(), line + progress.getDeltaLine());
	if ((!newProgress.getAnswer().equals(Status.FAILED)))
	    progress.add(newProgress);
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
	printMessage("applyTerminal: " + terminal, position, id, line);
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	TokenDefinition tokenDefinition = tokenDefinitions
		.getDefinition(terminal.getName());
	MemoEntry result = processTokenDefinition(node, tokenDefinition,
		position, id, line);
	if (result == null) {
	    throw new RuntimeException("There should be a result not null!");
	}
	printMessage(
		"applied Terminal '" + terminal + "' (" + result.getAnswer()
			+ ").", position, id, line);
	return result;
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
	MemoEntry newProgress = MemoEntry.success(0, 0, 0, null);
	do {
	    for (TokenDefinition tokenDefinition : hiddenAndIgnoredTokens) {
		newProgress = processTokenDefinition(node, tokenDefinition,
			position + progress.getDeltaPosition(),
			id + progress.getDeltaId(),
			line + progress.getDeltaLine());
		if (!newProgress.getAnswer().equals(Status.FAILED)) {
		    progress.add(newProgress);
		    break;
		}
	    }
	} while (newProgress.getDeltaPosition() > 0);
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
	    return MemoEntry.failed();
	}
	String match = matcher.group();
	int lineBreakNum = TextUtils.countLineBreaks(match);
	CodeLocation source = sourceCode.getSource().get(line - 1).getSource();
	TokenMetaData metaData = new TokenMetaData(source, id, position, line,
		lineBreakNum + 1);
	Token token = new Token(tokenDefinition.getName(), match,
		tokenDefinition.getVisibility(), metaData);
	ParserTree myTree = new ParserTree(token);
	node.addChild(myTree);
	if (maxPosition < position + match.length()) {
	    maxPosition = position + match.length();
	}
	return MemoEntry.success(match.length(), 1, lineBreakNum, myTree);
    }
}
