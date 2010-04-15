package com.puresol.coding.lang.fortran;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.coderanges.FortranFile;
import com.puresol.coding.lang.fortran.source.parts.Function;
import com.puresol.coding.lang.fortran.source.parts.Module;
import com.puresol.coding.lang.fortran.source.parts.Program;
import com.puresol.coding.lang.fortran.source.parts.Subroutine;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranParser extends AbstractSourceCodeParser {

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		TokenStream tokenStream = getTokenStream();
		addCodeRange(new FortranFile(tokenStream.getFile().getName(),
				tokenStream, 0, tokenStream.getSize() - 1));

		try {
			moveForward(0);
		} catch (EndOfTokenStreamException e) {
			// this may happen if there is an empty file...
			return;
		}
		if (processPartIfPossible(Program.class)) {
		} else if (processPartIfPossible(Subroutine.class)) {
		} else if (processPartIfPossible(Function.class)) {
		} else if (processPartIfPossible(Module.class)) {
		} else {
			throw new PartDoesNotMatchException(this);
		}
	}
}
