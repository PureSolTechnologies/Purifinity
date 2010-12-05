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
						/*
						 * The for loop is run as many times as the production
						 * contains constructions which are added up for an AST
						 * node.
						 */
						AST poppedAST;
						do {
							poppedAST = treeStack.pop();
							if (poppedAST.isNode()) {
								/*
								 * The popped AST is an own node.
								 */
								if (poppedAST.isStackingAllowed()) {
									/*
									 * The AST is allowed to be stacked, so do
									 * not do anything just add it to children
									 * list at the front position.
									 */
									newAST.addChildInFront(poppedAST);
								} else {
									/*
									 * The AST is not allowed to be stacked. So
									 * the presence for a node with the same
									 * type is checked and the result is added
									 * to that or the node is created.
									 */
									if (newAST.getName().equals(
											poppedAST.getName())) {
										newAST.addChildrenInFront(poppedAST
												.getChildren());
									} else {
										newAST.addChildInFront(poppedAST);
									}
								}
							} else {
								/*
								 * The currently popped AST is not allowed to be
								 * an own node, so all children are added to the
								 * tree in front at the children list.
								 */
								newAST.addChildrenInFront(poppedAST
										.getChildren());
							}
							/*
							 * The while loop is as long as there are ASTs
							 * popped which are non visible tokens...
							 */
						} while ((poppedAST.getToken() != null)
								&& (poppedAST.getToken().getVisibility() != Visibility.VISIBLE));
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
