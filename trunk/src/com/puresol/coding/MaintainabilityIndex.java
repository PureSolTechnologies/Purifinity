package com.puresol.coding;

import javax.i18n4j.Translator;

import com.puresol.html.HTMLStandards;

public class MaintainabilityIndex extends AbstractMetric {

    private static final Translator translator =
	    Translator.getTranslator(MaintainabilityIndex.class);

    /**
     * MaintainabilityIndex without comment.
     */
    private double MIwoc;
    /**
     * MaintainabilityIndex comment weight
     */
    private double MIcw;
    /**
     * MaintainabilityIndex
     */
    private double MI;

    public MaintainabilityIndex(CodeRange codeRange) {
	super(codeRange);
	calculate();
    }

    private void calculate() {
	CodeRange codeRange = getCodeRange();
	SLOCMetric slocMetric = new SLOCMetric(codeRange);
	McCabeMetric mcCabeMetric = new McCabeMetric(codeRange);
	HalsteadMetric halsteadMetric = new HalsteadMetric(codeRange);

	MIwoc =
		171.0 - 5.2 * Math.log(halsteadMetric.get_HV()) - 0.23
			* mcCabeMetric.getCyclomaticNumber() - 16.2
			* Math.log(slocMetric.getPhyLOC() * 100.0 / 171.0);
	MIcw =
		50 * Math.sin(Math.sqrt(2.4
			* (double) slocMetric.getComLOC()
			/ (double) slocMetric.getPhyLOC()));
	MI = MIwoc + MIcw;
    }

    public void print() {
	System.out.println("MIwoc = " + MIwoc);
	System.out.println("MIcw = " + MIcw);
	System.out.println("MI = " + MI);
    }

    public double getMIWoc() {
	return MIwoc;
    }

    public double getMIcw() {
	return MIcw;
    }

    public double getMI() {
	return MI;
    }

    @Override
    public QualityLevel getQualityLevel() {
	if (!CodeEvaluationSystem.isEvaluateMaintainabilityIndex()) {
	    return QualityLevel.HIGH;
	}
	CodeRange range = getCodeRange();
	if ((range.getType() == CodeRangeType.FILE)
		|| (range.getType() == CodeRangeType.CLASS)
		|| (range.getType() == CodeRangeType.ENUMERATION)) {
	    if (MI > 86) {
		return QualityLevel.HIGH;
	    }
	    if (MI > 65) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.LOW;
	} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
		|| (range.getType() == CodeRangeType.METHOD)
		|| (range.getType() == CodeRangeType.FUNCTION)
		|| (range.getType() == CodeRangeType.INTERFACE)) {
	    if (MI > 85) {
		return QualityLevel.HIGH;
	    }
	    if (MI > 65) {
		return QualityLevel.MEDIUM;
	    }
	    return QualityLevel.LOW;
	}
	return QualityLevel.HIGH; // not evaluated...
    }

    public static boolean isSuitable(CodeRange codeRange) {
	if (codeRange.getType() == CodeRangeType.FILE) {
	    return false;
	}
	if (codeRange.getType() == CodeRangeType.CLASS) {
	    return false;
	}
	return true;
    }

    public String getReport() {
	String report =
		"MIwoc\t"
			+ Math.round(getMIWoc() * 100.0)
			/ 100.0
			+ "\t"
			+ translator
				.i18n("Maintainability index without comments")
			+ "\n";
	report +=
		"MIcw\t"
			+ Math.round(getMIcw() * 100.0)
			/ 100.0
			+ "\t"
			+ translator
				.i18n("Maintainability index comment weight")
			+ "\n";
	report +=
		"MI\t" + Math.round(getMI() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Maintainability index") + "\n";
	return report;
    }

    public String getHTMLReport() {
	return HTMLStandards.convertTSVToTable(getReport());
    }
}
