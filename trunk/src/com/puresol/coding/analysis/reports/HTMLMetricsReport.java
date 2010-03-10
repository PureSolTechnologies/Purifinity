/***************************************************************************
 *
 *   HTMLAnalysisReport.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.reports;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.AvailableMetrics;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.analysis.metrics.CodeRangeMetrics;
import com.puresol.coding.analysis.metrics.Metric;
import com.puresol.html.HTMLStandards;

public class HTMLMetricsReport extends AbstractAnalysisReport {

    private static final Translator translator =
	    Translator.getTranslator(HTMLMetricsReport.class);

    public HTMLMetricsReport(CodeRangeMetrics metrics) {
	super(metrics);
    }

    public String getReport() {
	return getReport("", true);
    }

    public String getReport(String css, boolean inlineCSS) {
	String report =
		HTMLStandards.getStandardHeader("CodeMetrics for "
			+ getMetrics().getCodeRange().getName(), css,
			inlineCSS);
	report += getTitle();
	report += getOverview();
	report += getMetricReport();
	report += HTMLStandards.getStandardCopyrightFooter();
	return report;
    }

    private String getTitle() {
	CodeRange range = getMetrics().getCodeRange();
	String html = "<img src=\"logo.jpeg\"/>";
	html +=
		"<h1>AnalysisReport for "
			+ range.getType().getIdentifier() + " '"
			+ range.getName() + "'</h1>";
	return html;
    }

    private String getOverview() {
	String report = "<h2>Overview</h2>";
	report += "<table>";
	for (AvailableMetrics availMetric : getMetrics()
		.getCalculatedMetrics()) {
	    Metric metric = getMetrics().getMetric(availMetric);
	    report +=
		    "<tr><td><a href=\"#" + availMetric.getIdentifier()
			    + "\">" + availMetric.getIdentifier()
			    + "</a></td><td>"
			    + ReportStandards.getQualitySign(metric)
			    + "</td></tr>";
	}
	report += "</table>";
	return report;
    }

    private String getMetricReport() {
	String report = "";
	for (AvailableMetrics availMetric : getMetrics()
		.getCalculatedMetrics()) {
	    report +=
		    ReportStandards.getReport(getMetrics().getMetric(
			    availMetric));
	}
	report += getSourceCode();
	return report;
    }

    private String getSourceCode() {
	String report = "<h2>" + translator.i18n("Source Code") + "</h2>";
	report +=
		HTMLStandards.convertSourceCodeToHTML(getMetrics()
			.getCodeRange().getText());
	return report;
    }

    public QualityLevel getQualityLevel() {
	return getMetrics().getQualityLevel();
    }

}
