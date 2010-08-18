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
import com.puresol.uhura.parser.parsingtable.Item;

public class LR0Parser implements Parser {

	private static final Logger logger = Logger.getLogger(LR0Parser.class);

	@SuppressWarnings("unused")
	private final Properties options;
	private final List<Item> items = new ArrayList<Item>();

	private TokenStream tokenStream = null;
	private ProductionSet productions = null;

	private int currentPosition;
	private Stack<SyntaxTree> parserStack = new Stack<SyntaxTree>();
	private SyntaxTree topStackElement = null;
	@SuppressWarnings("unused")
	private Token lookAhead = null;

	public LR0Parser(Properties options) {
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
		currentPosition = -1;
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
		shiftElement();
		setLookAhead();
	}

	private void shiftElement() throws ParserException {
		currentPosition++;
		if (currentPosition >= tokenStream.size()) {
			error();
		}
		if (parserStack.size() > 1000) {
			throw new ParserException("Stack is too large!");
		}
		Token token = tokenStream.get(currentPosition);
		topStackElement = new SyntaxTree(token);
		parserStack.push(topStackElement);
	}

	private void setLookAhead() {
		if (currentPosition + 1 < tokenStream.size()) {
			lookAhead = tokenStream.get(currentPosition + 1);
		} else {
			lookAhead = null;
		}
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

	public boolean canReduce(Production parserRule) throws ParserException {
		List<Construction> elements = parserRule.getConstructions();
		int index = 0;
		do {
			Construction ruleElement = elements.get(elements.size()
					- index - 1);
			Quantity quantity = ruleElement.getQuantity();
			int counter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack.get(parserStack.size()
						- index - counter - 1);
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
				counter++;
				if ((quantity.getMax() != null)
						&& (counter == quantity.getMax())) {
					break;
				}
			}
			if ((quantity.getMin() != null) && (counter < quantity.getMin())) {
				return false;
			}
			index++;
		} while ((parserStack.size() - index - 1 >= 0)
				&& (index < elements.size()));
		if (index < elements.size()) {
			return false;
		}
		return true;
	}

	public void reduce(Production parserRule) throws ParserException {
		SyntaxTree tree = new SyntaxTree(parserRule.getName());
		List<Construction> elements = parserRule.getConstructions();
		int index = 0;
		do {
			Construction ruleElement = elements.get(elements.size()
					- index - 1);
			Quantity quantity = ruleElement.getQuantity();
			int counter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack
						.get(parserStack.size() - 1);
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
				counter++;
				tree.getChildren().add(0, parserStack.pop());
				if ((quantity.getMax() != null)
						&& (counter == quantity.getMax())) {
					break;
				}
			}
			if ((quantity.getMin() != null) && (counter < quantity.getMin())) {
				throw new ParserException("Rule '" + parserRule
						+ "'was not appliable! Was it not checked first?");
			}
			index++;
		} while ((parserStack.size() > 0) && (index < elements.size()));
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
		throw new ParserException();
	}

	private void printTracingInformation() {
		if (!logger.isTraceEnabled()) {
			return;
		}
		logger.trace("================================");
		logger.trace("LR1Parser Tracing Information...");
		logger.trace("================================");
		logger.trace("Stack:");
		for (SyntaxTree syntaxTree : parserStack) {
			logger.trace(syntaxTree);
		}
		logger.trace("Items:");
		for (Item item : items) {
			logger.trace(item.toString());
		}
	}

}
