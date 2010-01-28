package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.HalsteadMetric;
import com.puresol.html.HTMLStandards;

public class HalsteadReport {

    private static final Translator translator =
	    Translator.getTranslator(HalsteadReport.class);

    public static String getNumberReport(HalsteadMetric halstead) {
	String report =
		"n1\t" + halstead.get_n1() + "\t"
			+ translator.i18n("Number of different operators")
			+ "\n";
	report +=
		"N1\t" + halstead.get_N1() + "\t"
			+ translator.i18n("Total number operators") + "\n";
	report +=
		"n2\t" + halstead.get_n2() + "\t"
			+ translator.i18n("Number of different operands")
			+ "\n";
	report +=
		"N2\t" + halstead.get_N2() + "\t"
			+ translator.i18n("Total number of operands")
			+ "\n";
	report +=
		"n\t" + Math.round(halstead.get_n() * 100.0) / 100.0
			+ "\t" + translator.i18n("Vocabulary size") + "\n";
	report +=
		"N\t" + Math.round(halstead.get_N() * 100.0) / 100.0
			+ "\t" + translator.i18n("Program length") + "\n";
	report +=
		"HL\t" + Math.round(halstead.get_HL() * 100.0) / 100.0
			+ "\t" + translator.i18n("Halstead length") + "\n";
	report +=
		"HV\t" + Math.round(halstead.get_HV() * 100.0) / 100.0
			+ "\t" + translator.i18n("Halstead volume") + "\n";
	report +=
		"D\t" + Math.round(halstead.get_D() * 100.0) / 100.0
			+ "\t" + translator.i18n("Difficulty level")
			+ "\n";
	report +=
		"L\t" + Math.round(halstead.get_L() * 100.0) / 100.0
			+ "\t" + translator.i18n("Program level") + "\n";
	report +=
		"E\t" + Math.round(halstead.get_E() * 100.0) / 100.0
			+ "\t" + translator.i18n("Effort to implement")
			+ "\n";
	report +=
		"T\t" + Math.round(halstead.get_T() * 100.0) / 100.0
			+ "\t"
			+ translator.i18n("Implementatiom time [s]")
			+ "\n";
	report +=
		"B\t" + Math.round(halstead.get_B() * 100.0) / 100.0
			+ "\t"
			+ translator.i18n("Number of delivered bugs")
			+ "\n";
	return report;
    }

    public static String getOperatorReport(HalsteadMetric halstead) {
	String report = "";
	for (String operator : halstead.getOperators().keySet()) {
	    int number = halstead.getOperators().get(operator);
	    report += operator + "\t" + number + "\n";
	}
	return report;
    }

    public static String getHTMLOperatorReport(HalsteadMetric halstead) {
	return HTMLStandards
		.convertTSVToTable(getOperatorReport(halstead));
    }

    public static String getOperantReport(HalsteadMetric halstead) {
	String report = "";
	for (String operant : halstead.getOperants().keySet()) {
	    int number = halstead.getOperants().get(operant);
	    report += operant + "\t" + number + "\n";
	}
	return report;
    }

    public static String getHTMLOperantReport(HalsteadMetric halstead) {
	return HTMLStandards.convertTSVToTable(getOperantReport(halstead));
    }

    public static String getHTMLReport(HalsteadMetric halsteadMetric) {
	String report = "<h2>Halstead Metric</h2>";
	if (halsteadMetric != null) {
	    report += ReportStandards.getQualitySign(halsteadMetric);
	    report += "<br/>";
	    report +=
		    HTMLStandards
			    .convertTSVToTable(getNumberReport(halsteadMetric));

	    report += "<h3>" + translator.i18n("Operators") + "</h3>";
	    report += getHTMLOperatorReport(halsteadMetric);

	    report += "<h3>" + translator.i18n("Operands") + "</h3>";
	    report += getHTMLOperantReport(halsteadMetric);
	} else {
	    report +=
		    "<p>"
			    + translator
				    .i18n("No measureable for this kind of code range!")
			    + "</p>";
	}
	return report;
    }

}
