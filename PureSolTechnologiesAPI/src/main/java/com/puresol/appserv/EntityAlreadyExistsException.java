/***************************************************************************
 *
 *   EntityAlreadyExistsException.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.appserv;

public class EntityAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(Class<?> clazz, Object id) {
		super("Entity of type " + clazz.getName() + " with ID "
				+ String.valueOf(id) + " already exists!");
	}
}
