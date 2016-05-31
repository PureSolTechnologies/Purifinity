package com.puresoltechnologies.purifinity.server.core.api.evaluation.defects;

import com.puresoltechnologies.purifinity.evaluation.domain.defects.DirectoryDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.FileDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.ProjectDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.RunDefects;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorStore;

/**
 * This is a store for a single evaluator. THis stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorDefectsStore
	extends EvaluatorStore<FileDefects, DirectoryDefects, ProjectDefects, RunDefects> {

}
