package com.puresol.coding.lang.java.source.grammar.packages;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ImportDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(SingleTypeImportDeclaration.class) != null) {
		} else if (acceptPart(TypeImportOnDemandDeclaration.class) != null) {
		} else if (acceptPart(SingleStaticImportDeclaration.class) != null) {
		} else if (acceptPart(StaticImportOnDemandDeclaration.class) != null) {
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
