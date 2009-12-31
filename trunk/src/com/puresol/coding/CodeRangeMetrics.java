/***************************************************************************
 *
 *   CodeRangeMetrics.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

abstract public class CodeRangeMetrics {

    private CodeRange codeRange = null;
    private AbstractMcCabeMetric mcCabe = null;
    private AbstractHalsteadMetric halstead = null;
    private AbstractSLOCMetric sloc = null;
    private AbstractMaintainabilityIndex maintainability = null;
    private AbstractEntropyMetric entropy = null;

    public CodeRangeMetrics(CodeRange codeRange) {
	this.codeRange = codeRange;
	calculate();
    }

    abstract protected void calculate();

    public CodeRange getCodeRange() {
	return codeRange;
    }

    protected void setMcCabeMetric(AbstractMcCabeMetric mcCabe) {
	this.mcCabe = mcCabe;
    }

    public AbstractMcCabeMetric getMcCabeMetric() {
	return mcCabe;
    }

    protected void setHalsteadMetric(AbstractHalsteadMetric halstead) {
	this.halstead = halstead;
    }

    public AbstractHalsteadMetric getHalsteadMetric() {
	return halstead;
    }

    protected void setSLOCMetric(AbstractSLOCMetric sloc) {
	this.sloc = sloc;
    }

    public AbstractSLOCMetric getSLOCMetric() {
	return sloc;
    }

    protected void setMaintainabilityIndex(
	    AbstractMaintainabilityIndex maintainability) {
	this.maintainability = maintainability;
    }

    public AbstractMaintainabilityIndex getMaintainabilityIndex() {
	return maintainability;
    }

    protected void setEntropyMetric(AbstractEntropyMetric entropy) {
	this.entropy = entropy;
    }

    public AbstractEntropyMetric getEntropyMetric() {
	return entropy;
    }

    public QualityLevel getQualityLevel() {
	QualityLevel level = QualityLevel.HIGH;
	if (getSLOCMetric() != null) {
	    level =
		    QualityLevel.getMinLevel(level, getSLOCMetric()
			    .getQualityLevel());
	}
	if (getMcCabeMetric() != null) {
	    level =
		    QualityLevel.getMinLevel(level, getMcCabeMetric()
			    .getQualityLevel());
	}
	if (getHalsteadMetric() != null) {
	    level =
		    QualityLevel.getMinLevel(level, getHalsteadMetric()
			    .getQualityLevel());
	}
	if (getMaintainabilityIndex() != null) {
	    level =
		    QualityLevel.getMinLevel(level,
			    getMaintainabilityIndex().getQualityLevel());
	}
	if (getEntropyMetric() != null) {
	    level =
		    QualityLevel.getMinLevel(level, getEntropyMetric()
			    .getQualityLevel());
	}
	return level;
    }
}
