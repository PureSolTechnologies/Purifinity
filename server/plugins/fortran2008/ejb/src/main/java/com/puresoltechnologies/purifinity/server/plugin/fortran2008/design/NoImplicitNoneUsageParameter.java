package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;

/**
 * This parameter is used when no implicit none was found.
 * 
 * @author Rick-Rainer Ludwig
 */
public class NoImplicitNoneUsageParameter extends DesignIssueParameter {

    private static final long serialVersionUID = -7793464658169914903L;

    public static final String NAME = "NoImplicitNone";

    public NoImplicitNoneUsageParameter() {
	super(NAME, "", "No 'IMPLICIT NONE' was found.");
    }

}
