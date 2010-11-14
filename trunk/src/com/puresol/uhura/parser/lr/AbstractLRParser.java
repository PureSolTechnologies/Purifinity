package com.puresol.uhura.parser.lr;

import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.trees.TreeWalker;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.AbstractParser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserActionSet;
import com.puresol.uhura.parser.parsetable.ParserTable;

/**
 * This class is a complete implementation of a LR parser. Only the parser table
 * is missing. The implementation of this needs to be done in the inherited
 * class in the abstract method.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractLRParser extends AbstractParser {

	private static final long serialVersionUID = 9173136242276185400L;

	private final static Logger logger = Logger
			.getLogger(AbstractLRParser.class);

	/**
	 * This flag specifies whether the parser is allowed to use back tracking or
	 * not. If it is set to true, back tracking is allowed.
	 */
	private final boolean backtrackEnabled;

	/**
	 * This stack keeps the back tracking information for back tracking.
	 */
	private final Stack<BacktrackLocation> backtrackStack = new Stack<BacktrackLocation>();

	/**
	 * This field contains the parser table to be used.
	 */
	private final ParserTable parserTable;

	/**
	 * This stack is for storing the states of the parser for shift and
	 * reduction.
	 */
	private final Stack<Integer> stateStack = new Stack<Integer>();

	/**
	 * This stack stores the AST fragments during parsing in parallel to the
	 * states in stateStack.
	 */
	private final Stack<AST> treeStack = new Stack<AST>();
	private final ParserErrors parserErrors = new ParserErrors();

	/**
	 * This is the current position in the stream.
	 */
	private int streamPosition = 0;

	/**
	 * This is the counter of the steps and gives the current steps number
	 * during parsing.
	 */
	private int stepCounter = 0;

	public AbstractLRParser(Grammar grammar) throws GrammarException {
		super(grammar);
		parserTable = calculateParserTable();
		backtrackEnabled = Boolean.valueOf((String) grammar.getOptions().get(
				"parser.backtracking"));
	}

	/**
	 * This method is abstract is to be implemented by inheriting classes to
	 * provide the correct parser table.
	 * 
	 * @return The correct parser table is to be returned.
	 * @throws GrammarException
	 */
	protected abstract ParserTable calculateParserTable()
			throws GrammarException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final AST parse(TokenStream tokenStream) throws ParserException {
		setTokenStream(tokenStream);
		reset();
		AST ast = parse();
		return finishAST(ast);
	}

	/**
	 * This method is called just before running the parser to reset all
	 * internal values and to clean all stacks.
	 */
	private final void reset() {
		backtrackStack.clear();
		treeStack.clear();
		stateStack.clear();
		parserErrors.clear();
		streamPosition = 0;
		stepCounter = 0;
		stateStack.push(0);
		shiftIgnoredTokens();
	}

	private final void shiftIgnoredTokens() {
		if (streamPosition == getTokenStream().size()) {
			return;
		}
		Token token = getTokenStream().get(streamPosition);
		while (token.getVisibility() == Visibility.IGNORED) {
			treeStack.push(new AST(token));
			streamPosition++;
			if (streamPosition == getTokenStream().size()) {
				break;
			}
			token = getTokenStream().get(streamPosition);
		}
	}

	/**
	 * This method does the actual parsing.
	 * 
	 * @return The result AST is returned.
	 * @throws ParserException
	 */
	private final AST parse() throws ParserException {
		Token token = null;
		try {
			boolean accepted = false;
			do {
				stepCounter++;
				if (logger.isTraceEnabled()) {
					logger.trace(toString());
				}
				final ParserActionSet actionSet;
				if (streamPosition < getTokenStream().size()) {
					token = getTokenStream().get(streamPosition);
					actionSet = parserTable.getActionSet(stateStack.peek(),
							token.getTerminal());
				} else {
					token = null;
					actionSet = parserTable.getActionSet(stateStack.peek(),
							FinishTerminal.getInstance());
				}
				if (logger.isTraceEnabled()) {
					logger.trace(actionSet);
				}
				ParserAction action = getAction(actionSet);
				switch (action.getAction()) {
				case SHIFT:
					shift(action.getParameter());
					break;
				case REDUCE:
					reduce(action.getParameter());
					break;
				case ACCEPT:
					accepted = accept();
					break;
				case ERROR:
					error();
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

	private final ParserAction getAction(ParserActionSet actionSet)
			throws GrammarException {
		if (actionSet.getActionNumber() == 1) {
			/*
			 * action is not ambiguous, therefore, the action is easily
			 * returned...
			 */
			return actionSet.getAction();
		}
		if (!backtrackEnabled) {
			/*
			 * Backtracking is disabled and action set is ambiguous. Throw
			 * exception due to illegal state...
			 */
			logger.trace("Action set '" + actionSet
					+ "' is ambiguous and back tracking is disabled!");
			throw new GrammarException("Grammar is ambiguous!");
		}
		if ((!backtrackStack.isEmpty())
				&& (backtrackStack.peek().getStepCounter() == stepCounter)) {
			/*
			 * We are currently at a step with stored backtracking information,
			 * so we just returned from a backtrack. Trying next alternative...
			 */
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
			addBacktrackLocation(location.getLastAlternative() + 1);
			return actionSet.getAction(location.getLastAlternative() + 1);
		}
		/*
		 * We have a new ambiguous state. We store backtracking information and
		 * try first alternative...
		 */
		if (logger.isTraceEnabled()) {
			logger.trace("Action set '"
					+ actionSet
					+ "' is ambiguous. Installing back tracking location in stack...");
		}
		addBacktrackLocation(0);
		return actionSet.getAction(0);
	}

	/**
	 * This method adds the backtracking information for the current step.
	 * 
	 * @param usedAlternative
	 *            is the number of the alternative which is the next to be
	 *            tried.
	 */
	private final void addBacktrackLocation(int usedAlternative) {
		@SuppressWarnings("unchecked")
		Stack<Integer> backtrackStates = (Stack<Integer>) stateStack.clone();
		Stack<AST> backtrackTrees = new Stack<AST>();
		for (AST ast : treeStack) {
			AST newAST = new AST(ast.getName(), ast.getToken(), ast.isNode(),
					ast.isStackingAllowed());
			newAST.addChildren(ast.getChildren());
			backtrackTrees.push(newAST);
		}
		backtrackStack.push(new BacktrackLocation(backtrackStates,
				backtrackTrees, streamPosition, stepCounter, usedAlternative));
	}

	/**
	 * This method is called for shift actions.
	 * 
	 * @param newState
	 *            is the new state id after shift.
	 * @param token
	 *            is the current token in token stream.
	 * @throws ParserException
	 */
	private final void shift(int newState) {
		stateStack.push(newState);
		Token token = getTokenStream().get(streamPosition);
		AST ast = new AST(token);
		treeStack.push(ast);
		streamPosition++;
		shiftIgnoredTokens();
	}

	/**
	 * This method is called for reduce actions.
	 * 
	 * @param grammarRuleId
	 *            is the rule id which is to be applied for reduction.
	 * @throws ParserException
	 *             is thrown if the rule can not successfully be applied.
	 */
	private final void reduce(int grammarRuleId) throws ParserException {
		try {
			Production production = getGrammar().getProduction(grammarRuleId);
			AST tree = new AST(production);
			for (int i = 0; i < production.getConstructions().size(); i++) {
				/*
				 * The for loop is run as many times as the production contains
				 * constructions which are added up for an AST node.
				 */
				stateStack.pop();
				AST poppedAST;
				do {
					poppedAST = treeStack.pop();
					if (poppedAST.isNode()) {
						/*
						 * The popped AST is an own node.
						 */
						if (poppedAST.isStackingAllowed()) {
							/*
							 * The AST is allowed to be stacked, so do not do
							 * anything just add it to children list at the
							 * front position.
							 */
							tree.addChildInFront(poppedAST);
						} else {
							/*
							 * The AST is not allowed to be stacked. So the
							 * presence for a node with the same type is checked
							 * and the result is added to that or the node is
							 * created.
							 */
							if (tree.getName().equals(poppedAST.getName())) {
								tree.addChildrenInFront(poppedAST.getChildren());
							} else {
								tree.addChildInFront(poppedAST);
							}
						}
					} else {
						/*
						 * The currently popped AST is not allowed to be an own
						 * node, so all children are added to the tree in front
						 * at the children list.
						 */
						tree.addChildrenInFront(poppedAST.getChildren());
					}
					/*
					 * The while loop is as long as there are ASTs popped which
					 * are non visible tokens...
					 */
				} while ((poppedAST.getToken() != null)
						&& (poppedAST.getToken().getVisibility() != Visibility.VISIBLE));
			}
			treeStack.push(tree);
			ParserAction action = parserTable.getAction(stateStack.peek(),
					new NonTerminal(production.getName()));
			if (action.getAction() == ActionType.ERROR) {
				error();
				return;
			}
			stateStack.push(action.getParameter());
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
	 * @throws ParserException
	 */
	private final boolean accept() throws ParserException {
		try {
			if ((treeStack.size() >= 1) && (stateStack.size() == 2)) {
				AST ast = treeStack.pop();
				while (treeStack.size() > 0) {
					ast.addChildInFront(treeStack.pop());
				}
				treeStack.push(ast);
				return true;
			}
			error();
		} catch (ASTException e) {
			logger.error(e.getMessage(), e);
			error();
		}
		return false;
	}

	/**
	 * This method is called for an error action.
	 * 
	 * @param token
	 * @throws ParserException
	 */
	private final void error() throws ParserException {
		Token token = getTokenStream().get(streamPosition);
		Integer currentState = stateStack.peek();
		parserErrors.addError(currentState);
		if (backtrackEnabled && !backtrackStack.isEmpty()) {
			trackBack();
			return;
		}
		if (!backtrackEnabled) {
			logger.trace("No valid action available and back tracking is disabled. Aborting...");
		} else {
			logger.trace("No valid action available and back tracking stack is empty. Aborting...");
		}
		throw new ParserException("Error! Could not parse the token stream!",
				token);
	}

	/**
	 * This method perform the back tracking by popping all relevant information
	 * from the stacks.
	 */
	private final void trackBack() {
		logger.trace("No valid action available. Perform back tracking...");
		BacktrackLocation backtrackLocation = backtrackStack.peek();
		streamPosition = backtrackLocation.getStreamPosition();
		stepCounter = backtrackLocation.getStepCounter();
		treeStack.clear();
		treeStack.addAll(backtrackLocation.getTreeStack());
		stateStack.clear();
		stateStack.addAll(backtrackLocation.getStateStack());
		stepCounter--;
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

	private AST finishAST(AST ast) {
		TreeWalker<AST> walker = new TreeWalker<AST>(ast);
		// TODO put the calculation and handling of ASTMetaData in here...
		return ast;
	}
}
