package com.puresol.uhura.parser.lr;

import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.AbstractParser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserActionSet;
import com.puresol.uhura.parser.parsetable.ParserTable;

public abstract class AbstractLRParser extends AbstractParser {

	private static final long serialVersionUID = 9173136242276185400L;

	private final static Logger logger = Logger
			.getLogger(AbstractLRParser.class);

	private boolean backtrackEnabled = false;
	private ParserTable parserTable;
	private final Stack<BacktrackLocation> backtrackStack = new Stack<BacktrackLocation>();
	private final Stack<Integer> stateStack = new Stack<Integer>();
	private final Stack<AST> treeStack = new Stack<AST>();
	private int streamPosition = 0;
	private int stepCounter = 0;

	public AbstractLRParser(Grammar grammar) throws GrammarException {
		super(grammar);
		calculateParserTable();
	}

	protected abstract void calculateParserTable() throws GrammarException;

	/**
	 * @return the backtrackEnabled
	 */
	@Override
	public boolean isBacktrackEnabled() {
		return backtrackEnabled;
	}

	/**
	 * @param backtrackEnabled
	 *            the backtrackEnabled to set
	 */
	@Override
	public void setBacktrackEnabled(boolean backtrackEnabled) {
		this.backtrackEnabled = backtrackEnabled;
	}

	/**
	 * @param parserTable
	 *            the parserTable to set
	 */
	protected final void setParserTable(ParserTable parserTable) {
		this.parserTable = parserTable;
		if (logger.isTraceEnabled()) {
			logger.trace(parserTable.toString());
		}
	}

	/**
	 * @return the streamPosition
	 */
	protected int getStreamPosition() {
		return streamPosition;
	}

	/**
	 * @param streamPosition
	 *            the streamPosition to set
	 */
	protected void setStreamPosition(int streamPosition) {
		this.streamPosition = streamPosition;
	}

	/**
	 * @return the stateStack
	 */
	protected Stack<Integer> getStateStack() {
		return stateStack;
	}

	/**
	 * @return the treeStack
	 */
	protected Stack<AST> getTreeStack() {
		return treeStack;
	}

	/**
	 * @return the stepCounter
	 */
	protected int getStepCounter() {
		return stepCounter;
	}

	/**
	 * @param stepCounter
	 *            the stepCounter to set
	 */
	protected void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}

	@Override
	public ParserTable getParserTable() {
		return parserTable;
	}

	@Override
	public AST parse(TokenStream tokenStream) throws ParserException {
		setTokenStream(tokenStream);
		reset();
		return parse();
	}

	protected AST parse() throws ParserException {
		Token token = null;
		try {
			boolean accepted = false;
			do {
				stepCounter++;
				if (logger.isTraceEnabled()) {
					logger.trace(toString());
				}
				Construction construction;
				if (streamPosition < getTokenStream().size()) {
					token = getTokenStream().get(streamPosition);
					construction = parserTable.getConstructionForToken(token);
				} else {
					token = null;
					construction = FinishConstruction.getInstance();
				}
				ParserActionSet actionSet = parserTable.getActionSet(
						stateStack.peek(), construction);
				ParserAction action = getAction(actionSet);
				switch (action.getAction()) {
				case SHIFT:
					shift(action, token);
					break;
				case REDUCE:
					reduce(action);
					break;
				case ACCEPT:
					accepted = accept();
					break;
				case ERROR:
					error(token);
					break;
				default:
					throw new ParserException("Invalid action '" + action
							+ "'for parser!", token);
				}
			} while (!accepted);
			/*
			 * We are finished. The last element in the stack is the result...
			 */
			return treeStack.pop();
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage(), token);
		}
	}

	/**
	 * This method is called just before running the parser to reset all
	 * internal values and to clean all stacks.
	 */
	protected void reset() {
		treeStack.clear();
		stateStack.clear();
		streamPosition = 0;
		stepCounter = 0;
		stateStack.push(0);
	}

	protected ParserAction getAction(ParserActionSet actionSet)
			throws GrammarException {
		if (actionSet.getActionNumber() == 1) {
			return actionSet.getAction();
		}
		if (!isBacktrackEnabled()) {
			logger.trace("Action set '" + actionSet
					+ "' is ambiguous and back tracking is disabled!");
			throw new GrammarException("Grammar is ambiguous!");
		}
		if ((!backtrackStack.isEmpty())
				&& (backtrackStack.peek().getStep() == stepCounter)) {
			if (logger.isTraceEnabled()) {
				logger.trace("Action set '"
						+ actionSet
						+ "' is ambiguous and back tracking was performed already. Trying new alternative...");
			}
			BacktrackLocation location = backtrackStack.pop();
			if (location.getLastAlternative() + 1 >= actionSet
					.getActionNumber()) {
				logger.trace("No alternative left. Abort.");
				return new ParserAction(ActionType.ERROR, -1);
			}
			backtrackStack.push(new BacktrackLocation(stepCounter, stateStack
					.peek(), location.getLastAlternative() + 1));
			return actionSet.getAction(location.getLastAlternative() + 1);
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Action set '"
					+ actionSet
					+ "' is ambiguous. Installing back tracking location in stack...");
		}
		backtrackStack.push(new BacktrackLocation(stepCounter, stateStack
				.peek(), 0));
		return actionSet.getAction(0);
	}

	/**
	 * This method is called for shift actions.
	 * 
	 * @param action
	 *            is the action to be performed.
	 * @param token
	 *            is the current token in token stream.
	 */
	protected void shift(ParserAction action, Token token) {
		treeStack.push(new AST(token));
		stateStack.push(action.getParameter());
		streamPosition++;
	}

	protected void reduce(ParserAction action) throws ParserException {
		try {
			Production production = getGrammar().getProductions().get(
					action.getParameter());
			AST tree = new AST(production);
			for (int i = 0; i < production.getConstructions().size(); i++) {
				AST poppedAST = treeStack.pop();
				if (poppedAST.isNode()) {
					if (poppedAST.isStackingAllowed()) {
						tree.addChildInFront(poppedAST);
					} else {
						if (tree.getName().equals(poppedAST.getName())) {
							tree.addChildrenInFront(poppedAST.getChildren());
						} else {
							tree.addChildInFront(poppedAST);
						}
					}
				} else {
					tree.addChildrenInFront(poppedAST.getChildren());
				}
				stateStack.pop();
			}
			int targetState;
			targetState = parserTable.getAction(stateStack.peek(),
					new ProductionConstruction(production.getName()))
					.getParameter();
			treeStack.push(tree);
			stateStack.push(targetState);
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage(), null);
		} catch (ASTException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage(), null);
		}
	}

	/**
	 * This method is called for accept action and checks for the acceptance
	 * state for the parser to finish the parsing process.
	 * 
	 * The containing check in this method is a check to be secure. Accept is
	 * only true if the tree stack only contains one more element (the result
	 * tree) and the state stack only contains the current state and the zero
	 * state for the start element.
	 * 
	 * @return True is returned if all conditions are met for finishing the
	 *         parser.
	 */
	protected boolean accept() {
		if ((treeStack.size() == 1) && (stateStack.size() == 2)) {
			return true;
		}
		return false;
	}

	protected void error(Token token) throws ParserException {
		if ((!backtrackEnabled) || (backtrackStack.isEmpty())) {
			if (!backtrackEnabled) {
				logger.trace("No valid action available and back tracking is disabled. Aborting...");
			} else {
				logger.trace("No valid action available and back tracking stack is empty. Aborting...");
			}
			throw new ParserException(
					"Error! Could not parse the token stream!", token);
		}
		logger.trace("No valid action available. Perform back tracking...");
		rewindTreeStack(backtrackStack.peek().getStep());
		while (treeStack.size() < stateStack.size()) {
			stateStack.pop();
		}
		stateStack.push(backtrackStack.peek().getStateId());
	}

	protected void rewindTreeStack(int targetStep) {
		while (stepCounter > targetStep) {
			stepBackTreeStack();
		}
	}

	protected void stepBackTreeStack() {
		AST ast = treeStack.pop();
		if (ast.getChildren().size() > 0) {
			for (AST child : ast.getChildren()) {
				treeStack.push(child);
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		buffer.append(stepCounter);
		buffer.append(")\t| ");
		for (Integer stateId : stateStack) {
			buffer.append(" ");
			buffer.append(stateId);
		}
		buffer.append("\t| ");
		for (AST syntaxTree : treeStack) {
			buffer.append(" ");
			buffer.append(syntaxTree.getName());
		}
		buffer.append("\t| ");
		TokenStream tokenStream = getTokenStream();
		for (int i = streamPosition; i < tokenStream.size(); i++) {
			buffer.append(" ");
			buffer.append(tokenStream.get(i));
			if (i > streamPosition + 5) {
				buffer.append("[...]");
				break;
			}
		}
		buffer.append("$");
		return buffer.toString();
	}
}
