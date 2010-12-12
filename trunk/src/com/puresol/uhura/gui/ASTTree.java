package com.puresol.uhura.gui;

import javax.swingx.Tree;

import com.puresol.uhura.ast.ParserTree;

/**
 * This class shows and handles ASTs produced by NyotaUhura.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ASTTree extends Tree {

	private static final long serialVersionUID = -5302250366795217741L;

	private ParserTree ast = null;

	public ASTTree() {
		super();
	}

	public ASTTree(ParserTree ast) {
		super();
		setAST(ast);
	}

	public void setAST(ParserTree ast) {
		this.ast = ast;
		refresh();
	}

	public ParserTree getAst() {
		return ast;
	}

	public void setAst(ParserTree ast) {
		this.ast = ast;
	}

	private void refresh() {
		this.removeAll();
	}
}
