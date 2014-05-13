package com.puresoltechnologies.purifinity.server.metrics.spi;

import java.io.Serializable;

import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.server.metrics.spi.sloc.SLOCType;

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
	 * @param name
	 *            is the token name.
	 * @return The {@link SLOCType} is returned. If the type is not COMMENT,
	 *         BLANK or PRODUCTIVE (e.g. is a line terminator), PHYSICAL is
	 *         returned. null must never be returned!!!!
	 */
	public SLOCType getTypeByName(String name);

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of token in context to the language.
	 * 
	 * @param token
	 *            is the token to be checked.
	 * @return The {@link SLOCType} is returned. If the type is not COMMENT,
	 *         BLANK or PRODUCTIVE (e.g. is a line terminator), PHYSICAL is
	 *         returned. null must never be returned!!!!
	 */
	public SLOCType getType(AbstractTerminal token);

}
