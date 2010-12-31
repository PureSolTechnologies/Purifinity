package com.puresol.uhura.parser.lr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;
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
public abstract class AbstractFastLRParser extends AbstractParser {

	private static final long serialVersionUID = 9173136242276185400L;

	/**
	 * This class keeps all states and actions for a single thread
	 * 
	 * @author Rick-Rainer Ludwig
	 * 
	 */
	private class ParserThread implements Cloneable {

		private Stack<Integer> states = new Stack<Integer>();
		private Stack<ParserAction> actions = new Stack<ParserAction>();
		private int streamPosition = 0;
		private int clonedAt = 0;

		public ParserThread() {
			states.push(0);
		}

		public void nextPosition() {
			streamPosition++;
		}

		public int getCurrentPosition() {
			return streamPosition;
		}

		public void removeStates(int count) {
			for (int i = 0; i < count; i++) {
				states.pop();
			}
		}

		public void addStep(ParserAction action, Integer state) {
			actions.push(action);
			states.push(state);
		}

		public int getCurrentState() {
			return states.peek();
		}

		public List<ParserAction> getActions() {
			return new ArrayList<ParserAction>(actions);
		}

		public boolean isFinished() {
			if (actions.size() > 0) {
				return actions.peek().getAction() == ActionType.ACCEPT;
			}
			return false;
		}

		@Override
		public ParserThread clone() {
			ParserThread cloned = new ParserThread();
			cloned.states.clear();
			cloned.states.addAll(this.states);
			cloned.actions.addAll(this.actions);
			cloned.streamPosition = this.streamPosition;
			cloned.clonedAt = actions.size();
			return cloned;
		}

		@Override
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("(streampos: ");
			buffer.append(streamPosition);
			buffer.append(", cloned: ");
			buffer.append(clonedAt);
			buffer.append(") ");
			boolean first = true;
			for (Integer state : states) {
				if (first) {
					first = false;
				} else {
					buffer.append(" ");
				}
				buffer.append(state);
			}
			buffer.append(" | ");
			first = true;
			for (ParserAction action : actions) {
				if (first) {
					first = false;
				} else {
					buffer.append(" ");
				}
				buffer.append(action);
			}
			return buffer.toString();
		}
	}

	private final static Logger logger = Logger
			.getLogger(AbstractFastLRParser.class);

	/**
	 * This field contains the parser table to be used.
	 */
	private final ParserTable parserTable;

	private final List<ParserThread> threads = new ArrayList<ParserThread>();

	public AbstractFastLRParser(Grammar grammar) throws GrammarException {
		super(grammar);
		parserTable = calculateParserTable();
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ParserTree parse(TokenStream tokenStream)
			throws ParserException {
		setTokenStream(tokenStream);
		return parse();
	}

	/**
	 * This method does the actual parsing.
	 * 
	 * @return The result AST is returned.
	 * @throws ParserException
	 */
	private final ParserTree parse() throws ParserException {
		threads.clear();
		threads.add(new ParserThread()); // adding start thread
		int stepCounter = 0;
		boolean changed;
		do {
			stepCounter++;
			if (logger.isTraceEnabled()) {
				logger.trace("Step: " + stepCounter + "\tThreads: "
						+ threads.size());
			}
			changed = stepForward();
		} while (changed && (threads.size() > 0));
		if (threads.size() == 0) {
			throw new ParserException(
					"No more active threads and parsing is not finished!");
		} else if (threads.size() > 1) {
			logger.warn("Ambiguous grammar for current token stream! ("
					+ threads.size() + " variants!)");
			// TODO Think about a solution here!!!
			// throw new ParserException(
			// "Ambiguous grammar for current token stream!");
		}
		return createAST(threads.get(0).getActions());
	}

	/**
	 * This method performs a parsing step forward for all active threads.
	 * 
	 * @param stepCounter
	 * @return
	 * @throws ParserException
	 */
	private boolean stepForward() throws ParserException {
		boolean changed = false;
		List<Integer> errorThreads = new ArrayList<Integer>();
		for (int threadId = 0; threadId < threads.size(); threadId++) {
			ParserThread thread = threads.get(threadId);
			if (thread.isFinished()) {
				continue;
			}
			final ParserActionSet actions = getActions(
					thread.getCurrentState(), thread.getCurrentPosition());
			if (actions == null) {
				/*
				 * The current token is inactive for parsing (ignored). Just a
				 * step forward is to be performed.
				 */
				thread.nextPosition();
				changed = true;
				continue;
			}
			if ((actions.getActionNumber() > 1) && (threads.size() > 1000)) {
				continue;
			}
			changed = true;
			for (int actionId = actions.getActionNumber() - 1; actionId >= 0; actionId--) {
				if (actionId == 0) {
					thread = threads.get(threadId);
				} else {
					thread = threads.get(threadId).clone();
					threads.add(thread);
				}
				if (!process(thread, actions.getAction(actionId))) {
					errorThreads.add(threadId);
				}
			}
		}
		killThreadsInErrorState(errorThreads);
		return changed;
	}

	private ParserActionSet getActions(int currentState, int currentPosition)
			throws ParserException {
		Token token = getToken(currentPosition);
		if ((token != null) && (token.getVisibility() != Visibility.VISIBLE)) {
			return null;
		}
		final ParserActionSet actionSet;
		if (token != null) {
			actionSet = parserTable.getActionSet(currentState, new Terminal(
					token.getName(), token.getText(), getGrammar()
							.isIgnoreCase()));
		} else {
			/*
			 * The finish token was found. So look for an action for finish.
			 */
			actionSet = parserTable.getActionSet(currentState,
					FinishTerminal.getInstance());
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Token: " + token + "; ActionSet: " + actionSet);
		}
		return actionSet;
	}

	/**
	 * This method returns the current token at the given stream position.
	 * Additionally the element at position .size() is returned as null to mark
	 * the end of the finish token.
	 * 
	 * @param currentPosition
	 * @return
	 * @throws ParserException
	 */
	private Token getToken(int currentPosition) throws ParserException {
		int tokenId = currentPosition;
		TokenStream tokenStream = getTokenStream();
		if (tokenId < tokenStream.size()) {
			return tokenStream.get(tokenId);
		} else if (tokenId == tokenStream.size()) {
			return null;
		}
		throw new ParserException("Token after oken stream end was requested!");
	}

	private boolean process(ParserThread thread, ParserAction parserAction)
			throws ParserException {
		try {
			ActionType actionType = parserAction.getAction();
			switch (actionType) {
			case SHIFT:
				shift(thread, parserAction);
				return true;
			case REDUCE:
				reduce(thread, parserAction);
				return true;
			case ACCEPT:
				accept(thread, parserAction);
				return true;
			case ERROR:
				return false;
			default:
				throw new ParserException("Invalid action '" + actionType
						+ "'for parser!");
			}
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage());
		}
	}

	/**
	 * Shifts a thread.
	 * 
	 * @param thread
	 * @param parserAction
	 */
	private void shift(ParserThread thread, ParserAction parserAction) {
		thread.addStep(parserAction, parserAction.getParameter());
		thread.nextPosition();
	}

	/**
	 * Reduces a thread.
	 * 
	 * @param thread
	 * @param parserAction
	 */
	private void reduce(ParserThread thread, ParserAction parserAction)
			throws GrammarException {
		Production production = getGrammar().getProduction(
				parserAction.getParameter());
		thread.removeStates(production.getConstructions().size());
		int currentState = thread.getCurrentState();
		NonTerminal nonTerminal = new NonTerminal(production.getName());
		int newState = parserTable.getAction(currentState, nonTerminal)
				.getParameter();
		thread.addStep(parserAction, newState);
	}

	/**
	 * Sets a thread to accept.
	 * 
	 * @param thread
	 * @param parserAction
	 */
	private void accept(ParserThread thread, ParserAction parserAction) {
		thread.addStep(parserAction, thread.getCurrentState());
	}

	/**
	 * This method removes all threads from the threads list which are in an
	 * error state.
	 * 
	 * @param errorThreads
	 */
	private void killThreadsInErrorState(List<Integer> errorThreads) {
		for (int threadId = errorThreads.size() - 1; threadId >= 0; threadId--) {
			threads.remove((int) errorThreads.get(threadId));
		}
	}

	private ParserTree createAST(List<ParserAction> actions)
			throws ParserException {
		return LRTokenStreamConverter.convert(getTokenStream(), getGrammar(),
				actions);
	}
}
