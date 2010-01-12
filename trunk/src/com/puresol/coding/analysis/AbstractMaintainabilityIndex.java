/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import javax.i18n4j.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.html.HTMLStandards;

abstract public class AbstractMaintainabilityIndex implements Analysis {

    private static final Translator translator =
	    Translator.getTranslator(AbstractMaintainabilityIndex.class);

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

    private CodeRange codeRange;
    private AbstractSLOCMetric slocMetric;
    private AbstractMcCabeMetric mcCabeMetric;
    private AbstractHalsteadMetric halsteadMetric;

    public AbstractMaintainabilityIndex(CodeRange codeRange,
	    AbstractSLOCMetric slocMetric, AbstractMcCabeMetric mcCabeMetric,
	    AbstractHalsteadMetric halsteadMetric) {
	this.codeRange = codeRange;
	this.slocMetric = slocMetric;
	this.mcCabeMetric = mcCabeMetric;
	this.halsteadMetric = halsteadMetric;
	checkInput();
	calculate();
    }

    private void checkInput() {
	if (codeRange != slocMetric.getCodeRange()) {
	    throw new IllegalArgumentException(
		    "Code ranges must be same!!!");
	}
	if (codeRange != mcCabeMetric.getCodeRange()) {
	    throw new IllegalArgumentException(
		    "Code ranges must be same!!!");
	}
	if (codeRange != halsteadMetric.getCodeRange()) {
	    throw new IllegalArgumentException(
		    "Code ranges must be same!!!");
	}
    }

    private void calculate() {
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

    public CodeRange getCodeRange() {
	return codeRange;
    }
}
