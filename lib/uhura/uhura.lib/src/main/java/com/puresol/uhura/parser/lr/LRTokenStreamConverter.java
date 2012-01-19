package com.puresol.uhura.parser.lr;

import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.trees.TreeException;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.ParserTreeMetaData;
import com.puresol.uhura.parser.parsetable.ParserAction;

/**
 * This class is used to convert a token stream into a parser tree. For this
 * action a grammar and a list of all parser actions are needed. The grammar and
 * the actions are taken from a former parser action.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LRTokenStreamConverter {

    private static final Logger logger = LoggerFactory
	    .getLogger(LRTokenStreamConverter.class);

    public static ParserTree convert(TokenStream tokenStream, Grammar grammar,
	    List<ParserAction> actions) throws ParserException {
	return new LRTokenStreamConverter(tokenStream, grammar, actions)
		.getParserTree();
    }

    private final TokenStream tokenStream;
    private final Grammar grammar;
    private final List<ParserAction> actions;
    private final boolean ignoredTokensLeading;
    private final Stack<ParserTree> treeStack = new Stack<ParserTree>();
    int position;

    private LRTokenStreamConverter(TokenStream tokenStream, Grammar grammar,
	    List<ParserAction> actions) {
	super();
	this.tokenStream = tokenStream;
	this.grammar = grammar;
	this.actions = actions;
	ignoredTokensLeading = Boolean.valueOf(grammar.getOptions()
		.getProperty("grammar.ignored-leading"));
    }

    private ParserTree getParserTree() throws ParserException {
	ParserTree tree = convert();
	tree = addMetaData(tree);
	return tree;
    }

    private void reset() {
	position = 0;
	treeStack.clear();
    }

    private ParserTree convert() throws ParserException {
	try {
	    reset();
	    for (ParserAction action : actions) {
		process(action);
	    }
	    putRemainingIgnoredTokensTogether();
	    return treeStack.peek();
	} catch (TreeException e) {
	    logger.error(e.getMessage(), e);
	    throw new ParserException(e.getMessage());
	}
    }

    private void process(ParserAction action) throws ParserException,
	    TreeException {
	if (logger.isTraceEnabled()) {
	    logger.trace("Action: " + action + "; stack size: "
		    + treeStack.size());
	    logger.trace(treeStack.toString());
	}
	switch (action.getAction()) {
	case SHIFT:
	    shift();
	    break;
	case REDUCE:
	    reduce(action);
	    break;
	case ACCEPT:
	    break;
	default:
	    throw new ParserException(
		    "Invalid parser action within action list!");
	}
    }

    private void putRemainingIgnoredTokensTogether() throws TreeException {
	/*
	 * Put remaining tokens from token stream at the end.
	 */
	while (position < tokenStream.size()) {
	    treeStack.peek()
		    .addChild(new ParserTree(tokenStream.get(position)));
	    position++;
	}
	/*
	 * Put remaining tokens from tree stack at the beginning.
	 */
	if (treeStack.size() > 1) {
	    ParserTree treeElement = treeStack.pop();
	    while (treeStack.size() > 0) {
		treeElement.addChildInFront(treeStack.pop());
	    }
	    treeStack.push(treeElement);
	}
    }

    private void shift() {
	while (tokenStream.get(position).getVisibility() != Visibility.VISIBLE) {
	    treeStack.push(new ParserTree(tokenStream.get(position)));
	    position++;
	}
	treeStack.push(new ParserTree(tokenStream.get(position)));
	position++;
    }

    private void reduce(ParserAction action) throws TreeException {
	Production production = grammar.getProduction(action.getParameter());
	logger.trace(production.toString());
	ParserTree newAST = new ParserTree(production);
	for (int i = 0; i < production.getConstructions().size(); i++) {
	    /*
	     * The for loop is run as many times as the production contains
	     * constructions which are added up for an AST node.
	     */
	    ParserTree poppedAST;
	    do {
		poppedAST = treeStack.pop();
		if (poppedAST.isNode()) {
		    /*
		     * The popped AST is an own node.
		     */
		    if (poppedAST.isStackingAllowed()) {
			/*
			 * The AST is allowed to be stacked, so do not do
			 * anything just add it to children list at the front
			 * position.
			 */
			newAST.addChildInFront(poppedAST);
		    } else {
			/*
			 * The AST is not allowed to be stacked. So the presence
			 * for a node with the same type is checked and the
			 * result is added to that or the node is created.
			 */
			if (newAST.getName().equals(poppedAST.getName())) {
			    newAST.addChildrenInFront(poppedAST.getChildren());
			} else {
			    newAST.addChildInFront(poppedAST);
			}
		    }
		} else {
		    /*
		     * The currently popped AST is not allowed to be an own
		     * node, so all children are added to the tree in front at
		     * the children list.
		     */
		    newAST.addChildrenInFront(poppedAST.getChildren());
		}
		/*
		 * The while loop is as long as there are ASTs popped which are
		 * non visible tokens...
		 */
	    } while ((poppedAST.getToken() != null)
		    && (poppedAST.getToken().getVisibility() != Visibility.VISIBLE));
	}
	if (ignoredTokensLeading) {
	    while ((treeStack.size() > 0)
		    && (treeStack.peek().getToken() != null)
		    && (treeStack.peek().getToken().getVisibility() != Visibility.VISIBLE)) {
		newAST.addChildInFront(treeStack.pop());
	    }
	}
	treeStack.push(newAST);
    }

    private ParserTree addMetaData(ParserTree tree) {
	TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(tree);
	int line = 1;
	final String sourceName = tokenStream.getName();
	do {
	    final ParserTree currentNode = iterator.getCurrentNode();
	    final Token token = currentNode.getToken();
	    if (token != null) {
		final int lineNum = token.getMetaData().getLineNum();
		currentNode.setMetaData(new ParserTreeMetaData(sourceName,
			line, lineNum));
		line += lineNum - 1;
	    }
	} while (iterator.goForward());
	iterator.gotoEnd();
	do {
	    final ParserTree currentNode = iterator.getCurrentNode();
	    final Token token = currentNode.getToken();
	    if (token != null) {
		line = currentNode.getMetaData().getLine();
	    } else {
		List<ParserTree> children = currentNode.getChildren();
		if (children.size() == 0) {
		    currentNode.setMetaData(new ParserTreeMetaData(sourceName,
			    line, 1));

		} else {
		    final ParserTreeMetaData metaDataLeft = children.get(0)
			    .getMetaData();
		    final ParserTreeMetaData metaDataRight = children.get(
			    children.size() - 1).getMetaData();
		    currentNode.setMetaData(new ParserTreeMetaData(sourceName,
			    metaDataLeft.getLine(), metaDataRight.getLine()
				    - metaDataLeft.getLine()
				    + metaDataRight.getLineNum()));
		}
	    }
	} while (iterator.goBackward());
	return tree;
    }

}
