package com.puresoltechnologies.purifinity.evaluation.domain.style;

import com.puresoltechnologies.commons.domain.AbstractValue;
import com.puresoltechnologies.commons.domain.Parameter;

public class StyleIssue<T> extends AbstractValue<T> {

    private static final long serialVersionUID = -264392532290776208L;

    public StyleIssue(T value, Parameter<T> parameter) {
	super(value, parameter);
    }

    @Override
    public Parameter<T> getParameter() {
	return super.getParameter();
    }

}
