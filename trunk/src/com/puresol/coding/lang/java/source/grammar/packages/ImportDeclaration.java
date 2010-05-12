package com.puresol.coding.lang.java.source.grammar.packages;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ImportDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	private static final List<Class<? extends Parser>> IMPORT_DECLARATIONS = new ArrayList<Class<? extends Parser>>();
	static {
		IMPORT_DECLARATIONS.add(SingleTypeImportDeclaration.class);
		IMPORT_DECLARATIONS.add(TypeImportOnDemandDeclaration.class);
		IMPORT_DECLARATIONS.add(SingleStaticImportDeclaration.class);
		IMPORT_DECLARATIONS.add(StaticImportOnDemandDeclaration.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(SingleTypeImportDeclaration.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
