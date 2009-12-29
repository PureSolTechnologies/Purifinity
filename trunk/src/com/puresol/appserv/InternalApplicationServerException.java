/***************************************************************************
 *
 *   InternalApplicationServerException.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.appserv;

public class InternalApplicationServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public InternalApplicationServerException() {
		super("An internal error occured within the application server!");
	}
}
