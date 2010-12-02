package com.puresol.uhura.parser.lr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.trees.TreeWalker;
import com.puresol.uhura.ast.AST;
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
public abstract class AbstractLRParser_v2 extends AbstractParser {

	private static final long serialVersionUID = 9173136242276185400L;

	private final static Logger logger = Logger
			.getLogger(AbstractLRParser_v2.class);

	public class BacktrackLocation_v2 implements Serializable {

		private static final long serialVersionUID = -2641716613348317852L;

		private final Stack<Integer> stateStack;
		private final int actionStackSize;
		private final int streamPosition;
		private final int stepCounter;
		private final int lastAlternative;

		public BacktrackLocation_v2(Stack<Integer> stateStack,
				int actionStackSize, int streamPosition, int stepCounter,
				int lastAlternative) {
			super();
			this.stateStack = stateStack;
			this.actionStackSize = actionStackSize;
			this.streamPosition = streamPosition;
			this.stepCounter = stepCounter;
			this.lastAlternative = lastAlternative;
		}

		/**
		 * @return the stateStack
		 */
		public Stack<Integer> getStateStack() {
			return stateStack;
		}

		/**
		 * @return the treeStack
		 */
		public int getActionStackSize() {
			return actionStackSize;
		}

		/**
		 * @return the streamPosition
		 */
		public int getStreamPosition() {
			return streamPosition;
		}

		/**
		 * @return the stepCounter
		 */
		public int getStepCounter() {
			return stepCounter;
		}

		/**
		 * @return the lastAlternative
		 */
		public int getLastAlternative() {
			return lastAlternative;
		}
	}

	/**
	 * This flag specifies whether the parser is allowed to use back tracking or
	 * not. If it is set to true, back tracking is allowed.
	 */
	private final boolean backtrackEnabled;

	/**
	 * This stack keeps the back tracking information for back tracking.
	 */
	private final Stack<BacktrackLocation_v2> backtrackStack = new Stack<BacktrackLocation_v2>();

	private final Map<Integer, Set<ParserAction>> failedActions = new HashMap<Integer, Set<ParserAction>>();

	/**
	 * This field contains the parser table to be used.
	 */
	private final ParserTable parserTable;

	/**
	 * This stack is for storing the states of the parser for shift and
	 * reduction.
	 */
	private Stack<Integer> stateStack = new Stack<Integer>();

	/**
	 * This stack stores the AST fragments during parsing in parallel to the
	 * states in stateStack.
	 */
	private final List<ParserAction> actionStack = new ArrayList<ParserAction>();
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

	public AbstractLRParser_v2(Grammar grammar) throws GrammarException {
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

	protected ParserTable getParserTable() {
		return parserTable;
	}

	private void addFailedAction(int state, ParserAction action) {
		Set<ParserAction> actions = failedActions.get(state);
		if (actions == null) {
			actions = new HashSet<ParserAction>();
			failedActions.put(state, actions);
		}
		actions.add(action);
	}

	private boolean isFailedAction(int state, ParserAction action) {
		Set<ParserAction> actions = failedActions.get(state);
		if (actions == null) {
			return false;
		}
		return actions.contains(action);
	}

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
		actionStack.clear();
		stateStack.clear();
		parserErrors.clear();
		streamPosition = 0;
		stepCounter = 0;
		stateStack.push(0);
		failedActions.clear();
		shiftIgnoredTokens();
	}

	private final void shiftIgnoredTokens() {
		if (streamPosition == getTokenStream().size()) {
			return;
		}
		Token token = getTokenStream().get(streamPosition);
		while (token.getVisibility() == Visibility.IGNORED) {
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
					shift(action);
					break;
				case REDUCE:
					reduce(action);
					break;
				case ACCEPT:
					accepted = accept(action);
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
			return LRTokenStreamConverter.convert(getTokenStream(),
					getGrammar(), new ArrayList<ParserAction>(actionStack));
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
			BacktrackLocation_v2 location = backtrackStack.pop();
			/*
			 * mark last alternative as fail...
			 */
			addFailedAction(stateStack.peek(),
					actionSet.getAction(location.getLastAlternative()));
			int stepAhead = 1;
			while ((location.getLastAlternative() + stepAhead < actionSet
					.getActionNumber())
					&& (isFailedAction(
							stateStack.peek(),
							actionSet.getAction(location.getLastAlternative()
									+ stepAhead)))) {
				stepAhead++;
			}
			if (location.getLastAlternative() + stepAhead >= actionSet
					.getActionNumber()) {
				logger.trace("No alternative left. Abort.");
				return new ParserAction(ActionType.ERROR, -1);
			}
			addBacktrackLocation(location.getLastAlternative() + stepAhead);
			return actionSet.getAction(location.getLastAlternative()
					+ stepAhead);
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
	@SuppressWarnings("unchecked")
	private final void addBacktrackLocation(int usedAlternative) {
		backtrackStack.push(new BacktrackLocation_v2(
				(Stack<Integer>) stateStack.clone(), actionStack.size(),
				streamPosition, stepCounter, usedAlternative));
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
	private final void shift(ParserAction action) {
		stateStack.push(action.getParameter());
		actionStack.add(action);
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
	private final void reduce(ParserAction action) throws ParserException {
		try {
			Production production = getGrammar().getProduction(
					action.getParameter());
			for (int i = 0; i < production.getConstructions().size(); i++) {
				/*
				 * The for loop is run as many times as the production contains
				 * constructions which are added up for an AST node.
				 */
				stateStack.pop();
			}
			actionStack.add(action);
			ParserAction gotoAction = parserTable.getAction(stateStack.peek(),
					new NonTerminal(production.getName()));
			if (gotoAction.getAction() == ActionType.ERROR) {
				error();
				return;
			}
			stateStack.push(gotoAction.getParameter());
		} catch (GrammarException e) {
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
	private final boolean accept(ParserAction action) throws ParserException {
		actionStack.add(action);
		return true;
	}

	/**
	 * This method is called for an error action.
	 * 
	 * @param token
	 * @throws ParserException
	 */
	private final void error() throws ParserException {
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
		throw new ParserException("Error! Could not parse the token stream!");
	}

	/**
	 * This method perform the back tracking by popping all relevant information
	 * from the stacks.
	 */
	private final void trackBack() {
		logger.trace("No valid action available. Perform back tracking...");
		BacktrackLocation_v2 backtrackLocation = backtrackStack.peek();
		streamPosition = backtrackLocation.getStreamPosition();
		stepCounter = backtrackLocation.getStepCounter();
		while (actionStack.size() > backtrackLocation.getActionStackSize()) {
			actionStack.remove(actionStack.size() - 1);
		}
		stateStack = backtrackLocation.getStateStack();
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
		TokenStream tokenStream = getTokenStream();
		if (tokenStream != null) {
			for (int i = streamPosition; i < tokenStream.size(); i++) {
				buffer.append(" ");
				buffer.append(tokenStream.get(i));
				if (i > streamPosition + 5) {
					buffer.append("[...]");
					break;
				}
			}
			buffer.append("$");
		}
		return buffer.toString();
	}

	private AST finishAST(AST ast) {
		TreeWalker<AST> walker = new TreeWalker<AST>(ast);
		walker.walkBackward(new MetaDataGeneratorVisitor());
		return ast;
	}
}
