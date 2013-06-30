/***************************************************************************
 *
 *   LanguageNotSupportedException.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.purifinity.coding.analysis.api;

/**
 * This exception is thrown in case of a programming language which is not
 * supported by a tool.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LanguageNotSupportedException extends Exception {

    private static final long serialVersionUID = 6057970328936737422L;

    public LanguageNotSupportedException() {
	super();
    }

    public LanguageNotSupportedException(String message, Throwable cause) {
	super(message, cause);
    }

    public LanguageNotSupportedException(String message) {
	super(message);
    }

    public LanguageNotSupportedException(Throwable cause) {
	super(cause);
    }
}
