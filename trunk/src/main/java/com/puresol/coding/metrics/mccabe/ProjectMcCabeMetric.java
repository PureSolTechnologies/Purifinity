package com.puresol.coding.metrics.mccabe;

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

public class ProjectMcCabeMetric extends AbstractProjectMetric<McCabeMetric> {

	private static final long serialVersionUID = -5093217611195212999L;

	public ProjectMcCabeMetric(ProjectAnalyzer projectAnalyzer) {
		super(projectAnalyzer);
	}

	@Override
	protected McCabeMetric processFile(File file) {
		Analyzer analyzer = getProjectAnalyzer().getAnalyzer(file);
		McCabeMetric metric = new McCabeMetric(analyzer.getLanguage(),
				new CodeRange(file.getPath(), CodeRangeType.FILE,
						analyzer.getParserTree()));
		metric.run();
		return metric;
	}

	@Override
	public String getName() {
		return McCabeMetric.NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return McCabeMetric.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
