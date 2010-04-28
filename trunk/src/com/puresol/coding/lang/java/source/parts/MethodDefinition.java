package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodDefinition extends AbstractJavaParser {

	private static final long serialVersionUID = -5002337973133443486L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(Annotation.class) != null)
			;
		acceptPart(MethodModifiers.class);
		expectPart(MethodReturnType.class);
		String name = getCurrentToken().getText();
		expectToken(IdLiteral.class);
		skipNested(LParen.class, RParen.class);
		acceptPart(ThrowsDeclaration.class);
		expectPart(CodeBlock.class);

		finish(name);
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.METHOD;
	}

}
