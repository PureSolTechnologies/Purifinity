package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Statement extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
//	    if (accept(ident)) {
//	        expect(becomes);
//	        expression();
//	    } else if (accept(callsym)) {
//	        expect(ident);
//	    } else if (accept(beginsym)) {
//	        do {
//	            statement();
//	        } while (accept(semicolon));
//	        expect(endsym);
//	    } else if (accept(ifsym)) {
//	        condition();
//	        expect(thensym);
//	        statement();
//	    } else if (accept(whilesym)) {
//	        condition();
//	        expect(dosym);
//	        statement();
//	    }

		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
