package com.puresol.uhura.parser.lr;

import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.parsetable.ParserAction;

public class LRTokenStreamConverter {

	private static final Logger logger = Logger
			.getLogger(LRTokenStreamConverter.class);

	public static AST convert(TokenStream tokenStream, Grammar grammar,
			List<ParserAction> actions) throws ParserException {
		return new LRTokenStreamConverter(tokenStream, grammar, actions)
				.convert();
	}

	private final TokenStream tokenStream;
	private final Grammar grammar;
	private final List<ParserAction> actions;

	private LRTokenStreamConverter(TokenStream tokenStream, Grammar grammar,
			List<ParserAction> actions) {
		super();
		this.tokenStream = tokenStream;
		this.grammar = grammar;
		this.actions = actions;
	}

	private AST convert() throws ParserException {
		try {
			int position = 0;
			Stack<AST> treeStack = new Stack<AST>();
			for (ParserAction action : actions) {
				if (logger.isTraceEnabled()) {
					logger.trace("Action: " + action + "; stack size: "
							+ treeStack.size());
					logger.trace(treeStack.toString());
				}
				switch (action.getAction()) {
				case SHIFT:
					while (tokenStream.get(position).getVisibility() != Visibility.VISIBLE) {
						treeStack.push(new AST(tokenStream.get(position)));
						position++;
					}
					treeStack.push(new AST(tokenStream.get(position)));
					position++;
					break;
				case REDUCE:
					Production production = grammar.getProduction(action
							.getParameter());
					logger.trace(production.toString());
					AST newAST = new AST(production);
					for (int i = 0; i < production.getConstructions().size(); i++) {
						if (treeStack.size() > 0) {
							while ((treeStack.peek().getToken() != null)
									&& (treeStack.peek().getToken()
											.getVisibility() != Visibility.VISIBLE)) {
								newAST.addChildInFront(treeStack.pop());
							}
						}
						newAST.addChildInFront(treeStack.pop());
					}
					treeStack.push(newAST);
					break;
				}
			}
			while (position < tokenStream.size()) {
				treeStack.peek().addChild(new AST(tokenStream.get(position)));
				position++;
			}
			return treeStack.peek();
		} catch (ASTException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage());
		}
	}

}
