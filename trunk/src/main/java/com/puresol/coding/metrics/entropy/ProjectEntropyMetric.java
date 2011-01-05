package com.puresol.coding.metrics.entropy;

import java.io.File;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;

public class ProjectEntropyMetric extends AbstractProjectMetric<EntropyMetric> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectEntropyMetric(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected EntropyMetric processFile(File file) {
		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		EntropyMetric metric = new EntropyMetric(analyzer.getLanguage(),
				new CodeRange(file.getPath(), CodeRangeType.FILE,
						analyzer.getParserTree()));
		metric.run();
		return metric;
	}

	@Override
	public String getName() {
		return EntropyMetric.NAME;
	}

	@Override
	public String getDescription() {
		return EntropyMetric.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
