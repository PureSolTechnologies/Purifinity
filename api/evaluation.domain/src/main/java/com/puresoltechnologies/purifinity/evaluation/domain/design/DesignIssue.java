package com.puresoltechnologies.purifinity.evaluation.domain.design;

import com.puresoltechnologies.commons.domain.GeneralValue;

public class DesignIssue extends GeneralValue<Integer> {

    private static final long serialVersionUID = 3991682986335349586L;

    public DesignIssue(Integer weight, DesignIssueParameter parameter) {
	super(weight, parameter);
    }

}
