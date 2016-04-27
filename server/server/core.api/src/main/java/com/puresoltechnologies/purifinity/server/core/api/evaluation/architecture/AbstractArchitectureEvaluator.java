package com.puresoltechnologies.purifinity.server.core.api.evaluation.architecture;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.architecture.ArchitectureEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractArchitectureEvaluator extends AbstractEvaluator implements ArchitectureEvaluator {

    @Inject
    private Logger logger;

    public AbstractArchitectureEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.ARCHITECTURE, description);
    }
}
