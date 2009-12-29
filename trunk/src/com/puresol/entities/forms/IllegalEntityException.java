/***************************************************************************
 *
 *   IllegalEntityException.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.entities.forms;

public class IllegalEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalEntityException(Object entity) {
		super("Entity '" + entity.getClass().getName()
				+ "' has illegal format!");
	}

}
