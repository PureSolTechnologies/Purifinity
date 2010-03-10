/***************************************************************************
 *
 *   CodeRangeType.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

/**
 * This enum lists all kind of code ranges which are supported.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum CodeRangeType implements Identifiable {
    FILE {
	@Override
	public String getIdentifier() {
	    return translator.i18n("File");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return false;
	}
    },
    CLASS {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Class");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return false;
	}
    },
    INTERFACE {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Interface");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return false;
	}
    },
    ENUMERATION {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Enumeration");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return false;
	}
    },
    CONSTRUCTOR {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Constructor");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return true;
	}
    },
    DESTRUCTOR {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Destructor");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return true;
	}
    },
    METHOD {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Method");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return true;
	}
    },
    FUNCTION {
	@Override
	public String getIdentifier() {
	    return translator.i18n("Function");
	}

	@Override
	public boolean isRunnableCodeSegment() {
	    return true;
	}
    };

    private static final Translator translator =
	    Translator.getTranslator(CodeRangeType.class);

    public abstract boolean isRunnableCodeSegment();

    @Override
    public abstract String getIdentifier();
}
