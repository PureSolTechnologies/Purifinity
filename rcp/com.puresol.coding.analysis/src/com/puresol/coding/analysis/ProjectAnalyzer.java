package com.puresol.coding.analysis;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.eclipse.core.runtime.jobs.Job;

public abstract class ProjectAnalyzer extends Job implements Serializable {

    private static final long serialVersionUID = 6413809660830217670L;

    public ProjectAnalyzer(String name) {
	super(name);
    }

    public abstract File getWorkspaceDirectory();

    public abstract File getProjectDirectory();

    public abstract List<AnalyzedFile> getAnalyzedFiles();

    public abstract List<File> getFailedFiles();

    public abstract Analysis getAnalysis(AnalyzedFile file);

    public abstract AnalyzedFile findAnalyzedFile(File file);

    public abstract AnalyzedFile findAnalyzedFileBySourceFile(File sourceFile);

}