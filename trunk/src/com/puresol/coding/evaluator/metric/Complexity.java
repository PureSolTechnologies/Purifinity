/***************************************************************************
 *
 *   Complexity.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric;

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

/**
 * This enumeration stands for a complexity. This was derived from the
 * categories of the CoCoMo model. The complexity is defined in three
 * levels: low, medium and high complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum Complexity implements Identifiable {
    LOW {
	@Override
	public String getIdentifier() {
	    return translator.i18n("low");
	}
    },
    MEDIUM {
	@Override
	public String getIdentifier() {
	    return translator.i18n("medium");
	}
    },
    HIGH {
	@Override
	public String getIdentifier() {
	    return translator.i18n("high");
	}
    };

    private static final Translator translator =
	    Translator.getTranslator(Complexity.class);

    @Override
    public abstract String getIdentifier();
}
