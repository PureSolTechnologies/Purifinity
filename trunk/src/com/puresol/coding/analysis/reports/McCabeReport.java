package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.AbstractMcCabeMetric;

public class McCabeReport {

	private static final Translator translator = Translator
			.getTranslator(McCabeReport.class);

	public static String getHTMLReport(AbstractMcCabeMetric mcCabe) {
		String report = "<h2>McCabe Cyclomatic Number</h2>";
		if (mcCabe != null) {
			report += ReportStandards.getQualitySign(mcCabe);
			report += "<br/>";
			report += translator.i18n("Cyclomatic number v(G)") + "="
					+ mcCabe.getCyclomaticNumber();
		} else {
			report += "<p>No measureable for this kind of code range!</p>";
		}
		return report;
	}

}
