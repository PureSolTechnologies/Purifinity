package com.puresol.uhura.parser.lr;

import java.util.Properties;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.AST;
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
import com.puresol.uhura.parser.parsetable.ParserTable;

public abstract class AbstractLRTableParser extends AbstractParser {

	private final static Logger logger = Logger
			.getLogger(AbstractLRTableParser.class);

	private ParserTable parserTable;
	private final Stack<Integer> stateStack = new Stack<Integer>();
	private final Stack<AST> treeStack = new Stack<AST>();
	private int streamPosition = 0;
	private int stepCounter = 0;

	public AbstractLRTableParser(Properties options, Grammar grammar)
			throws GrammarException {
		super(options, grammar);
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
	public AST call() throws ParserException {
		treeStack.clear();
		stateStack.clear();
		streamPosition = 0;
		stepCounter = 0;
		stateStack.push(0);
		while (true) {
			if (logger.isTraceEnabled()) {
				logger.trace(toString());
			}
			Token token;
			Construction construction;
			if (streamPosition < getTokenStream().size()) {
				token = getTokenStream().get(streamPosition);
				construction = parserTable.getConstructionForToken(token);
			} else {
				token = null;
				construction = FinishConstruction.getInstance();
			}
			stepCounter++;
			ParserAction action = parserTable.getAction(stateStack.peek(),
					construction);
			if (action.getAction() == ActionType.SHIFT) {
				treeStack.push(new AST(token));
				stateStack.push(action.getTargetState());
				streamPosition++;
			} else if (action.getAction() == ActionType.REDUCE) {
				Production production = getGrammar().getProductions().get(
						action.getTargetState());
				AST tree = new AST(production);
				for (int i = 0; i < production.getConstructions().size(); i++) {
					AST poppedAST = treeStack.pop();
					if (poppedAST.isNode()) {
						tree.addChildInFront(poppedAST);
					} else {
						tree.addChildrenInFront(poppedAST.getChildren());
					}
					stateStack.pop();
				}
				int targetState = parserTable.getAction(stateStack.peek(),
						new ProductionConstruction(production.getName()))
						.getTargetState();
				treeStack.push(tree);
				stateStack.push(targetState);
			} else if (action.getAction() == ActionType.ACCEPT) {
				break;
			} else {
				throw new ParserException("Error during parsing!", token);
			}
		}
		/*
		 * We are finished. The last element in the stack is the result...
		 */
		return treeStack.pop();
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
