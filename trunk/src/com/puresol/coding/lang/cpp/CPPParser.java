package com.puresol.coding.lang.cpp;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.cpp.source.coderanges.CPPFile;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class CPPParser extends AbstractSourceCodeParser {

	public CPPParser(TokenStream tokenStream) {
		setTokenStream(tokenStream);
		setStartPosition(0);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		try {
			TokenStream tokenStream = getTokenStream();
			addCodeRange(new CPPFile(tokenStream.getFile().getName(),
					tokenStream, 0, tokenStream.getSize() - 1));
			moveForward(0);
			// TODO
		} catch (EndOfTokenStreamException e) {
			// this may happen if there is an empty file...
			return;
		}
	}
}
