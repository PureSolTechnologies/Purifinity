/***************************************************************************
 *
 *   QualityLevel.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.evaluation.domain;

import java.io.Serializable;

/**
 * This enum stands for a severity of the issue.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum Severity implements Comparable<Severity>,Serializable {

    BLOCKER, //
    CRITICAL, //
    MAJOR, //
    MINOR, //
    TRIVIAL, //
    NONE, //
    ;

    /**
     * This method returns the minimum quality.
     * 
     * @param severities
     *            are the {@link Severity}s where the minimum is to be found of.
     * @return The minimum {@link Severity} is returned.
     */
    public static Severity getMostSevere(Severity... severities) {
	Severity mostSevere = NONE;
	for (Severity quality : severities) {
	    if (quality.ordinal() < mostSevere.ordinal()) {
		mostSevere = quality;
	    }
	}
	return mostSevere;
    }
}
