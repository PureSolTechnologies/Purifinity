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

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractEntropyMetric;
import com.puresol.coding.analysis.AbstractHalsteadMetric;
import com.puresol.coding.analysis.AbstractMaintainabilityIndex;
import com.puresol.coding.analysis.AbstractMcCabeMetric;
import com.puresol.coding.analysis.AbstractSLOCMetric;
import com.puresol.coding.analysis.CodeRangeMetrics;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.html.HTMLStandards;

public class HTMLAnalysisReport extends AbstractAnalysisReport {

    private static final Translator translator =
	    Translator.getTranslator(HTMLAnalysisReport.class);

    public HTMLAnalysisReport(CodeRangeMetrics metrics) {
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
	report += HTMLStandards.getStandardFooter();
	return report;
    }

    private String getTitle() {
	CodeRange range = getMetrics().getCodeRange();
	String html = "<img src=\"logo.jpeg\"/>";
	html +=
		"<h1> AnalysisReport for "
			+ range.getType().getIdentifier() + " '"
			+ range.getName() + "'</h1>";
	return html;
    }

    private String getOverview() {
	String report = "<h2>Overview</h2>";
	AbstractSLOCMetric sloc = getMetrics().getSLOCMetric();
	AbstractMcCabeMetric mcCabe = getMetrics().getMcCabeMetric();
	AbstractHalsteadMetric halstead = getMetrics().getHalsteadMetric();
	AbstractMaintainabilityIndex maintainability =
		getMetrics().getMaintainabilityIndex();
	AbstractEntropyMetric entropy = getMetrics().getEntropyMetric();
	report += "<table>";
	if (sloc != null) {
	    report +=
		    "<tr><td>SLOC Metric</td><td>"
			    + ReportStandards.getQualitySign(sloc)
			    + "</td><td>"
			    + translator
				    .i18n("Statistics on source code fo lines")
			    + "</td></tr>";
	}
	if (mcCabe != null) {
	    report +=
		    "<tr><td>McCabe Metric</td><td>"
			    + ReportStandards.getQualitySign(mcCabe)
			    + "</td><td>"
			    + translator.i18n("Cyclomatic number")
			    + "</td></tr>";
	}
	if (halstead != null) {
	    report +=
		    "<tr><td>Halstead Metric</td><td>"
			    + ReportStandards.getQualitySign(halstead)
			    + "</td><td>"
			    + translator
				    .i18n("Statistics on operators and operands")
			    + "</td></tr>";
	}
	if (maintainability != null) {
	    report +=
		    "<tr><td>Maintainability Index</td><td>"
			    + ReportStandards
				    .getQualitySign(maintainability)
			    + "</td><td>"
			    + translator.i18n("Maintainability Index")
			    + "</td></tr>";
	}
	if (entropy != null) {
	    report +=
		    "<tr><td>Entropy Metric</td><td>"
			    + ReportStandards.getQualitySign(entropy)
			    + "</td><td>"
			    + translator
				    .i18n("Inspections on basis of entropy calculations")
			    + "</td></tr>";
	}
	report += "</table>";
	return report;
    }

    private String getMetricReport() {
	String report =
		SLOCReport.getHTMLReport(getMetrics().getSLOCMetric());
	report +=
		McCabeReport.getHTMLReport(getMetrics().getMcCabeMetric());
	report +=
		HalsteadReport.getHTMLReport(getMetrics()
			.getHalsteadMetric());
	report +=
		MaintainabilityReport.getHTMLReport(getMetrics()
			.getMaintainabilityIndex());
	report +=
		EntropyReport.getHTMLReport(getMetrics()
			.getEntropyMetric());
	report += getSourceCode();
	return report;
    }

    private String getSourceCode() {
	String report = "<h2>" + translator.i18n("Source Code") + "</h2>";
	report +=
		ReportStandards.convertSourceCodeToHTML(getMetrics()
			.getCodeRange().getText());
	return report;
    }

    public QualityLevel getQualityLevel() {
	return getMetrics().getQualityLevel();
    }

}
