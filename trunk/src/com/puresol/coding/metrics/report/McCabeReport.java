package com.puresol.coding.metrics.report;

import javax.i18n4j.Translator;

import com.puresol.coding.evaluator.metric.McCabeMetric;
import com.puresol.coding.evaluator.metric.report.HTMLMetricsReport;
import com.puresol.coding.evaluator.metric.report.McCabeReport;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.html.Anchor;

public class McCabeReport {

	private static final Translator translator = Translator
			.getTranslator(McCabeReport.class);

	public static String getHTMLReport(McCabeMetric mcCabe) {
		String report = Anchor.generate(mcCabe.getName(), "<h3>"
				+ translator.i18n("McCabe Cyclomatic Number") + "</h3>");
		if (mcCabe != null) {
			report += HTMLConverter.convertQualityLevelToHTML(mcCabe
					.getQualityLevel());
			report += "<br/>";
			report += translator.i18n("Cyclomatic number v(G)") + "="
					+ mcCabe.getCyclomaticNumber();
		} else {
			report += HTMLMetricsReport.notMeasurableForCodeRangeMessage();
		}
		return report;
	}
}
