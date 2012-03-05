package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.ProjectAnalyzer;

public abstract class ProjectEvaluator extends Evaluator {

    private static final long serialVersionUID = -4543626577984599766L;

    public ProjectEvaluator(String name) {
	super(name);
    }

    public abstract ProjectAnalyzer getProjectAnalyzer();

}
