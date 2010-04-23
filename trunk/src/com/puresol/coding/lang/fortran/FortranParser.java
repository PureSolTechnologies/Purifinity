package com.puresol.coding.lang.fortran;

import com.puresol.coding.lang.fortran.source.coderanges.FortranFile;
import com.puresol.coding.lang.fortran.source.parts.Function;
import com.puresol.coding.lang.fortran.source.parts.Module;
import com.puresol.coding.lang.fortran.source.parts.Program;
import com.puresol.coding.lang.fortran.source.parts.Subroutine;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranParser extends AbstractFortranParser {

    private static final long serialVersionUID = 5137487026150640755L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	TokenStream tokenStream = getTokenStream();
	addCodeRange(new FortranFile(tokenStream.getFile().getName(),
		tokenStream, 0, tokenStream.getSize() - 1));

	try {
	    moveToNextVisible(0);
	} catch (EndOfTokenStreamException e) {
	    // this may happen if there is an empty file...
	    return;
	}
	if (acceptPart(Program.class)) {
	} else if (acceptPart(Subroutine.class)) {
	} else if (acceptPart(Function.class)) {
	} else if (acceptPart(Module.class)) {
	} else {
	    throw new PartDoesNotMatchException(this);
	}
    }
}
