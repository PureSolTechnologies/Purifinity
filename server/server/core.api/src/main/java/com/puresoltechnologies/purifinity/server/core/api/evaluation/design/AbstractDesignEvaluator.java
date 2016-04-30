package com.puresoltechnologies.purifinity.server.core.api.evaluation.design;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.defects.DefectEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractDesignEvaluator extends AbstractEvaluator implements DefectEvaluator {

    public AbstractDesignEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.DESGIN, description);
    }
}
