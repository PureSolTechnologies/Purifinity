package com.puresol.purifinity.coding.metrics.sloc;

import java.io.Serializable;

import com.puresol.purifinity.uhura.lexer.Token;

/**
 * This interface is used to implement a part of the SLOC metric implementation
 * within language packages. This implementation is used to distinguish between
 * the different kind of lines: blank, comment and productive.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface LanguageDependedSLOCMetric extends Serializable {

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of token in context to the language.
	 * 
	 * @param token
	 *            is the token to be checked.
	 * @return The SLOCType is returned. If the type is not COMMENT, BLANK or
	 *         PRODUCTIVE (e.g. is a line terminator), PHYSICAL is returned.
	 *         null must never be returned!!!!
	 */
	public SLOCType getType(Token token);

}
