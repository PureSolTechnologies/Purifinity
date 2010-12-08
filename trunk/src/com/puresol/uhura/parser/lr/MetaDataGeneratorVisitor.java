package com.puresol.uhura.parser.lr;

import com.puresol.trees.TreeVisitor;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.ast.ParserTreeMetaData;
import com.puresol.uhura.lexer.Token;

public class MetaDataGeneratorVisitor implements TreeVisitor<ParserTree> {

	@Override
	public WalkingAction visit(ParserTree tree) {
		ParserTreeMetaData metaData;
		if (tree.hasChildren()) {
			ParserTree firstChild = tree.getChildren().get(0);
			ParserTree lastChild = tree.getChildren().get(
					tree.getChildren().size() - 1);
			int line = firstChild.getMetaData().getLine();
			int lineNum = lastChild.getMetaData().getLine()
					+ lastChild.getMetaData().getLineNum()
					- firstChild.getMetaData().getLine();
			metaData = new ParserTreeMetaData(
					firstChild.getMetaData().getSourceName(), line, lineNum);
		} else {
			Token token = tree.getToken();
			if (token != null) {
				metaData = new ParserTreeMetaData(token.getMetaData().getSourceName(),
						token.getMetaData().getLine(), token.getMetaData()
								.getLineNum());
			} else {
				metaData = new ParserTreeMetaData("", 0, 0);
			}
		}
		tree.setMetaData(metaData);
		return WalkingAction.PROCEED;
	}
}
