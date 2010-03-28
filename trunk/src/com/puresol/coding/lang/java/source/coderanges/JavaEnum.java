package com.puresol.coding.lang.java.source.coderanges;

import com.puresol.coding.analysis.AbstractCodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.parser.TokenStream;

public class JavaEnum extends AbstractCodeRange {

	public JavaEnum(String name, TokenStream tokenStream, int start, int stop) {
		super(name, tokenStream, start, stop);
	}

	@Override
	public String getLanguage() {
		return "Java";
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.ENUMERATION;
	}

	@Override
	public String getTypeName() {
		return getType().getIdentifier();
	}

}
