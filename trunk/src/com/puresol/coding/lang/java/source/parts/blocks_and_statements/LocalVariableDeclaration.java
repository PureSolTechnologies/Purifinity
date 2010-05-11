package com.puresol.coding.lang.java.source.parts.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.classes.VariableDeclarators;
import com.puresol.coding.lang.java.source.parts.classes.VariableModifiers;
import com.puresol.coding.lang.java.source.parts.types_values_variables.Type;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class LocalVariableDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(VariableModifiers.class);
	expectPart(Type.class);
	expectPart(VariableDeclarators.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
