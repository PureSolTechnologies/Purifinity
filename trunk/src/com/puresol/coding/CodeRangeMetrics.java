package com.puresol.coding;

abstract public class CodeRangeMetrics {

    private CodeRange codeRange = null;
    private McCabeMetric mcCabe = null;
    private HalsteadMetric halstead = null;
    private SLOCMetric sloc = null;
    private MaintainabilityIndex maintainability = null;
    private EntropyMetric entropy = null;

    public CodeRangeMetrics(CodeRange codeRange) {
	this.codeRange = codeRange;
	calculate();
    }

    abstract protected void calculate();

    public CodeRange getCodeRange() {
	return codeRange;
    }

    protected void setMcCabeMetric(McCabeMetric mcCabe) {
	this.mcCabe = mcCabe;
    }

    public McCabeMetric getMcCabeMetric() {
	return mcCabe;
    }

    protected void setHalsteadMetric(HalsteadMetric halstead) {
	this.halstead = halstead;
    }

    public HalsteadMetric getHalsteadMetric() {
	return halstead;
    }

    protected void setSLOCMetric(SLOCMetric sloc) {
	this.sloc = sloc;
    }

    public SLOCMetric getSLOCMetric() {
	return sloc;
    }

    protected void setMaintainabilityIndex(
	    MaintainabilityIndex maintainability) {
	this.maintainability = maintainability;
    }

    public MaintainabilityIndex getMaintainabilityIndex() {
	return maintainability;
    }

    protected void setEntropyMetric(EntropyMetric entropy) {
	this.entropy = entropy;
    }

    public EntropyMetric getEntropyMetric() {
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
