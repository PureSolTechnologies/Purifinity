/***************************************************************************
 *
 *   AbstractAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.parser.AbstractParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.parser.tokens.TokenStreamIterator;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

abstract public class AbstractAnalyser implements Analyzer {

	private static final long serialVersionUID = -2593701440766091118L;

	private static final Logger logger = Logger
			.getLogger(AbstractAnalyser.class);

	public static Parser createParserInstance(Class<? extends Parser> clazz,
			TokenStream tokenStream) throws ParserException {
		try {
			AbstractParser parser = (AbstractParser)Instances.createInstance(clazz);
			parser.setTokenStreamIterator(new TokenStreamIterator(tokenStream, 0));
			return parser;
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage());
		}
	}

	private final File file;

	private CodeRange rootCodeRange = null;
	private final SymbolTable symbols = new SymbolTable();
	private final Date timeStamp;

	public AbstractAnalyser(File file) {
		this.file = file;
		timeStamp = new Date();
	}

	@Override
	public Date getTimeStamp() {
		return timeStamp;
	}

	@Override
	public File getFile() {
		return file;
	}

	protected void setRootCodeRange(CodeRange rootCodeRange) {
		this.rootCodeRange = rootCodeRange;
	}

	@Override
	public CodeRange getRootCodeRange() {
		return rootCodeRange;
	}

	protected void addSymbol(Symbol symbol) {
		symbols.add(symbol);
	}

	@Override
	public SymbolTable getSymbols() {
		return symbols;
	}

	@Override
	public final List<CodeRange> getNonFragmentCodeRangesRecursively() {
		List<CodeRange> childCodeRanges = rootCodeRange.getChildCodeRanges();
		List<CodeRange> nonFragmentCodeRanges = new ArrayList<CodeRange>();
		for (CodeRange codeRange : childCodeRanges) {
			if (codeRange.getCodeRangeType() != CodeRangeType.FRAGMENT) {
				nonFragmentCodeRanges.add(codeRange);
			}
		}
		return nonFragmentCodeRanges;
	}

	@Override
	public boolean persist(File file) {
		try {
			File persistDirectory = file.getParentFile();
			if (!persistDirectory.exists()) {
				if (!persistDirectory.mkdirs()) {
					return false;
				}
			}
			Persistence.persist(this, file);
			return true;
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result
				+ ((rootCodeRange == null) ? 0 : rootCodeRange.hashCode());
		result = prime * result + ((symbols == null) ? 0 : symbols.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAnalyser other = (AbstractAnalyser) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (rootCodeRange == null) {
			if (other.rootCodeRange != null)
				return false;
		} else if (!rootCodeRange.equals(other.rootCodeRange))
			return false;
		if (symbols == null) {
			if (other.symbols != null)
				return false;
		} else if (!symbols.equals(other.symbols))
			return false;
		return true;
	}
}
