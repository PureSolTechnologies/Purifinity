package com.puresoltechnologies.purifinity.server.plugin.java7;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class JavaPluginRegistration extends AbstractPluginRegistration
	implements AnalyzerRemotePlugin {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "java7.plugin", "java7.ejb", ProgrammingLanguageAnalyzer.class,
	    Java.class);
    private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
	    Java.NAME, Java.VERSION, Java.PLUGIN_VERSION, JNDI_ADDRESS,
	    "This is a Java 7 programming language analyzer.");

    private final Java java = new Java();

    @PostConstruct
    public void registraion() {
	register(AnalyzerPluginServiceRemote.class,
		AnalyzerPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS,
		INFORMATION);
    }

    @PreDestroy
    public void unregistration() {
	unregister(AnalyzerPluginServiceRemote.class,
		AnalyzerPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS);
    }

    @Override
    public String getName() {
	return java.getName();
    }
}
