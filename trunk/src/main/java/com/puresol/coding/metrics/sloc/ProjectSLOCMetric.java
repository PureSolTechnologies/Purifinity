package com.puresol.coding.metrics.sloc;

import java.io.File;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.EvaluatorOutput;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;

public class ProjectSLOCMetric extends AbstractProjectMetric<SLOCMetric> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectSLOCMetric(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected SLOCMetric processFile(File file) {
		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		SLOCMetric metric = new SLOCMetric(analyzer.getLanguage(),
				new CodeRange(file.getPath(), CodeRangeType.FILE,
						analyzer.getParserTree()));
		metric.run();
		return metric;
	}

	@Override
	public String getName() {
		return SLOCMetric.NAME;
	}

	@Override
	public String getDescription() {
		return SLOCMetric.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<EvaluatorOutput> getTextOutput() {
		return null;
	}
}
