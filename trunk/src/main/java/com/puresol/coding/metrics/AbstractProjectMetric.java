package com.puresol.coding.metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swing.JOptionPane;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.document.Chapter;
import com.puresol.document.Document;
import com.puresol.document.Paragraph;
import com.puresol.document.Table;
import com.puresol.gui.Application;

public abstract class AbstractProjectMetric<T extends CodeRangeEvaluator>
		extends AbstractEvaluator implements ProjectEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Translator translator = Translator
			.getTranslator(AbstractProjectMetric.class);

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
		try {
			qualities.clear();
			List<AnalyzedFile> files = projectAnalyzer.getAnalyzedFiles();
			if (getMonitor() != null) {
				getMonitor().setRange(0, files.size());
				getMonitor().setTitle(getName());
				getMonitor().showProgressPercent();
			}
			int sum = 0;
			int count = 0;
			int qualCount = 0;
			Collections.sort(files);
			for (AnalyzedFile file : files) {
				if (Thread.interrupted()) {
					return;
				}
				if (getMonitor() != null) {
					count++;
					getMonitor().setStatus(count);
					getMonitor().setText(file.getFile().getPath());
				}
				Map<String, SourceCodeQuality> levels;
				levels = processFile(file);
				qualities.putAll(levels);
				for (SourceCodeQuality level : levels.values()) {
					if (level != SourceCodeQuality.UNSPECIFIED) {
						sum += level.getLevel();
						qualCount++;
					}
				}

			}
			int result = (int) Math.round((double) sum / (double) qualCount);
			projectQuality = SourceCodeQuality.fromLevel(result);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Application.getInstance(),
					translator.i18n("IOException was thrown!"),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		} finally {
			if (getMonitor() != null) {
				getMonitor().finished(this);
			}
		}
	}

	abstract protected Map<String, SourceCodeQuality> processFile(
			AnalyzedFile file) throws IOException;

	@Override
	public SourceCodeQuality getQuality() {
		return projectQuality;
	}

	@Override
	public List<Result> getResults() {
		return new ArrayList<Result>();
	}

	@Override
	public Document getReport() {
		Document document = new Document(getName());
		Chapter descriptionChapter = new Chapter(document,
				translator.i18n("Description"));
		for (String paragraph : getDescription().split("\\n")) {
			new Paragraph(descriptionChapter, paragraph);
		}
		if (getResults() != null) {
			Chapter resultsSummaryChapter = new Chapter(document,
					translator.i18n("Results Summary"));
			Table resultsTable = new Table(resultsSummaryChapter,
					"Results Table", translator.i18n("Symbol"),
					translator.i18n("Value"), translator.i18n("Unit"),
					translator.i18n("Description"));
			for (Result result : getResults()) {
				resultsTable.addRow(result.getName(), result.getValue(),
						result.getUnit(), result.getDescription());
			}
		}
		Chapter partQualityChapter = new Chapter(document,
				translator.i18n("Quality of Parts"));
		Table partQualityTable = new Table(partQualityChapter,
				"Table of Part Qualities", translator.i18n("Operator"),
				translator.i18n("Count"));
		for (String partName : qualities.keySet()) {
			partQualityTable.addRow(partName, qualities.get(partName));
		}
		return document;
	}
}
