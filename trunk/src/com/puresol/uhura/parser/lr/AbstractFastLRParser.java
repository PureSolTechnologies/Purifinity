package com.puresol.uhura.parser.lr;

import java.util.ArrayList;
import java.util.List;
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
			return cloned;
		}

		@Override
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(streamPosition);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final AST parse(TokenStream tokenStream) throws ParserException {
		setTokenStream(tokenStream);
		reset();
		return parse();
	}

	/**
	 * This method is called just before running the parser to reset all
	 * internal values and to clean all stacks.
	 */
	private final void reset() {
		threads.clear();
	}

	/**
	 * This method does the actual parsing.
	 * 
	 * @return The result AST is returned.
	 * @throws ParserException
	 */
	private final AST parse() throws ParserException {
		ParserThread startThread = new ParserThread();
		threads.add(startThread);
		TokenStream tokenStream = getTokenStream();
		Token token = null;
		int stepCounter = 0;
		boolean changed = true;
		try {
			while (changed) {
				changed = false;
				if (threads.size() == 0) {
					throw new ParserException(
							"No more active threads and parsing is not finished!",
							token);
				}
				stepCounter++;
				List<Integer> errorThreads = new ArrayList<Integer>();
				final int threadNum = threads.size();
				logger.debug("Step: " + stepCounter + "\tThreads: " + threadNum);
				for (int threadId = 0; threadId < threadNum; threadId++) {
					ParserThread thread = threads.get(threadId);
					if (thread.isFinished()) {
						continue;
					}
					changed = true;
					int tokenId = thread.getCurrentPosition();
					if (tokenId < getTokenStream().size()) {
						token = getTokenStream().get(tokenId);
						if (token.getVisibility() != Visibility.VISIBLE) {
							thread.nextPosition();
							continue;
						}
					} else {
						token = null;
					}
					// logger.debug("Thread " + threadId + "\t"
					// + threads.get(threadId).toString());
					final ParserActionSet actionSet;
					if (token != null) {
						actionSet = parserTable.getActionSet(
								threads.get(threadId).getCurrentState(),
								token.getTerminal());
					} else {
						actionSet = parserTable.getActionSet(
								threads.get(threadId).getCurrentState(),
								FinishTerminal.getInstance());
					}
					logger.debug("Token: " + token + "; ActionSet: "
							+ actionSet);
					for (int actionId = actionSet.getActionNumber() - 1; actionId >= 0; actionId--) {
						thread = threads.get(threadId);
						if (actionId > 0) {
							thread = thread.clone();
							threads.add(thread);
							logger.debug("Creating new thread...");
						}
						ParserAction parserAction = actionSet
								.getAction(actionId);
						ActionType actionType = parserAction.getAction();
						switch (actionType) {
						case SHIFT:
							thread.addStep(parserAction,
									parserAction.getParameter());
							thread.nextPosition();
							break;
						case REDUCE:
							Production production = getGrammar().getProduction(
									parserAction.getParameter());
							thread.removeStates(production.getConstructions()
									.size());
							int currentState = thread.getCurrentState();
							NonTerminal nonTerminal = new NonTerminal(
									production.getName());
							int newState = parserTable.getAction(currentState,
									nonTerminal).getParameter();
							thread.addStep(parserAction, newState);
							break;
						case ACCEPT:
							thread.addStep(parserAction,
									thread.getCurrentState());
							break;
						case ERROR:
							errorThreads.add(threadId);
							break;
						default:
							throw new ParserException("Invalid action '"
									+ actionType + "'for parser!", token);
						}
					}
				}
				for (int threadId = errorThreads.size() - 1; threadId >= 0; threadId--) {
					threads.remove((int) errorThreads.get(threadId));
				}
			}
			/*
			 * We are finished. The last element in the stack is the result...
			 */
			return null;
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage(), token);
		}
	}
}
