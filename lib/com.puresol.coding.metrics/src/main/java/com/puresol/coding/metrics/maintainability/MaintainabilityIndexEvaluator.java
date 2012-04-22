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

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluation.api.Result;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class MaintainabilityIndexEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Logger logger = LoggerFactory
	    .getLogger(MaintainabilityIndexEvaluator.class);

    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private final Map<String, List<Result>> evaluatorResults = new HashMap<String, List<Result>>();
    private final FileStore fileStore = FileStoreFactory.getInstance();

    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;
    private int qualitySum = 0;
    private int qualityCount = 0;

    public MaintainabilityIndexEvaluator(AnalysisRun analysisRun) {
	super(new EvaluatorInformation(MaintainabilityIndex.NAME,
		MaintainabilityIndex.DESCRIPTION), analysisRun);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    qualities.clear();
	    qualitySum = 0;
	    qualityCount = 0;
	    List<AnalyzedFile> files = getAnalysisRun().getAnalyzedFiles();
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
	} catch (FileStoreException e) {
	    logger.error("Could not calculate maintainability index!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	}
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(AnalyzedFile file)
	    throws IOException, FileStoreException {
	FileAnalysis analysis = fileStore.loadAnalysis(file.getHashId());
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    MaintainabilityIndex metric = new MaintainabilityIndex(
		    getAnalysisRun(), language, codeRange);
	    metric.schedule();
	    String identifier = file.getFile().getPath() + ": "
		    + codeRange.getType().getName() + " '"
		    + codeRange.getName() + "'";
	    qualities.put(identifier, metric.getQuality());
	    evaluatorResults.put(identifier, metric.getResults());
	    addProjectQualityPart(metric.getQuality());
	}
	return qualities;
    }

    private void addProjectQualityPart(SourceCodeQuality level) {
	if (level != SourceCodeQuality.UNSPECIFIED) {
	    qualitySum += level.getLevel();
	    qualityCount++;
	}
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
}
