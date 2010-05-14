package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * blockStatement : localVariableDeclarationStatement |
 * classOrInterfaceDeclaration | statement ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BlockStatement extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(LocalVariableDeclarationStatement.class) != null) {
		} else if (acceptPart(ClassDeclaration.class) != null) {
		} else if (acceptPart(Statement.class) != null) {
		} else {
			abort();
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
