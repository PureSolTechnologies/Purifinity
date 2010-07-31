/***************************************************************************
 *
 *   StrangeSituationException.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.exceptions;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

/**
 * This is a standard exception which is always thrown in situations which never
 * should occur! For example there are a lot of classes transporting information
 * about objects and their methods. These objects shoult check their content
 * during set. A later invoke of these method never should provide an exception.
 * If it is so, then we have a strange situation and the program is to be
 * aborted with this exception.
 * 
 * This exception should never be seen by customers!!! It's always a sign for a
 * bad implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StrangeSituationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(StrangeSituationException.class);
	private static final Translator translator = Translator
			.getTranslator(StrangeSituationException.class);

	static public final String STRANGE_SITUATION_MESSAGE = translator
			.i18n("Something very strange is going on here!\n"
					+ "Nothing can be done to proceed here.\n"
					+ "Aborting is the only option (if not miracle is happening)... ;-(");

	public StrangeSituationException(Throwable e) {
		super(STRANGE_SITUATION_MESSAGE);
		logger.error(e.getMessage(), e);
		logger.fatal(STRANGE_SITUATION_MESSAGE);
	}

	public StrangeSituationException(String message) {
		super(STRANGE_SITUATION_MESSAGE);
		logger.error(message);
		logger.fatal(STRANGE_SITUATION_MESSAGE);
	}
}
