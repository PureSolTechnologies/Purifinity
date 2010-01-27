/***************************************************************************
 *
 *   Language.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang;

import com.puresol.data.Identifiable;

/**
 * This enumeration reflects all supported programming languages. This enum
 * also lists languages which are planned to be supported.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum Language implements Identifiable {
    CPP, FORTRAN, JAVA, PERL, PHP, TEXT;

    @Override
    public String getIdentifier() {
	if (this == CPP) {
	    return "C/C++";
	}
	if (this == FORTRAN) {
	    return "Fortran";
	}
	if (this == JAVA) {
	    return "Java";
	}
	if (this == PERL) {
	    return "Perl";
	}
	if (this == PHP) {
	    return "PHP";
	}
	if (this == TEXT) {
	    return "Text";
	}
	return null;
    }
}
