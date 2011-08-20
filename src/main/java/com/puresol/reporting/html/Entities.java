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

public enum Entities {

    AMP("&", "&amp;"), LT("<", "&lt;"), GT(">", "&gt;"), QUOT("\"",
	    "&quot;"), UUML("ü", "&uuml;"), UUML_C("Ü", "&Uuml;"), OUML(
	    "ö", "&ouml;"), OUML_C("Ö", "&Ouml;"), AUML("ä", "&auml;"), AUML_C(
	    "Ä", "&Auml;"), SZLIG("ß", "&szlig;");

    private final String sign;
    private final String entity;

    private Entities(String sign, String entity) {
	this.sign = sign;
	this.entity = entity;
    }

    public String getSign() {
	return sign;
    }

    public String getEntity() {
	return entity;
    }

    public static String convertToEntities(final String text) {
	String result = text;
	for (Entities entity : Entities.class.getEnumConstants()) {
	    result =
		    result
			    .replaceAll(entity.getSign(), entity
				    .getEntity());
	}
	return result;
    }

    public static String convertFromEntities(final String text) {
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
