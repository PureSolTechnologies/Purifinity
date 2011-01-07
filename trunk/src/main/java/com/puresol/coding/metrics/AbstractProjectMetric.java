package com.puresol.coding.metrics;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.SourceCodeQuality;

public abstract class AbstractProjectMetric<T extends CodeRangeEvaluator>
		extends AbstractEvaluator implements ProjectEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final ProjectAnalyzer projectAnalyzer;
	private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
	private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;

	public AbstractProjectMetric(ProjectAnalyzer projectAnalyzer) {
		super();
		this.projectAnalyzer = projectAnalyzer;
	}

	@Override
	public ProjectAnalyzer getProjectAnalyzer() {
		return projectAnalyzer;
	}

	@Override
	public void run() {
		qualities.clear();
		List<File> files = projectAnalyzer.getFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(getName());
		}
		int sum = 0;
		int count = 0;
		int qualCount = 0;
		Collections.sort(files);
		for (File file : files) {
			if (Thread.interrupted()) {
				return;
			}
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			T t = processFile(file);
			SourceCodeQuality level = t.getQuality();
			if (level != SourceCodeQuality.UNSPECIFIED) {
				sum += level.getLevel();
				qualCount++;
			}
			qualities.put(file.getPath(), level);
		}
		int result = (int) Math.round((double) sum / (double) qualCount);
		projectQuality = SourceCodeQuality.fromLevel(result);
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	abstract protected T processFile(File file);

	@Override
	public SourceCodeQuality getQuality() {
		return projectQuality;
	}

	@Override
	public List<Result> getResults() {
		return null;
	}

	@Override
	public Map<String, SourceCodeQuality> getPartQualities() {
		return qualities;
	}
}
