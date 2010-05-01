package com.puresol.coding.lang.java.source.parts.packages;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.parts.interfaces.Annotations;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class PackageDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -3655022757942995084L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(Annotations.class);
		expectToken(PackageKeyword.class);
		expectPart(PackageName.class);
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
