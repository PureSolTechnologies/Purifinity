/***************************************************************************
 *
 *   McCabeMetric4Java.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.java;

import com.puresol.coding.CodeEvaluationSystem;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.McCabeMetric;
import com.puresol.coding.QualityLevel;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric4Java extends McCabeMetric {

    public McCabeMetric4Java(CodeRange codeRange) {
	super(codeRange);
    }

    @Override
    public QualityLevel getQualityLevel() {
	if (!CodeEvaluationSystem.isEvaluateMcCabeMetric()) {
	    return QualityLevel.HIGH;
	}
	CodeRange range = getCodeRange();
	if ((range.getType() == CodeRangeType.FILE)
		|| (range.getType() == CodeRangeType.CLASS)
		|| (range.getType() == CodeRangeType.ENUMERATION)) {
	    if (getCyclomaticNumber() < 100) {
		return QualityLevel.HIGH;
	    }
	    if (getCyclomaticNumber() < 125) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.LOW;
	} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
		|| (range.getType() == CodeRangeType.METHOD)
		|| (range.getType() == CodeRangeType.FUNCTION)
		|| (range.getType() == CodeRangeType.INTERFACE)) {
	    if (getCyclomaticNumber() < 15) {
		return QualityLevel.HIGH;
	    }
	    if (getCyclomaticNumber() < 20) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.LOW;
	}
	return QualityLevel.HIGH; // not evaluated...
    }
}
