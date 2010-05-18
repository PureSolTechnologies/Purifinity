package com.puresol.coding.lang.java.source.grammar.packages;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.PackageOrTypeName;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class SingleStaticImportDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -7361469476347249581L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ImportKeyword.class);
		expectToken(StaticKeyword.class);
		Parser name = expectPart(PackageOrTypeName.class);
		if ((name.getEndPosition() - name.getStartPosition() <= 1)
				|| (!name.getTokenStream().get(name.getEndPosition() - 1)
						.getDefinition().equals(Dot.class))) {
			/*
			 * In this case the name is just a single identifier, but at least a
			 * package name and one identifier is expected!
			 */
			throw new PartDoesNotMatchException(this);
		}
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
