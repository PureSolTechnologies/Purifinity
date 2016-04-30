package com.puresoltechnologies.purifinity.server.core.api.evaluation.style;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.style.StyleIssueEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractStyleEvaluator extends AbstractEvaluator implements StyleIssueEvaluator {

    public AbstractStyleEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.STYLE, description);
    }

}
