/***************************************************************************
 *
 *   BeanNotAvailableException.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.appserv;

public class BeanNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public BeanNotAvailableException(String name, Class<?> cast) {
		super("Bean '" + name + "' of type " + cast.getName()
				+ " is not available on server!");
	}
}
