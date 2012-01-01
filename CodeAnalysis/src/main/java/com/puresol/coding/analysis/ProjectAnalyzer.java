package com.puresol.coding.analysis;

import java.io.File;
import java.util.List;

import com.puresol.document.Document;
import com.puresol.gui.progress.RunnableProgressObservable;

public interface ProjectAnalyzer extends RunnableProgressObservable {

    public File getWorkspaceDirectory();

    public File getProjectDirectory();

    public List<AnalyzedFile> getAnalyzedFiles();

    public List<File> getFailedFiles();

    public Analysis getAnalysis(AnalyzedFile file);

    public Document getReport();

    public AnalyzedFile findAnalyzedFile(File file);

    public AnalyzedFile findAnalyzedFileBySourceFile(File sourceFile);

}