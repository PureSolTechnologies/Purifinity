package com.puresol.uhura.parser.lr;

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
			metaData = new ASTMetaData(
					firstChild.getMetaData().getSourceName(), line, lineNum);
		} else {
			Token token = tree.getToken();
			if (token != null) {
				metaData = new ASTMetaData(token.getMetaData().getSourceName(),
						token.getMetaData().getLine(), token.getMetaData()
								.getLineNum());
			} else {
				metaData = new ASTMetaData("", 0, 0);
			}
		}
		tree.setMetaData(metaData);
		return WalkingAction.PROCEED;
	}
}
