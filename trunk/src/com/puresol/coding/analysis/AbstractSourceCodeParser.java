package com.puresol.coding.analysis;

import java.util.ArrayList;

import com.puresol.parser.AbstractParser;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Inject;
import com.puresol.utils.Instances;

public abstract class AbstractSourceCodeParser extends AbstractParser implements
		SourceCodeParser {

	private final ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

	@Inject(SymbolTable.class)
	private SymbolTable symbols = null;

	protected final void addCodeRange(CodeRange codeRange) {
		if (!codeRanges.contains(codeRange)) {
			codeRanges.add(codeRange);
		}
	}

	protected final void addCodeRanges(ArrayList<CodeRange> codeRanges) {
		this.codeRanges.addAll(codeRanges);
	}

	public final ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}

	public final SymbolTable getSymbolTable() {
		return symbols;
	}

	@Override
	protected final void processPart(Class<? extends Parser> part,
			boolean moveForward) throws PartDoesNotMatchException,
			ParserException {
		try {
			if (SourceCodeParser.class.isAssignableFrom(part)) {
				AbstractSourceCodeParser parser = (AbstractSourceCodeParser) Instances
						.createInstance(part);
				parser.setTokenStream(getTokenStream());
				parser.setStartPosition(getCurrentPosition());
				parser.scan();
				if (parser instanceof SourceCodeParser) {
					ArrayList<CodeRange> ranges = ((SourceCodeParser) parser)
							.getCodeRanges();
					addCodeRanges(ranges);
				}
				if (moveForward) {
					moveForward(parser.getNumberOfTokens());
				}
			} else {
				super.processPart(part, moveForward);
			}
		} catch (EndOfTokenStreamException e) {
			// this may happen at the end of a file...
		} catch (ClassInstantiationException e) {
			throw new ParserException("Could not create instance for '"
					+ part.getName() + "'!");
		}
	}
}
