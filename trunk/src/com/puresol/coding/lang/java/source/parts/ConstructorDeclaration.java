package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -5105706064635403458L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(ConstructorModifiers.class);

		String name = getCurrentToken().getText();

		expectToken(IdLiteral.class);
		skipNested(LParen.class, RParen.class);
		acceptPart(ThrowsDeclaration.class);
		expectPart(CodeBlock.class);

		finish(name);
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.CONSTRUCTOR;
	}

}
