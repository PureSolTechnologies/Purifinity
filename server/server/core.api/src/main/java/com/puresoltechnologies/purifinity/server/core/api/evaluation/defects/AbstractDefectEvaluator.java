package com.puresoltechnologies.purifinity.server.core.api.evaluation.defects;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractDefectEvaluator extends AbstractEvaluator {

    public AbstractDefectEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.DEFECTS, description);
    }
}
