package com.puresol.coding;

abstract public class AbstractAnalysisReport implements AnalysisReport {

	private CodeRange range = null;
	private McCabeMetric mcCabe = null;
	private HalsteadMetric halstead = null;
	private SLOCMetric sloc = null;
	private MaintainabilityIndex maintainability = null;
	private EntropyMetric entropy = null;

	public AbstractAnalysisReport(CodeRange range) {
		this.range = range;
		calculate();
	}

	private void calculate() {
		mcCabe = new McCabeMetric(range);
		halstead = new HalsteadMetric(range);
		sloc = new SLOCMetric(range);
		maintainability = new MaintainabilityIndex(range);
		entropy = new EntropyMetric(range);
	}

	public CodeRange getCodeRange() {
		return range;
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
}
