package com.puresol.coding.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;

public abstract class AbstractProjectMetric<T extends CodeRangeEvaluator>
		extends AbstractEvaluator implements ProjectEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final Map<File, T> fileResults = new HashMap<File, T>();
	private final ProjectAnalyzer projectAnalyzer;

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
		List<File> files = projectAnalyzer.getFiles();
		if (getMonitor() != null) {
			getMonitor().setRange(0, files.size());
			getMonitor().setDescription(getName());
		}
		int count = 0;
		for (File file : files) {
			if (Thread.interrupted()) {
				return;
			}
			if (getMonitor() != null) {
				count++;
				getMonitor().setStatus(count);
			}
			fileResults.put(file, processFile(file));
		}
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	abstract protected T processFile(File file);

	@Override
	public SourceCodeQuality getQuality() {
		int sum = 0;
		int count = 0;
		for (File file : fileResults.keySet()) {
			T metric = fileResults.get(file);
			SourceCodeQuality level = metric.getQuality();
			if (level != SourceCodeQuality.UNSPECIFIED) {
				sum += level.getLevel();
				count++;
			}
		}
		int result = (int) Math.round((double) sum / (double) count);
		return SourceCodeQuality.fromLevel(result);
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><body>");
		buffer.append("<p>Overall Quality: "
				+ HTMLConverter.convertQualityLevelToHTML(getQuality())
				+ "</p>");
		buffer.append("<table>");
		List<File> files = new ArrayList<File>(fileResults.keySet());
		Collections.sort(files);
		for (File file : files) {
			SourceCodeQuality level = fileResults.get(file).getQuality();
			buffer.append("<tr>");
			buffer.append("<td>" + file.getPath() + "</td><td>"
					+ HTMLConverter.convertQualityLevelToHTML(level) + "</td>");
			buffer.append("</tr>");
		}
		buffer.append("</table>");
		buffer.append("</body></html>");
		return buffer.toString();
	}
}
