package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;

/**
 * This parameter is used when an implict usage is found.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CombinedUsageOfImplictParameter extends DesignIssueParameter {

    private static final long serialVersionUID = -1112823899835907399L;

    public static final String NAME = "CombinedUsageOfImplicit";

    public CombinedUsageOfImplictParameter() {
	super(NAME, "", "The combined usage of 'IMPLICIT NONE' and 'IMPLICIT' statements are used.");
    }

}
