package com.puresoltechnologies.purifinity.server.core.api.evaluation.defects;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractDefectEvaluator extends AbstractEvaluator {

    @Inject
    private Logger logger;

    public AbstractDefectEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.DEFECTS, description);
    }
}
