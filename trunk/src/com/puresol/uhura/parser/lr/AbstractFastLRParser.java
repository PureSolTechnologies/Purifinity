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

		public ParserThread() {
			states.push(0);
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
			return actions.peek().getAction() == ActionType.ACCEPT;
		}

		@Override
		public ParserThread clone() {
			ParserThread cloned = new ParserThread();
			cloned.states.clear();
			cloned.states.addAll(this.states);
			cloned.actions.addAll(this.actions);
			return cloned;
		}

		@Override
		public String toString() {
			StringBuffer buffer = new StringBuffer();
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

	/**
	 * This is the current position in the stream.
	 */
	private int streamPosition = 0;

	/**
	 * This is the counter of the steps and gives the current steps number
	 * during parsing.
	 */
	private int stepCounter = 0;

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
		AST ast = parse();
		return finishAST(ast);
	}

	/**
	 * This method is called just before running the parser to reset all
	 * internal values and to clean all stacks.
	 */
	private final void reset() {
		threads.clear();
		treeStack.clear();
		stateStack.clear();
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
		ParserThread startThread = new ParserThread();
		threads.add(startThread);
		TokenStream tokenStream = getTokenStream();
		Token token = null;
		try {
			for (int tokenId = 0; tokenId <= tokenStream.size(); tokenId++) {
				stepCounter++;
				if (tokenId < getTokenStream().size()) {
					token = getTokenStream().get(tokenId);
				} else {
					token = null;
				}
				if (token.getVisibility() != Visibility.VISIBLE) {
					continue;
				}
				List<Integer> errorThreads = new ArrayList<Integer>();
				final int threadNum = threads.size();
				logger
						.debug("Step: " + stepCounter + "\tThreads: "
								+ threadNum);
				for (int threadId = 0; threadId < threadNum; threadId++) {
					logger.debug("Thread " + threadId + "\t"
							+ threads.get(threadId).toString());
					final ParserActionSet actionSet;
					if (token != null) {
						actionSet = parserTable.getActionSet(threads.get(
								threadId).getCurrentState(), token
								.getTerminal());
					} else {
						actionSet = parserTable.getActionSet(threads.get(
								threadId).getCurrentState(), FinishTerminal
								.getInstance());
					}
					logger.debug("Token: " + token + "; ActionSet: "
							+ actionSet);
					for (int actionId = actionSet.getActionNumber() - 1; actionId >= 0; actionId--) {
						ParserThread thread = threads.get(threadId);
						if (actionId > 0) {
							thread = thread.clone();
							threads.add(thread);
						}
						ParserAction parserAction = actionSet
								.getAction(actionId);
						ActionType actionType = parserAction.getAction();
						switch (actionType) {
						case SHIFT:
							thread.addStep(parserAction, parserAction
									.getParameter());
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
							thread.addStep(parserAction, thread
									.getCurrentState());
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
					threads.remove(threadId);
				}
			}
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
								tree
										.addChildrenInFront(poppedAST
												.getChildren());
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
		logger
				.trace("No valid action available and back tracking is disabled. Aborting...");
		throw new ParserException("Error! Could not parse the token stream!",
				token);
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
		walker.walkBackward(new MetaDataGeneratorVisitor());
		return ast;
	}
}
