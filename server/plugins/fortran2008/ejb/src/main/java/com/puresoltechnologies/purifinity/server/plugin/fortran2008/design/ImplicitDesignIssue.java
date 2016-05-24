package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssue;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;

public class ImplicitDesignIssue extends DesignIssue {

    private static final long serialVersionUID = 1334436803463946234L;

    public ImplicitDesignIssue(DesignIssueParameter parameter) {
	super(1, parameter);
    }

}
