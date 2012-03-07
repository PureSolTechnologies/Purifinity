package com.puresol.coding.metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.gui.Application;

public abstract class AbstractProjectMetric<T extends CodeRangeEvaluator>
	extends ProjectEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private final ProjectAnalyzer projectAnalyzer;
    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;

    public AbstractProjectMetric(ProjectAnalyzer projectAnalyzer) {
	super(projectAnalyzer.getName());
	this.projectAnalyzer = projectAnalyzer;
    }

    @Override
    public ProjectAnalyzer getProjectAnalyzer() {
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
	    JOptionPane.showMessageDialog(Application.getInstance(),
		    "IOException was thrown!", "Error",
		    JOptionPane.ERROR_MESSAGE);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
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
}
