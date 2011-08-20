/***************************************************************************
 *
 *   Entities.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.reporting.html;

public enum WhiteSpaces {

    SPACE(" ", "&nbsp;"), TAB("\t", "&nbsp;&nbsp;&nbsp;&nbsp;"), LINE_BREAK(
	    "\n", "<br/>");

    private final String sign;
    private final String entity;

    private WhiteSpaces(String sign, String entity) {
	this.sign = sign;
	this.entity = entity;
    }

    public String getSign() {
	return sign;
    }

    public String getEntity() {
	return entity;
    }

    public static String convertFromWhiteSpaces(final String text) {
	String result = text;
	for (Entities entity : Entities.class.getEnumConstants()) {
	    result =
		    result
			    .replaceAll(entity.getSign(), entity
				    .getEntity());
	}
	return result;
    }

    public static String convertToWhiteSpaces(final String text) {
	String result = text;
	for (Entities entity : Entities.class.getEnumConstants()) {
	    result =
		    result
			    .replaceAll(entity.getEntity(), entity
				    .getSign());
	}
	return result;
    }
}
