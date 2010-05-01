package com.puresol.coding.lang.java.source.parts.packages;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.source.parts.types_values_variables.TypeName;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class StaticImportOnDemandDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -7361469476347249581L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(StaticKeyword.class);
		expectToken(ImportKeyword.class);
		expectPart(TypeName.class);
		expectToken(Dot.class);
		expectToken(Star.class);
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
