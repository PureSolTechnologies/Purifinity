package com.puresoltechnologies.purifinity.server.core.api.evaluation.design;

import com.puresoltechnologies.purifinity.evaluation.domain.design.DirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericDirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericFileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericRunDesignIssues;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorStore;

/**
 * This is a store for a single evaluator. This stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorDesignIssuesStore extends
	EvaluatorStore<FileDesignIssues, DirectoryDesignIssues, GenericFileDesignIssues, GenericDirectoryDesignIssues, GenericProjectDesignIssues, GenericRunDesignIssues> {

}
