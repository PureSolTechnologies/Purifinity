package com.puresol.coding.metrics.maintainability;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swing.JOptionPane;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.gui.Application;

public class ProjectMaintainabilityIndex extends AbstractEvaluator implements
	ProjectEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Translator translator = Translator
	    .getTranslator(ProjectMaintainabilityIndex.class);

    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private final Map<String, List<Result>> evaluatorResults = new HashMap<String, List<Result>>();

    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;
    private int qualitySum = 0;
    private int qualityCount = 0;
    private final ProjectAnalyzer projectAnalyzer;

    public ProjectMaintainabilityIndex(ProjectAnalyzer projectAnalyzer) {
	super();
	this.projectAnalyzer = projectAnalyzer;
    }

    @Override
    public void run() {
	try {
	    qualities.clear();
	    qualitySum = 0;
	    qualityCount = 0;
	    List<AnalyzedFile> files = projectAnalyzer.getAnalyzedFiles();
	    if (getMonitor() != null) {
		getMonitor().setRange(0, files.size());
		getMonitor().setTitle(getName());
		getMonitor().showProgressPercent();
	    }
	    int count = 0;
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
		processFile(file);
	    }
	    int result = (int) Math.round((double) qualitySum
		    / (double) qualityCount);
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

    private void processFile(AnalyzedFile file) throws IOException {
	Analysis analysis = getProjectAnalyzer().getAnalysis(file);
	ProgrammingLanguage language = analysis.getLanguage();

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    MaintainabilityIndex metric = new MaintainabilityIndex(language,
		    codeRange);
	    metric.run();
	    String identifier = file.getFile().getPath() + ": "
		    + codeRange.getType().getName() + " '"
		    + codeRange.getName() + "'";
	    qualities.put(identifier, metric.getQuality());
	    evaluatorResults.put(identifier, metric.getResults());
	    addProjectQualityPart(metric.getQuality());
	}
    }

    private void addProjectQualityPart(SourceCodeQuality level) {
	if (level != SourceCodeQuality.UNSPECIFIED) {
	    qualitySum += level.getLevel();
	    qualityCount++;
	}
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

    @Override
    public List<Result> getResults() {
	return new ArrayList<Result>();
    }

    @Override
    public SourceCodeQuality getQuality() {
	return projectQuality;
    }

    @Override
    public ProjectAnalyzer getProjectAnalyzer() {
	return projectAnalyzer;
    }
}
