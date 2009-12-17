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
		mcCabe = new McCabeMetric(codeRange);
		halstead = new HalsteadMetric(codeRange);
		sloc = new SLOCMetric(codeRange);
		maintainability = new MaintainabilityIndex(codeRange);
		entropy = new EntropyMetric(codeRange);
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
		QualityLevel level = getSLOCMetric().getQualityLevel();
		if (level.getLevel() > getMcCabeMetric().getQualityLevel().getLevel()) {
			level = getMcCabeMetric().getQualityLevel();
		}
		if (level.getLevel() > getHalsteadMetric().getQualityLevel().getLevel()) {
			level = getHalsteadMetric().getQualityLevel();
		}
		if (level.getLevel() > getMaintainabilityIndex().getQualityLevel()
				.getLevel()) {
			level = getMaintainabilityIndex().getQualityLevel();
		}
		if (level.getLevel() > getEntropyMetric().getQualityLevel().getLevel()) {
			level = getEntropyMetric().getQualityLevel();
		}
		return level;
	}
}
