package com.puresol.purifinity.uhura.parser.lr;

import com.puresol.purifinity.trees.TreeVisitor;
import com.puresol.purifinity.trees.WalkingAction;
import com.puresol.purifinity.uhura.lexer.Token;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.parser.ParserTreeMetaData;
import com.puresol.purifinity.uhura.source.UnspecifiedSourceCodeLocation;

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
		metaData = new ParserTreeMetaData(new UnspecifiedSourceCodeLocation(), 0, 0);
	    }
	}
	tree.setMetaData(metaData);
	return WalkingAction.PROCEED;
    }
}
