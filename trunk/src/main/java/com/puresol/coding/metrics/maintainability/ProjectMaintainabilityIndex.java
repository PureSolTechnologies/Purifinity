package com.puresol.coding.metrics.maintainability;

import java.io.File;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;

public class ProjectMaintainabilityIndex extends
		AbstractProjectMetric<MaintainabilityIndex> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectMaintainabilityIndex(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected MaintainabilityIndex processFile(File file) {
		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		MaintainabilityIndex metric = new MaintainabilityIndex(
				analyzer.getLanguage(), new CodeRange(file.getPath(),
						CodeRangeType.FILE, analyzer.getParserTree()));
		metric.run();
		return metric;
	}

	@Override
	public String getName() {
		return MaintainabilityIndex.NAME;
	}

	@Override
	public String getDescription() {
		return MaintainabilityIndex.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return MaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
