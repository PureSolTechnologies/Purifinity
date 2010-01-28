package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.McCabeMetric;

public class McCabeReport {

    private static final Translator translator =
	    Translator.getTranslator(McCabeReport.class);

    public static String getHTMLReport(McCabeMetric mcCabe) {
	String report = "<h2>McCabe Cyclomatic Number</h2>";
	if (mcCabe != null) {
	    report += ReportStandards.getQualitySign(mcCabe);
	    report += "<br/>";
	    report +=
		    translator.i18n("Cyclomatic number v(G)") + "="
			    + mcCabe.getCyclomaticNumber();
	} else {
	    report += "<p>No measureable for this kind of code range!</p>";
	}
	return report;
    }

}
