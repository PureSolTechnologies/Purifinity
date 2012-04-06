package com.puresol.coding.client.content;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.Analysis;

public enum AnalysisNavigatorModel {

    INSTANCE;

    private final List<Analysis> analyzers = new ArrayList<Analysis>();

    public void addAnalysis(Analysis analyzer) {
	analyzers.add(analyzer);
    }

    public void removeAnalysis(Analysis analyzer) {
	analyzers.remove(analyzer);
    }

    public List<Analysis> getAnalyses() {
	return analyzers;
    }

}
