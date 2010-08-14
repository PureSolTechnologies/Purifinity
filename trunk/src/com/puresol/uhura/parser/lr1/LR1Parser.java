package com.puresol.uhura.parser.lr1;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionElement;
import com.puresol.uhura.grammar.production.ProductionElementType;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Quantity;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;

public class LR1Parser implements Parser {

	@SuppressWarnings("unused")
	private final Properties options;
	private TokenStream tokenStream = null;
	private ProductionSet productions = null;

	private int currentPosition;
	private Stack<SyntaxTree> parserStack = new Stack<SyntaxTree>();

	public LR1Parser(Properties options) {
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
			shift();
			while (reduce())
				;
		} while (!stop());
	}

	private void shift() throws ParserException {
		if (currentPosition >= tokenStream.size()) {
			error();
		}
		if (parserStack.size() > 1000) {
			throw new ParserException("Stack is too large!");
		}
		Token token = tokenStream.get(currentPosition);
		parserStack.push(new SyntaxTree(token.getTypeId(), token));
		currentPosition++;
	}

	private boolean reduce() throws ParserException {
		List<Production> possibilities = new ArrayList<Production>();
		for (Production rule : productions.getRuleSet()) {
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
		List<ProductionElement> elements = parserRule.getElements();
		int index = 0;
		do {
			ProductionElement ruleElement = elements.get(elements.size()
					- index - 1);
			Quantity quantity = ruleElement.getQuantity();
			int counter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack.get(parserStack.size()
						- index - 1);
				if (ruleElement.getType() == ProductionElementType.TOKEN) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (token.getTypeId() != ruleElement.getTypeId()) {
						break;
					}
				} else if (ruleElement.getType() == ProductionElementType.TEXT) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getText().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ProductionElementType.PRODUCTION) {
					Token token = stackElement.getToken();
					if (token != null) {
						break;
					}
					if (stackElement.getTypeId() != ruleElement.getTypeId()) {
						break;
					}
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
		SyntaxTree tree = new SyntaxTree(parserRule.getTypeId(), null);
		List<ProductionElement> elements = parserRule.getElements();
		int index = 0;
		do {
			ProductionElement ruleElement = elements.get(elements.size()
					- index - 1);
			Quantity quantity = ruleElement.getQuantity();
			int counter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack
						.get(parserStack.size() - 1);
				if (ruleElement.getType() == ProductionElementType.TOKEN) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (token.getTypeId() != ruleElement.getTypeId()) {
						break;
					}
				} else if (ruleElement.getType() == ProductionElementType.TEXT) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getText().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ProductionElementType.PRODUCTION) {
					Token token = stackElement.getToken();
					if (token != null) {
						break;
					}
					if (stackElement.getTypeId() != ruleElement.getTypeId()) {
						break;
					}
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
				&& (currentPosition == tokenStream.size())) {
			return true;
		}
		return false;
	}

	private void error() throws ParserException {
		throw new ParserException();
	}

}
