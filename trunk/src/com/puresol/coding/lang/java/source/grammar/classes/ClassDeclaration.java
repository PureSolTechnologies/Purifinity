package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * classDeclaration : normalClassDeclaration | enumDeclaration ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(NormalClassDeclaration.class) != null) {
		} else if (acceptPart(EnumDeclaration.class) != null) {
		} else {
			abort();
		}
		finish();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	/**
	 * Returns a list of declared fields.
	 * 
	 * @return A list of declared fields is returned.
	 */
	public List<FieldDeclaration> getFields() {
		return getChildCodeRanges(FieldDeclaration.class);
	}

}
