package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.api.AnalysisRun;

public abstract class ProjectEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -4543626577984599766L;

    public ProjectEvaluator(String name) {
	super(name);
    }

    public abstract AnalysisRun getProjectAnalyzer();

}