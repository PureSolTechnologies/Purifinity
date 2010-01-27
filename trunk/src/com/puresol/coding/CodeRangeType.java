/***************************************************************************
 *
 *   CodeRangeType.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

/**
 * This enum lists all kind of code ranges which are supported.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum CodeRangeType implements Identifiable {
    FILE, CLASS, INTERFACE, ENUMERATION, CONSTRUCTOR, METHOD, FUNCTION;

    private static final Translator translator =
	    Translator.getTranslator(CodeRangeType.class);

    public boolean isRunnableCodeSegment() {
	if (this == METHOD) {
	    return true;
	}
	if (this == FUNCTION) {
	    return true;
	}
	return false;
    }

    @Override
    public String getIdentifier() {
	if (this == FILE) {
	    return translator.i18n("File");
	}
	if (this == CLASS) {
	    return translator.i18n("Class");
	}
	if (this == INTERFACE) {
	    return translator.i18n("Interface");
	}
	if (this == ENUMERATION) {
	    return translator.i18n("Enumeration");
	}
	if (this == CONSTRUCTOR) {
	    return translator.i18n("Constructor");
	}
	if (this == METHOD) {
	    return translator.i18n("Method");
	}
	if (this == FUNCTION) {
	    return translator.i18n("Function");
	}

	return "";
    }
}
