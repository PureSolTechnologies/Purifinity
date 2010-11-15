package com.puresol.uhura.parser.lr;

import java.io.File;

import com.puresol.trees.TreeIterator;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTMetaData;
import com.puresol.uhura.lexer.Token;

public class MetaDataGeneratorVisitor implements TreeVisitor<AST> {

	@Override
	public WalkingAction visit(AST tree) {
		ASTMetaData metaData;
		if (tree.hasChildren()) {
			AST firstChild = tree.getChildren().get(0);
			AST lastChild = tree.getChildren().get(
					tree.getChildren().size() - 1);
			int line = firstChild.getMetaData().getLine();
			int lineNum = lastChild.getMetaData().getLine()
					+ lastChild.getMetaData().getLineNum()
					- firstChild.getMetaData().getLine();
			metaData = new ASTMetaData(firstChild.getMetaData().getFile(),
					line, lineNum);
		} else {
			Token token = tree.getToken();
			if (token != null) {
				metaData = new ASTMetaData(new File("unknown"), token
						.getMetaData().getLine(), token.getMetaData()
						.getLineNum());
			} else {
				TreeIterator<AST> iterator = new TreeIterator<AST>(tree);
				if (iterator.goForward()) {
					ASTMetaData meta = iterator.getCurrentNode().getMetaData();
					metaData = new ASTMetaData(meta.getFile(), meta.getLine(),
							1);
				} else {
					throw new RuntimeException(
							"There is no starting point for meta information calculation. There is a token missing!");
				}
			}
		}
		tree.setMetaData(metaData);
		return WalkingAction.PROCEED;
	}
}
