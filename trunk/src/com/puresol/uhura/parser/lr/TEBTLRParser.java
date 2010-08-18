package com.puresol.uhura.parser.lr;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.ConstructionType;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Quantity;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;

/**
 * TEBTLR Parser stands for: Trial'n'Error (TE) Backtrack (BT) LR parser. This
 * is the simplest implementation for an LR parser which should be quite stable
 * for most LR grammars, but has the drawback of speed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TEBTLRParser implements Parser {

	private static final Logger logger = Logger.getLogger(TEBTLRParser.class);

	/**
	 * This class represents a single backtrack element within the backtrack
	 * stack of the trial'n'error backtrack parser.
	 * 
	 * @author Rick-Rainer Ludwig
	 * 
	 */
	private class BacktrackPosition {
		private final int position;
		private final int decision;

		public BacktrackPosition(int position, int decision) {
			super();
			this.position = position;
			this.decision = decision;
		}

		/**
		 * @return the position
		 */
		public int getPosition() {
			return position;
		}

		/**
		 * @return the decision
		 */
		public int getDecision() {
			return decision;
		}

	}

	@SuppressWarnings("unused")
	private final Properties options;

	private TokenStream tokenStream = null;
	private ProductionSet productions = null;

	private int currentPosition;
	private final Stack<SyntaxTree> parserStack = new Stack<SyntaxTree>();
	private final Stack<BacktrackPosition> backtrackStack = new Stack<BacktrackPosition>();

	public TEBTLRParser(Properties options) {
		this.options = options;
	}

	@Override
	public void setTokenStream(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	@Override
	public void setProductions(ProductionSet productions) {
		this.productions = productions;
	}

	@Override
	public SyntaxTree call() throws ParserException {
		currentPosition = 0;
		parserStack.clear();
		parse();
		return parserStack.pop();
	}

	private void parse() throws ParserException {
		do {
			printTracingInformation();
			shift();
			while (reduce())
				;
		} while (!stop());
		printTracingInformation();
	}

	private void shift() throws ParserException {
		if (currentPosition >= tokenStream.size()) {
			error();
			return;
		}
		if (parserStack.size() > 1000) {
			throw new ParserException("Stack is too large!");
		}
		Token token = tokenStream.get(currentPosition);
		parserStack.push(new SyntaxTree(token));
		currentPosition++;
	}

	private boolean reduce() throws ParserException {
		List<Production> possibilities = new ArrayList<Production>();
		for (Production rule : productions.getProductions()) {
			if (canReduce(rule)) {
				possibilities.add(rule);
			}
		}
		if (possibilities.size() > 1) {
			throw new ParserException("Ambiguous conditions!");
		}
		if (possibilities.size() == 0) {
			return false;
		}
		reduce(possibilities.get(0));
		return true;
	}

	public boolean canReduce(Production production) throws ParserException {
		List<Construction> elements = production.getConstructions();
		int elementPosition = 0;
		int stackCounter = 0;
		do {
			Construction construction = elements.get(elements.size()
					- elementPosition - 1);
			Quantity quantity = construction.getQuantity();
			int occuranceCounter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack.get(parserStack.size()
						- stackCounter - 1);
				if (construction.getType() == ConstructionType.TOKEN) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getName().equals(construction.getName())) {
						break;
					}
				} else if (construction.getType() == ConstructionType.TEXT) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getText().equals(construction.getText())) {
						break;
					}
				} else if (construction.getType() == ConstructionType.PRODUCTION) {
					Token token = stackElement.getToken();
					if (token != null) {
						break;
					}
					if (!stackElement.getName().equals(construction.getName())) {
						break;
					}
				} else {
					break;
				}
				occuranceCounter++;
				stackCounter++;
				if ((quantity.getMax() != null)
						&& (occuranceCounter == quantity.getMax())) {
					break;
				}
			}
			if ((quantity.getMin() != null)
					&& (occuranceCounter < quantity.getMin())) {
				return false;
			}
			elementPosition++;
		} while ((parserStack.size() - elementPosition - 1 >= 0)
				&& (elementPosition < elements.size()));
		if (elementPosition < elements.size()) {
			return false;
		}
		return (stackCounter > 0);
	}

	public void reduce(Production production) throws ParserException {
		logger.trace("reduce: " + production);
		/*
		 * -1 due to currentPosition was already moved after shift:
		 */
		if ((backtrackStack.size() > 0)
				&& (backtrackStack.peek().getPosition() == currentPosition - 1)) {
			BacktrackPosition position = backtrackStack.pop();
			backtrackStack.push(new BacktrackPosition(position.getPosition(),
					position.getDecision() + 1));
		} else {
			backtrackStack.push(new BacktrackPosition(currentPosition - 1, 1));
		}
		SyntaxTree tree = new SyntaxTree(production.getName());
		List<Construction> elements = production.getConstructions();
		int elementPosition = 0;
		do {
			Construction ruleElement = elements.get(elements.size()
					- elementPosition - 1);
			Quantity quantity = ruleElement.getQuantity();
			int occuranceCounter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack.peek();
				if (ruleElement.getType() == ConstructionType.TOKEN) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getName().equals(ruleElement.getName())) {
						break;
					}
				} else if (ruleElement.getType() == ConstructionType.TEXT) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getText().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ConstructionType.PRODUCTION) {
					Token token = stackElement.getToken();
					if (token != null) {
						break;
					}
					if (!stackElement.getName().equals(ruleElement.getName())) {
						break;
					}
				} else {
					break;
				}
				occuranceCounter++;
				tree.getChildren().add(0, parserStack.pop());
				if ((quantity.getMax() != null)
						&& (occuranceCounter == quantity.getMax())) {
					break;
				}
			}
			if ((quantity.getMin() != null)
					&& (occuranceCounter < quantity.getMin())) {
				throw new ParserException("Rule '" + production
						+ "'was not appliable! Was it not checked first?");
			}
			elementPosition++;
		} while ((parserStack.size() > 0)
				&& (elementPosition < elements.size()));
		parserStack.push(tree);
	}

	private boolean stop() {
		if ((parserStack.size() == 1)
				&& (currentPosition >= tokenStream.size() - 1)) {
			return true;
		}
		return false;
	}

	private void error() throws ParserException {
		backtrack();
	}

	private void backtrack() throws ParserException {
		if (backtrackStack.size() == 0) {
			throw new ParserException();
		}
		logger.trace("Error! Backtracking...");
		BacktrackPosition backtrackPosition = backtrackStack.pop();
		while (currentPosition > backtrackPosition.getPosition()) {
			stepBack();
		}
		shift(); // push which was done before former reduce
		for (int i = 0; i < backtrackPosition.getDecision() - 1; i++) {
			reduce();
		}
		logger.trace("Backtracked:");
		printTracingInformation();
		shift();// push instead of reduce...
	}

	/**
	 * Steps one step backward. If the top element in stack is a syntax tree
	 * from a production, the production is popped and all children of the
	 * production are pushed again except of the last child.
	 */
	private void stepBack() {
		if (currentPosition <= 0) {
			throw new IllegalStateException(
					"Current position is zero or less. Backtracking is not possible!");
		}
		if (parserStack.size() == 0) {
			throw new IllegalStateException(
					"Parser stack is empty. Backtracking is not possible!");
		}
		SyntaxTree tree = parserStack.pop();
		while (tree.getToken() == null) {
			// non-terminal
			for (SyntaxTree child : tree.getChildren()) {
				parserStack.push(child);
			}
			tree = parserStack.pop();
		}
		printTracingInformation();
		currentPosition--;
	}

	private void printTracingInformation() {
		if (!logger.isTraceEnabled()) {
			return;
		}
		logger.trace("===================================");
		logger.trace("TEBTLRParser Tracing Information...");
		logger.trace("===================================");
		logger.trace("Stack:");
		for (SyntaxTree syntaxTree : parserStack) {
			logger.trace(syntaxTree);
		}
	}

}
