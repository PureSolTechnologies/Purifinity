package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Condition extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
//		void condition(void) {
//		    if (accept(oddsym)) {
//		        expression();
//		    } else {
//		        expression();
//		        if (sym == eql || sym == neq || sym == lss || sym == leq || sym == gtr || sym == geq) {
//		            getsym();
//		            expression();
//		        } else {
//		            error("condition: invalid operator");
//		            getsym();
//		        }
//		    }
//		}

		
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
