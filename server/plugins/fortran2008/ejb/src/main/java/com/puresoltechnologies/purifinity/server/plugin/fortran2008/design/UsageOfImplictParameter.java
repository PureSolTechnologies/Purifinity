package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;

/**
 * This parameter is used when an implict usage is found.
 * 
 * @author Rick-Rainer Ludwig
 */
public class UsageOfImplictParameter extends DesignIssueParameter {

    private static final long serialVersionUID = -1112823899835907399L;

    public UsageOfImplictParameter() {
	super("UsageOfImplicit", "", "The usage of 'IMPLICIT' statement should be avoided.");
    }

}
