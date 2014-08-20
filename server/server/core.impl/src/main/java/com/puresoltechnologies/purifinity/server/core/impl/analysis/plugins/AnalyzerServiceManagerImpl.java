package com.puresoltechnologies.purifinity.server.core.impl.analysis.plugins;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class AnalyzerServiceManagerImpl extends
		AbstractServiceManager<AnalyzerServiceInformation> implements
		AnalyzerServiceManager, AnalyzerServiceManagerRemote {

	public AnalyzerServiceManagerImpl() {
		super("Analyzer Service Manager");
	}

	@Override
	public ProgrammingLanguageAnalyzer createInstance(String jndi) {
		return JndiUtils.createRemoteEJBInstance(
				ProgrammingLanguageAnalyzer.class, jndi);
	}

}
