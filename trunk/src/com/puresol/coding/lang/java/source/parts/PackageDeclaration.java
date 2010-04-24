package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class PackageDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -3655022757942995084L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(PackageKeyword.class);
		expectToken(IdLiteral.class);
		while (acceptToken(Dot.class)) {
			expectToken(IdLiteral.class);
		}
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
