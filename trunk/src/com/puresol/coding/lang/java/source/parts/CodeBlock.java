package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class CodeBlock extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		skipNested(LCurlyBracket.class, RCurlyBracket.class);
//	    if (accept(constsym)) {
//	        do {
//	            expect(ident);
//	            expect(eql);
//	            expect(number);
//	        } while (accept(comma));
//	        expect(semicolon);
//	    }
//	    if (accept(varsym)) {
//	        do {
//	            expect(ident);
//	        } while (accept(comma));
//	        expect(semicolon);
//	    }
//	    while (accept(procsym)) {
//	        expect(ident);
//	        expect(semicolon);
//	        block();
//	        expect(semicolon);
//	    }
//	    statement();

		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
