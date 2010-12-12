package com.puresol.coding.metrics.sloc;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;

public class ProjectSLOCMetric extends AbstractEvaluator implements
		ProjectEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final ProjectAnalyzer projectAnalyzer;
	private ProjectSLOCResult projectResult;

	public ProjectSLOCMetric(ProjectAnalyzer projectAnalyzer) {
		super();
		this.projectAnalyzer = projectAnalyzer;
	}

	@Override
	public ProjectAnalyzer getProjectAnalyser() {
		return projectAnalyzer;
	}

	public ProjectSLOCResult getProjectResult() {
		return projectResult;
	}

	@Override
	public void run() {
		List<File> files = projectAnalyzer.getFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(getName());
		}
		final Map<File, SLOCResult> slocs = new HashMap<File, SLOCResult>();
		int count = 0;
		for (File file : files) {
			if (Thread.interrupted()) {
				return;
			}
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			slocs.put(file, processFile(file));
		}
		projectResult = new ProjectSLOCResult(slocs);
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	private SLOCResult processFile(File file) {
		Analyzer analyzer = projectAnalyzer.getAnalyzer(file);
		CodeRangeSLOCMetric slocMetric = new CodeRangeSLOCMetric(
				analyzer.getLanguage(), new CodeRange(file.getPath(),
						CodeRangeType.FILE, analyzer.getParserTree()));
		return slocMetric.getResult();
	}

	@Override
	public QualityLevel getQuality() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return CodeRangeSLOCMetric.NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return CodeRangeSLOCMetric.DESCRIPTION;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeRangeSLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
