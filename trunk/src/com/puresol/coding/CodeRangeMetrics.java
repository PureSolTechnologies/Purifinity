package com.puresol.coding;

public class CodeRangeMetrics {

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

    private void calculate() {
	if (McCabeMetric.isSuitable(codeRange)) {
	    mcCabe = new McCabeMetric(codeRange);
	}
	if (HalsteadMetric.isSuitable(codeRange)) {
	    halstead = new HalsteadMetric(codeRange);
	}
	if (SLOCMetric.isSuitable(codeRange)) {
	    sloc = new SLOCMetric(codeRange);
	}
	if (MaintainabilityIndex.isSuitable(codeRange)) {
	    maintainability = new MaintainabilityIndex(codeRange);
	}
	if (EntropyMetric.isSuitable(codeRange)) {
	    entropy = new EntropyMetric(codeRange);
	}
    }

    public CodeRange getCodeRange() {
	return codeRange;
    }

    public McCabeMetric getMcCabeMetric() {
	return mcCabe;
    }

    public HalsteadMetric getHalsteadMetric() {
	return halstead;
    }

    public SLOCMetric getSLOCMetric() {
	return sloc;
    }

    public MaintainabilityIndex getMaintainabilityIndex() {
	return maintainability;
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
