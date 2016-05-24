package com.puresoltechnologies.purifinity.server.core.api.evaluation.style;

import com.puresoltechnologies.purifinity.evaluation.domain.style.DirectoryStyleIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.style.FileStyleIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.style.ProjectStyleIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.style.RunStyleIssues;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorStore;

/**
 * This is a store for a single evaluator. This stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorStyleIssuesStore
	extends EvaluatorStore<FileStyleIssues, DirectoryStyleIssues, ProjectStyleIssues, RunStyleIssues> {

}
