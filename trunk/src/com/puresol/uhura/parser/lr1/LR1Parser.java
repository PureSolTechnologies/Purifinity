package com.puresol.uhura.parser.lr1;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserRule;
import com.puresol.uhura.parser.ParserRuleElement;
import com.puresol.uhura.parser.ParserRuleElementType;
import com.puresol.uhura.parser.ParserRuleSet;
import com.puresol.uhura.parser.Quantity;

public class LR1Parser implements Parser {

	@SuppressWarnings("unused")
	private final Properties options;
	private TokenStream tokenStream = null;
	private ParserRuleSet ruleSet = null;

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
	public void setRules(ParserRuleSet rules) {
		this.ruleSet = rules;
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
		parserStack.push(new SyntaxTree(token.getName(), token));
		currentPosition++;
	}

	private boolean reduce() throws ParserException {
		List<ParserRule> possibilities = new ArrayList<ParserRule>();
		for (ParserRule rule : ruleSet.getRuleSet()) {
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

	public boolean canReduce(ParserRule parserRule) throws ParserException {
		List<ParserRuleElement> elements = parserRule.getElements();
		int index = 0;
		do {
			ParserRuleElement ruleElement = elements.get(elements.size()
					- index - 1);
			Quantity quantity = ruleElement.getQuantity();
			int counter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack.get(parserStack.size()
						- index - 1);
				if (ruleElement.getType() == ParserRuleElementType.TOKEN) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getName().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ParserRuleElementType.TEXT) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getText().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ParserRuleElementType.PART) {
					Token token = stackElement.getToken();
					if (token != null) {
						break;
					}
					if (!stackElement.getName().equals(ruleElement.getText())) {
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

	public void reduce(ParserRule parserRule) throws ParserException {
		SyntaxTree tree = new SyntaxTree(parserRule.getName(), null);
		List<ParserRuleElement> elements = parserRule.getElements();
		int index = 0;
		do {
			ParserRuleElement ruleElement = elements.get(elements.size()
					- index - 1);
			Quantity quantity = ruleElement.getQuantity();
			int counter = 0;
			while (true) {
				SyntaxTree stackElement = parserStack
						.get(parserStack.size() - 1);
				if (ruleElement.getType() == ParserRuleElementType.TOKEN) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getName().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ParserRuleElementType.TEXT) {
					Token token = stackElement.getToken();
					if (token == null) {
						break;
					}
					if (!token.getText().equals(ruleElement.getText())) {
						break;
					}
				} else if (ruleElement.getType() == ParserRuleElementType.PART) {
					Token token = stackElement.getToken();
					if (token != null) {
						break;
					}
					if (!stackElement.getName().equals(ruleElement.getText())) {
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
		} while ((parserStack.size() - index - 1 >= 0)
				&& (index < elements.size()));
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
