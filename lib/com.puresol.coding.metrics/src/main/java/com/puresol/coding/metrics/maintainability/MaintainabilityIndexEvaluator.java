package com.puresol.coding.metrics.maintainability;

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
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.Result;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class MaintainabilityIndexEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Logger logger = LoggerFactory
	    .getLogger(MaintainabilityIndexEvaluator.class);

    private final MaintainabilityIndexFileResult qualities = new MaintainabilityIndexFileResult();
    private final Map<String, List<Result>> evaluatorResults = new HashMap<String, List<Result>>();
    private final FileStore fileStore = FileStoreFactory.getInstance();

    public MaintainabilityIndexEvaluator(AnalysisRun analysisRun) {
	super(MaintainabilityIndex.NAME, MaintainabilityIndex.DESCRIPTION,
		analysisRun);
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    qualities.clear();
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
		FileAnalysis analysis = fileStore
			.loadAnalysis(file.getHashId());
		processFile(analysis);
	    }
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (FileStoreException e) {
	    logger.error("Could not calculate maintainability index!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	} catch (InterruptedException e) {
	    logger.error("Maintainability index evaluation was interrupted!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	}
    }

    @Override
    protected void processFile(FileAnalysis analysis)
	    throws InterruptedException {
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    MaintainabilityIndex metric = new MaintainabilityIndex(
		    getAnalysisRun(), language, codeRange);
	    metric.schedule();
	    metric.join();
	    String identifier = analysis.getAnalyzedFile().getFile().getPath()
		    + ": " + codeRange.getType().getName() + " '"
		    + codeRange.getName() + "'";
	    qualities.put(identifier, metric.getQuality());
	    evaluatorResults.put(identifier, metric.getResults());
	}
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return MaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	// TODO Auto-generated method stub

    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub

    }
}