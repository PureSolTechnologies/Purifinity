package com.puresol.coding.client.content;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.ProjectAnalyzer;

public enum AnalysisNavigatorModel {

    INSTANCE;

    private final List<ProjectAnalyzer> analyzers = new ArrayList<ProjectAnalyzer>();

    public void addAnalysis(ProjectAnalyzer analyzer) {
	analyzers.add(analyzer);
    }

    public void removeAnalysis(ProjectAnalyzer analyzer) {
	analyzers.remove(analyzer);
    }

    public List<ProjectAnalyzer> getAnalyzers() {
	return analyzers;
    }

}
