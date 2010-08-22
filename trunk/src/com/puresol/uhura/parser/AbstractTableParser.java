package com.puresol.uhura.parser;

import java.util.Properties;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserTable;

public abstract class AbstractTableParser extends AbstractParser {

	private final static Logger logger = Logger
			.getLogger(AbstractTableParser.class);

	private ParserTable parserTable;
	private final Stack<Integer> stateStack = new Stack<Integer>();
	private final Stack<SyntaxTree> treeStack = new Stack<SyntaxTree>();

	public AbstractTableParser(Properties options, Grammar grammar)
			throws ParserException {
		super(options, grammar);
		calculateParserTable();
	}

	protected abstract void calculateParserTable() throws ParserException;

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
	public SyntaxTree call() throws ParserException {
		treeStack.clear();
		stateStack.clear();
		int state = 0;
		int streamPosition = 0;
		while (true) {
			Token token = getTokenStream().get(streamPosition);
			Construction construction = parserTable
					.getConstructionForToken(token);
			ParserAction action = parserTable.getAction(state, construction);
			if (action.getAction() == ActionType.SHIFT) {
				stateStack.push(state);
				treeStack.push(new SyntaxTree(token));
				state = action.getTargetState();
				streamPosition++;
			} else if (action.getAction() == ActionType.REDUCE) {
				Production production = getGrammar().getProductions().get(
						action.getTargetState());
				SyntaxTree tree = new SyntaxTree(production.getName());
				for (int i = 0; i < production.getConstructions().size(); i++) {
					tree.getChildren().add(0, treeStack.pop());
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
				throw new ParserException("Error during parsing!");
			}
		}
		/*
		 * We are finished. The last element in the stack is the result...
		 */
		return treeStack.pop();
	}

}
