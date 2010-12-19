package com.puresol.coding.metrics.halstead;

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

public class ProjectHalsteadMetric extends
		AbstractProjectMetric<HalsteadMetric> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectHalsteadMetric(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected HalsteadMetric processFile(File file) {
		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		HalsteadMetric metric = new HalsteadMetric(analyzer.getLanguage(),
				new CodeRange(file.getPath(), CodeRangeType.FILE,
						analyzer.getParserTree()));
		metric.run();
		return metric;
	}

	@Override
	public String getName() {
		return HalsteadMetric.NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return HalsteadMetric.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
