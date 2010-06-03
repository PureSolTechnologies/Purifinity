package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * Using an annotation. '@' is flaged in modifier
 * 
 * annotation : '@' qualifiedName ( '(' ( elementValuePairs | elementValue )?
 * ')' )? ;
 * 
 * @author Rick-Rainer Ludwig
 */

public class Annotation extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(NormalAnnotation.class) != null) {
		} else if (acceptPart(SingleElementAnnotation.class) != null) {
		} else if (acceptPart(MarkerAnnotation.class) != null) {
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
