package com.puresol.coding.metrics.normmaint;

import java.io.File;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;

public class ProjectNormalizedMaintainabilityIndex extends
		AbstractProjectMetric<NormalizedMaintainabilityIndex> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectNormalizedMaintainabilityIndex(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected NormalizedMaintainabilityIndex processFile(File file) {
		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		NormalizedMaintainabilityIndex metric = new NormalizedMaintainabilityIndex(
				analyzer.getLanguage(), new CodeRange(file.getPath(),
						CodeRangeType.FILE, analyzer.getParserTree()));
		metric.run();
		return metric;
	}

	@Override
	public String getName() {
		return NormalizedMaintainabilityIndex.NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return NormalizedMaintainabilityIndex.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
