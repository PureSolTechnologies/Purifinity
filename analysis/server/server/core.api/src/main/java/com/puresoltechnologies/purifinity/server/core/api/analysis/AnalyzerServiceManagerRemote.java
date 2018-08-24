package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

/**
 * This is the interface for the remote analyzer registration.
 */
@Remote
public interface AnalyzerServiceManagerRemote extends AnalyzerServiceManagerCommon {

    public static final String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/AnalyzerServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote";

}
