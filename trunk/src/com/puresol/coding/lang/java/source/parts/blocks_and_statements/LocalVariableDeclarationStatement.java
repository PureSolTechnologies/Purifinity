package com.puresol.coding.lang.java.source.parts.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class LocalVariableDeclarationStatement extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(LocalVariableDeclaration.class);
	expectToken(Semicolon.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
