package com.puresol.coding.analysis;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.parser.AbstractParser;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;
import com.puresol.utils.di.Inject;

/**
 * This abstract class extents the normal parser. Added is the functionality to
 * track a symbol table and to handle parts of the source code as code ranges.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractSourceCodeParser extends AbstractParser implements
		SourceCodeParser {

	private static final Logger logger = Logger
			.getLogger(AbstractSourceCodeParser.class);

	private final ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

	@Inject
	private SymbolTable symbols = null;

	protected AbstractSourceCodeParser() {
		super();
	}

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

	public final void setSymbolTable(SymbolTable symbols) {
		this.symbols = symbols;
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
				parser.setStartPosition(this.getCurrentPosition());
				parser.setTokenStream(getTokenStream());
				parser.setSymbolTable(symbols);
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
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage());
		}
	}
}
