package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AbstractAnalyzerServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class FortranServiceRegistration extends
	AbstractAnalyzerServiceRegistration {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "fortran2008.plugin", "fortran2008.ejb",
	    ProgrammingLanguageAnalyzer.class, Fortran.class);
    private static final AnalyzerServiceInformation INFORMATION = new AnalyzerServiceInformation(
	    Fortran.ID, Fortran.NAME, Fortran.VERSION, Fortran.PLUGIN_VERSION,
	    JNDI_ADDRESS,
	    "This is a Fortran 2008 programming language analyzer.",
	    Fortran.PARAMETERS, "/fortran2008.ui/index.jsf",
	    "/fortran2008.ui/config.jsf", "/fortran2008.ui/project.jsf",
	    "/fortran2008.ui/run.jsf");

    @PostConstruct
    public void registration() {
	register(AnalyzerServiceManagerRemote.class,
		AnalyzerServiceManagerRemote.JNDI_NAME,
		FortranPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
    }

    @PreDestroy
    public void unregistration() {
	unregister(AnalyzerServiceManagerRemote.class,
		AnalyzerServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
    }

    @Override
    public String getName() {
	return Fortran.NAME;
    }

    @Override
    public AnalyzerServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
