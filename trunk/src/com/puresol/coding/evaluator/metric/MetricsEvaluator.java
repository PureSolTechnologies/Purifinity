package com.puresol.coding.evaluator.metric;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.metric.report.HTMLMetricsReport;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class MetricsEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = 895546832156565427L;

	private static final Translator translator = Translator
			.getTranslator(MetricsEvaluator.class);

	public static final String NAME = "Metrics Evaluator";
	public static final String DESCRIPTION = translator
			.i18n("This evaluator calculates the most common code metrics.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(
				"CodeEvaluation.Evaluators.Metrics.EnableSLOC",
				"Switches the calculation of SLOC Metric on and off.",
				Boolean.class, "true"));
		SUPPORTED_PROPERTIES.add(new Property(
				"CodeEvaluation.Evaluators.Metrics.EnableMcCabe",
				"Switches the calculation of McCabe Metric on and off.",
				Boolean.class, "true"));
		SUPPORTED_PROPERTIES.add(new Property(
				"CodeEvaluation.Evaluators.Metrics.EnableHalstead",
				"Switches the calculation of Halstead Metric on and off.",
				Boolean.class, "true"));
		SUPPORTED_PROPERTIES
				.add(new Property(
						"CodeEvaluation.Evaluators.Metrics.EnableMaintainability",
						"Switches the calculation of Maintainability Index on and off.",
						Boolean.class, "true"));
		SUPPORTED_PROPERTIES.add(new Property(
				"CodeEvaluation.Evaluators.Metrics.EnableCodeDepth",
				"Switches the calculation of CodeDepth Metric on and off.",
				Boolean.class, "true"));
		SUPPORTED_PROPERTIES.add(new Property(
				"CodeEvaluation.Evaluators.Metrics.EnableEntropy",
				"Switches the calculation of Entropy Metric on and off.",
				Boolean.class, "true"));
	}

	private final ProjectAnalyser analyser;
	private final Hashtable<CodeRange, CodeRangeMetrics> metrics = new Hashtable<CodeRange, CodeRangeMetrics>();

	public MetricsEvaluator(ProjectAnalyser analyser) {
		super(analyser);
		this.analyser = analyser;
	}

	@Override
	public void run() {
		getAllCodeRanges();
		calculateMetrics();
	}

	/**
	 * This method reads all code ranges and adds them to the class's code range
	 * ArrayList due to the calcuation of metrics for all code ranges.
	 */
	private void getAllCodeRanges() {
		for (File file : analyser.getFiles()) {
			addFile(file);
			for (CodeRange codeRange : analyser.getNamedCodeRanges(file)) {
				addCodeRange(codeRange);
			}
		}
	}

	private void calculateMetrics() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setRange(0, getFiles().size());
			observer.setStatus(0);
			observer.setDescription(NAME);
			observer.showProgressPercent();
		}
		int index = 0;
		for (File file : getFiles()) {
			index++;
			if (observer != null) {
				observer.setStatus(index);
			}
			for (CodeRange codeRange : getCodeRanges(file)) {
				if (Thread.interrupted()) {
					return;
				}
				if (observer != null) {
					observer.setStatus(index);
				}
				calculateMetric(codeRange);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private void calculateMetric(CodeRange codeRange) {
		metrics.put(codeRange, new CodeRangeMetrics(codeRange));
	}

	public CodeRangeMetrics getMetrics(CodeRange codeRange) {
		return metrics.get(codeRange);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		} else if (format == ReportingFormat.TEXT) {
			return DESCRIPTION;
		}
		throw new UnsupportedReportingFormatException(format);
	}

	@Override
	public String getProjectComment(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextProjectComment();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLProjectComment();
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getTextProjectComment() {
		return translator.i18n("The overall project quality is: ")
				+ getProjectQuality().getIdentifier();
	}

	private String getHTMLProjectComment() {
		String text = "<p>"
				+ translator.i18n("The overall project quality is: ")
				+ HTMLConverter.convertQualityLevelToHTML(getProjectQuality())
				+ "</p>";
		text += "<table>\n";
		text += "<tr>";
		text += "<th>" + translator.i18n("File") + "</th>";
		text += "<th>" + translator.i18n("Quality Level") + "</th>";
		text += "</tr>\n";
		List<File> files = getFiles();
		Collections.sort(files);
		for (File file : files) {
			text += "<tr>";
			text += "<td>" + file + "</td><td>"
					+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
					+ "</td>";
			text += "</tr>\n";
		}
		text += "</table>\n";
		return text;
	}

	@Override
	public String getFileComment(File file, ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextFileComment(file);
		} else if (format == ReportingFormat.HTML) {
			return getHTMLFileComment(file);
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getTextFileComment(File file) {
		return translator.i18n("The overall file quality is: ")
				+ getQuality(file).getIdentifier();
	}

	private String getHTMLFileComment(File file) {
		String output = "<p>"
				+ translator.i18n("The overall file quality is: ")
				+ HTMLConverter.convertQualityLevelToHTML(getQuality(file))
				+ "</p>";
		ArrayList<CodeRange> codeRanges = getCodeRanges(file);
		Collections.sort(codeRanges);
		for (CodeRange codeRange : codeRanges) {
			output += "<hr/>";
			try {
				output += "<h3>"
						+ codeRange.getTitleString(ReportingFormat.HTML)
						+ "</h3>";
			} catch (UnsupportedReportingFormatException e) {
				output += "<h3>" + codeRange.getName() + "</h3>";
			}
			output += new HTMLMetricsReport(getMetrics(codeRange))
					.getReportText();
		}
		return output;
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) {
		return getQuality(codeRange).getIdentifier();
	}

	@Override
	public QualityLevel getProjectQuality() {
		int qualityLevel = 0;
		int count = 0;
		for (File file : getFiles()) {
			QualityLevel fileQuality = getQuality(file);
			if (fileQuality != QualityLevel.UNSPECIFIED) {
				qualityLevel += fileQuality.getLevel();
				count++;
			}
		}
		double avgQuality = (double) qualityLevel / (double) count;
		qualityLevel = (int) Math.round(avgQuality);
		return QualityLevel.fromLevel(qualityLevel);
	}

	@Override
	public QualityLevel getQuality(File file) {
		QualityLevel level = QualityLevel.HIGH;
		for (CodeRange range : getCodeRanges(file)) {
			QualityLevel rangeQualityLevel = getQuality(range);
			level = QualityLevel.getMinLevel(level, rangeQualityLevel);
		}
		return level;
	}

	@Override
	public QualityLevel getQuality(CodeRange codeRange) {
		CodeRangeMetrics codeRangeMetrics = metrics.get(codeRange);
		if (codeRangeMetrics == null) {
			return QualityLevel.UNSPECIFIED;
		}
		return codeRangeMetrics.getQualityLevel();
	}
}
