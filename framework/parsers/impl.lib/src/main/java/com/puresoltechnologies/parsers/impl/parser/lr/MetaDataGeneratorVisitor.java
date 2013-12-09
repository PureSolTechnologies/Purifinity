package com.puresoltechnologies.parsers.impl.parser.lr;

import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.parsers.impl.lexer.Token;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.parser.ParserTreeMetaData;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;

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
			metaData = new ParserTreeMetaData(firstChild.getMetaData()
					.getSource(), line, lineNum);
		} else {
			Token token = tree.getToken();
			if (token != null) {
				metaData = new ParserTreeMetaData(token.getMetaData()
						.getSource(), token.getMetaData().getLine(), token
						.getMetaData().getLineNum());
			} else {
				metaData = new ParserTreeMetaData(
						new UnspecifiedSourceCodeLocation(), 0, 0);
			}
		}
		tree.setMetaData(metaData);
		return WalkingAction.PROCEED;
	}
}
