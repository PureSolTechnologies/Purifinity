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
    CPP {
	public String getIdentifier() {
	    return "C/C++";
	}
    },
    FORTRAN {
	public String getIdentifier() {
	    return "Fortran";
	}
    },
    JAVA {
	public String getIdentifier() {
	    return "Java";
	}
    },
    PERL {
	public String getIdentifier() {
	    return "Perl";
	}
    },
    PHP {
	public String getIdentifier() {
	    return "PHP";
	}
    },
    TEXT {
	public String getIdentifier() {
	    return "Text";
	}
    };

    @Override
    public abstract String getIdentifier();

}
