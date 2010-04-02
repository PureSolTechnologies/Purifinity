package com.puresol.coding.evaluator.metric.report;

import java.io.File;

import javax.i18n4j.Translator;

import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.metric.CodeDepth;
import com.puresol.coding.evaluator.metric.EntropyMetric;
import com.puresol.coding.evaluator.metric.HalsteadMetric;
import com.puresol.coding.evaluator.metric.MaintainabilityIndex;
import com.puresol.coding.evaluator.metric.McCabeMetric;
import com.puresol.coding.evaluator.metric.Metric;
import com.puresol.coding.evaluator.metric.SLOCMetric;
import com.puresol.html.HTMLStandards;
import com.puresol.html.Link;
import com.puresol.jars.JarFile;
import com.puresol.utils.Files;

public class ReportStandards {

    private static final Translator translator =
	    Translator.getTranslator(ReportStandards.class);

    public static String getQualitySign(Metric analysis) {
	if (analysis.getQualityLevel() == QualityLevel.HIGH) {
	    return "<font color=\"#00ff00\">HIGH</font>";
	}
	if (analysis.getQualityLevel() == QualityLevel.MEDIUM) {
	    return "<font color=\"#ffff00\">MEDIUM</font>";
	}
	return "<font color=\"#ff0000\">LOW</font>";
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
			.i18n("No measureable for this kind of code range!")
		+ "</p>";
    }

    public static void createLogo(File logo) {
	JarFile.extractResource(HTMLMetricsProject.class
		.getResource("/config/logo.jpeg"), logo);
    }

    public static void createCSS(File css) {
	JarFile.extractResource(HTMLMetricsProject.class
		.getResource("/css/report.css"), css);
    }

    public static boolean createStartHTML(File directory, String name) {
	String html =
		HTMLStandards.getStandardHeader(name, "report.css", false);
	html += "<img src=\"logo.jpeg\"/> <h1>" + name + "</h1>";
	html += "<p>" + HTMLStandards.getCopyright() + "</p>";
	html +=
		"<p>For more information have a look to "
			+ Link.getPureSolTechnolgiesHomePage().toHTML()
			+ "</p>";
	html += HTMLStandards.getStandardFooter();
	return Files.writeFile(directory, "start.html", html);
    }
}
