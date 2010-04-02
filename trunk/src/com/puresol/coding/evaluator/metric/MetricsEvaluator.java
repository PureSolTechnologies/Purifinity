package com.puresol.coding.evaluator.metric;

import java.io.File;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.utils.Property;

public class MetricsEvaluator extends AbstractEvaluator {

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
						String.class, "true"));
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
			for (CodeRange codeRange : analyser.getCodeRanges(file)) {
				addCodeRange(codeRange);
			}
		}
	}

	private void calculateMetrics() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setRange(0, getFiles().size());
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
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getProjectComment() {
		return getProjectQuality().getIdentifier();
	}

	@Override
	public String getFileComment(File file) {
		return getQuality(file).getIdentifier();
	}

	@Override
	public String getCodeRangeComment(CodeRange codeRange) {
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
		return metrics.get(codeRange).getQualityLevel();
	}
}
