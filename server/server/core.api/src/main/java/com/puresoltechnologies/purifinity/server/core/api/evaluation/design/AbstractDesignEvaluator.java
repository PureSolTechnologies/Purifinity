package com.puresoltechnologies.purifinity.server.core.api.evaluation.design;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.defects.DefectEvaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericDirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericFileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.ProjectDesignIssues;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractDesignEvaluator extends
	AbstractEvaluator<FileDesignIssues, GenericFileDesignIssues, DirectoryDesignIssues, GenericDirectoryDesignIssues, ProjectDesignIssues, GenericProjectDesignIssues>
	implements DefectEvaluator {

    public AbstractDesignEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.DESGIN, description);
    }
}
