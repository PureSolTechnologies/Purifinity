package com.puresol.coding.metrics;

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

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.SourceCodeQuality;

public abstract class AbstractProjectMetric<T extends CodeRangeEvaluator>
	extends ProjectEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractProjectMetric.class);

    private final AnalysisRun projectAnalyzer;
    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;

    public AbstractProjectMetric(AnalysisRun projectAnalyzer) {
	super("Project Metric");
	this.projectAnalyzer = projectAnalyzer;
    }

    @Override
    public AnalysisRun getProjectAnalyzer() {
	return projectAnalyzer;
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    qualities.clear();
	    List<AnalyzedFile> files = projectAnalyzer.getAnalyzedFiles();
	    monitor.beginTask(getName(), files.size());
	    int sum = 0;
	    int count = 0;
	    int qualCount = 0;
	    Collections.sort(files);
	    for (AnalyzedFile file : files) {
		if (monitor.isCanceled()) {
		    monitor.done();
		    return Status.CANCEL_STATUS;
		}
		count++;
		monitor.worked(count);
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
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (IOException e) {
	    logger.error("Could not calculate project metric!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	} catch (FileStoreException e) {
	    logger.error("Could not calculate project metric!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	}
    }

    abstract protected Map<String, SourceCodeQuality> processFile(
	    AnalyzedFile file) throws IOException, FileStoreException;

    @Override
    public SourceCodeQuality getQuality() {
	return projectQuality;
    }

    @Override
    public List<Result> getResults() {
	return new ArrayList<Result>();
    }
}
