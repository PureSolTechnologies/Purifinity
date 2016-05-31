package com.puresoltechnologies.purifinity.server.core.api.evaluation.architecture;

import com.puresoltechnologies.purifinity.evaluation.domain.architecture.DirectoryArchitectureIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.architecture.FileArchitectureIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.architecture.ProjectArchitectureIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.architecture.RunArchitectureIssues;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorStore;

/**
 * This is a store for a single evaluator. This stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorArchitectureIssuesStore extends
	EvaluatorStore<FileArchitectureIssues, DirectoryArchitectureIssues, ProjectArchitectureIssues, RunArchitectureIssues> {

}
