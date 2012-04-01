package com.puresol.coding.metrics.maintainability;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class ProjectMaintainabilityIndex extends ProjectEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Logger logger = LoggerFactory
	    .getLogger(ProjectMaintainabilityIndex.class);

    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private final Map<String, List<Result>> evaluatorResults = new HashMap<String, List<Result>>();

    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;
    private int qualitySum = 0;
    private int qualityCount = 0;
    private final ProjectAnalyzer projectAnalyzer;

    public ProjectMaintainabilityIndex() {
	super("");
	this.projectAnalyzer = null;
    }

    public ProjectMaintainabilityIndex(ProjectAnalyzer projectAnalyzer) {
	super(projectAnalyzer.getName());
	this.projectAnalyzer = projectAnalyzer;
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    qualities.clear();
	    qualitySum = 0;
	    qualityCount = 0;
	    List<AnalyzedFile> files = projectAnalyzer.getAnalyzedFiles();
	    monitor.beginTask(getName(), files.size());
	    int count = 0;
	    Collections.sort(files);
	    for (AnalyzedFile file : files) {
		if (monitor.isCanceled()) {
		    monitor.done();
		    return Status.CANCEL_STATUS;
		}
		count++;
		monitor.worked(count);
		processFile(file);
	    }
	    int result = (int) Math.round((double) qualitySum
		    / (double) qualityCount);
	    projectQuality = SourceCodeQuality.fromLevel(result);
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (IOException e) {
	    logger.error("Could not calculate maintainability index!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	}
    }

    private void processFile(AnalyzedFile file) throws IOException {
	Analysis analysis = getProjectAnalyzer().getAnalysis(file);
	ProgrammingLanguage language = analysis.getLanguage();

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    MaintainabilityIndex metric = new MaintainabilityIndex(language,
		    codeRange);
	    metric.schedule();
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
