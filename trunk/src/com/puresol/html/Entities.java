/***************************************************************************
 *
 *   Entities.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.html;

import java.util.Hashtable;

public class Entities {

	private static final Hashtable<String, String> entities;
	static {
		entities = new Hashtable<String, String>();
		entities.put("<", "&lt;");
		entities.put(">", "&gt;");
		entities.put("&", "&amp;");
		entities.put("\"", "&quot;");
	}

	public static String convertToEntities(final String text) {
		String result = text;
		for (String entity : entities.keySet()) {
			result = result.replaceAll(entity, entities.get(entity));
		}
		return result;
	}

	public static String convertFromEntities(final String text) {
		String result = text;
		for (String entity : entities.keySet()) {
			result = result.replaceAll(entities.get(entity), entity);
		}
		return result;
	}
}
