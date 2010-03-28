package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.metrics.McCabeMetric;
import com.puresol.html.Anchor;

public class McCabeReport {

	private static final Translator translator = Translator
			.getTranslator(McCabeReport.class);

	public static String getHTMLReport(McCabeMetric mcCabe) {
		String report = Anchor.generate(mcCabe.getName(), "<h2>"
				+ translator.i18n("McCabe Cyclomatic Number") + "</h2>");
		if (mcCabe != null) {
			report += ReportStandards.getQualitySign(mcCabe);
			report += "<br/>";
			report += translator.i18n("Cyclomatic number v(G)") + "="
					+ mcCabe.getCyclomaticNumber();
		} else {
			report += ReportStandards.notMeasurableForCodeRangeMessage();
		}
		return report;
	}
}
