/***************************************************************************
 *
 *   HTMLAnalysisReport.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric.report;

import java.io.File;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.EvaluatorManager;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.metric.CodeDepth;
import com.puresol.coding.evaluator.metric.CodeRangeMetrics;
import com.puresol.coding.evaluator.metric.EntropyMetric;
import com.puresol.coding.evaluator.metric.HalsteadMetric;
import com.puresol.coding.evaluator.metric.MaintainabilityIndex;
import com.puresol.coding.evaluator.metric.McCabeMetric;
import com.puresol.coding.evaluator.metric.Metric;
import com.puresol.coding.evaluator.metric.SLOCMetric;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.html.HTMLStandards;

public class HTMLMetricsReport extends AbstractMetricsReport {

	private static final Translator translator = Translator
			.getTranslator(HTMLMetricsReport.class);

	public HTMLMetricsReport(CodeRangeMetrics metrics) {
		super(metrics);
	}

	public String getReport() {
		return getReport(null, true);
	}

	public String getReport(File cssFile, boolean inlineCSS) {
		String report = HTMLStandards.getStandardHeader("CodeMetrics for "
				+ getMetrics().getCodeRange().getName(), cssFile, inlineCSS,
				null);
		report += getTitle();
		report += getReportText();
		report += HTMLStandards.getStandardCopyrightFooter();
		return report;
	}

	public String getReportText() {
		String report = getOverview();
		report += getMetricReport();
		return report;
	}

	private String getTitle() {
		CodeRange range = getMetrics().getCodeRange();
		String html = "<img src=\"graphics/logo.jpeg\"/>";
		html += "<h1>AnalysisReport for " + range.getType().getIdentifier()
				+ " '" + range.getName() + "'</h1>";
		return html;
	}

	private String getOverview() {
		String report = "<h3>Overview</h3>";
		report += "<table>";
		for (Class<? extends Metric> availMetric : getMetrics()
				.getCalculatedMetrics()) {
			Metric metric = getMetrics().getMetric(availMetric);
			report += "<tr><td><a href=\"#"
					+ EvaluatorManager.getInstance().getName(availMetric)
					+ "\">"
					+ EvaluatorManager.getInstance().getName(availMetric)
					+ "</a></td><td>"
					+ HTMLConverter.convertQualityLevelToHTML(metric
							.getQualityLevel()) + "</td></tr>";
		}
		report += "</table>";
		return report;
	}

	private String getMetricReport() {
		String report = "";
		for (Class<? extends Metric> availMetric : getMetrics()
				.getCalculatedMetrics()) {
			report += getReport(getMetrics().getMetric(availMetric));
		}
		report += getSourceCode();
		return report;
	}

	private String getSourceCode() {
		String report = "<h3>" + translator.i18n("Source Code") + "</h3>";
		report += HTMLStandards.convertSourceCodeToHTML(getMetrics()
				.getCodeRange().getText());
		return report;
	}

	public QualityLevel getQualityLevel() {
		return getMetrics().getQualityLevel();
	}

	public static String getReport(Metric metric) {
		if (metric instanceof SLOCMetric) {
			return SLOCReport.getHTMLReport((SLOCMetric) metric);
		}
		if (metric instanceof CodeDepth) {
			return CodeDepthReport.getHTMLReport((CodeDepth) metric);
		}
		if (metric instanceof McCabeMetric) {
			return McCabeReport.getHTMLReport((McCabeMetric) metric);
		}
		if (metric instanceof HalsteadMetric) {
			return HalsteadReport.getHTMLReport((HalsteadMetric) metric);
		}
		if (metric instanceof MaintainabilityIndex) {
			return MaintainabilityReport
					.getHTMLReport((MaintainabilityIndex) metric);
		}
		if (metric instanceof EntropyMetric) {
			return EntropyReport.getHTMLReport((EntropyMetric) metric);
		}
		throw new IllegalArgumentException();
	}

	public static String notMeasurableForCodeRangeMessage() {
		return "<p>"
				+ translator
						.i18n("Not measureable for this kind of code range!")
				+ "</p>";
	}

}
