package com.puresoltechnologies.purifinity.server.core.impl.analysis.plugins;

import javax.ejb.Singleton;
import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class AnalyzerPluginServiceImpl extends
	AbstractPluginService<AnalyzerInformation> implements
	AnalyzerPluginService, AnalyzerPluginServiceRemote {

    public AnalyzerPluginServiceImpl() {
	super("Analyzer Plugin Service");
    }

    @Override
    public ProgrammingLanguageAnalyzer createInstance(String jndi)
	    throws NamingException {
	return JndiUtils.createRemoteEJBInstance(
		ProgrammingLanguageAnalyzer.class, jndi);
    }

}
