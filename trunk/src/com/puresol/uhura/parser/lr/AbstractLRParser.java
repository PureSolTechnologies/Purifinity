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
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserTable;

public abstract class AbstractLRParser extends AbstractParser {

	private static final long serialVersionUID = 9173136242276185400L;

	private final static Logger logger = Logger
			.getLogger(AbstractLRParser.class);

	private ParserTable parserTable;
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
	 * @param parserTable
	 *            the parserTable to set
	 */
	protected final void setParserTable(ParserTable parserTable) {
		this.parserTable = parserTable;
		if (logger.isTraceEnabled()) {
			logger.trace(parserTable.toString());
		}
	}

	@Override
	public ParserTable getParserTable() {
		return parserTable;
	}

	@Override
	public AST parse(TokenStream tokenStream) throws ParserException {
		Token token = null;
		try {
			setTokenStream(tokenStream);
			reset();
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
				ParserAction action = parserTable.getAction(stateStack.peek(),
						construction);
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
	private void reset() {
		treeStack.clear();
		stateStack.clear();
		streamPosition = 0;
		stepCounter = 0;
		stateStack.push(0);
	}

	/**
	 * This method is called for shift actions.
	 * 
	 * @param action
	 *            is the action to be performed.
	 * @param token
	 *            is the current token in token stream.
	 */
	private void shift(ParserAction action, Token token) {
		treeStack.push(new AST(token));
		stateStack.push(action.getParameter());
		streamPosition++;
	}

	private void reduce(ParserAction action) throws ParserException {
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
	private boolean accept() {
		if ((treeStack.size() == 1) && (stateStack.size() == 2)) {
			return true;
		}
		return false;
	}

	private void error(Token token) throws ParserException {
		throw new ParserException("Error during parsing!", token);
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
