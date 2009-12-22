package com.puresol.coding.java;

import com.puresol.coding.CodeEvaluationSystem;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.QualityLevel;
import com.puresol.coding.SLOCMetric;

/**
 * This class calculates a small statistics for a source code for source
 * lines of code. It's a simple counting statistics for productive, comment
 * and blank lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetric4Java extends SLOCMetric {

    public SLOCMetric4Java(CodeRange codeRange) {
	super(codeRange);
    }

    private QualityLevel getQualityLevelLineCount() {
	CodeRange range = getCodeRange();
	if ((range.getType() == CodeRangeType.FILE)
		|| (range.getType() == CodeRangeType.CLASS)
		|| (range.getType() == CodeRangeType.ENUMERATION)) {
	    if (getPhyLOC() > 2500) {
		return QualityLevel.LOW;
	    }
	    if (getPhyLOC() > 1000) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.HIGH;
	} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
		|| (range.getType() == CodeRangeType.METHOD)
		|| (range.getType() == CodeRangeType.FUNCTION)
		|| (range.getType() == CodeRangeType.INTERFACE)) {
	    if (getPhyLOC() > 40) {
		return QualityLevel.LOW;
	    }
	    if (getPhyLOC() > 25) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.HIGH;
	}
	return QualityLevel.HIGH; // not evaluated...
    }

    private QualityLevel getQualityLevelLineLength() {
	if ((getTrimmedProductiveLineStatistics().getMax() > 80)
		|| (getProductiveLineStatistics().getMax() > 80)
		|| (getLineStatistics().getMax() > 80)) {
	    return QualityLevel.LOW;
	}
	if ((getTrimmedProductiveLineStatistics().getAvg() > 40)
		|| (getProductiveLineStatistics().getAvg() > 50)
		|| (getLineStatistics().getAvg() > 50)) {
	    return QualityLevel.MEDIUM;
	}
	return QualityLevel.HIGH;
    }

    @Override
    public QualityLevel getQualityLevel() {
	if (!CodeEvaluationSystem.isEvaluateSLOCMetric()) {
	    return QualityLevel.HIGH;
	}
	QualityLevel levelLineCount = getQualityLevelLineCount();
	QualityLevel levelLineLength = this.getQualityLevelLineLength();
	if (levelLineCount.getLevel() < levelLineLength.getLevel()) {
	    return levelLineCount;
	}
	return levelLineLength;
    }

}
