package com.puresoltechnologies.purifinity.server.plugin.fortran2008.defects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.Fortran;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.FortranPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class FortranDefectEvaluatorRegistration extends AbstractEvaluatorServiceRegistration {

    public static final String JNDI_ADDRESS = JndiUtils.createGlobalName("fortran2008.plugin",
	    "com-puresoltechnologies-purifinity-plugins-fortran2008.ejb", Evaluator.class,
	    FortranDefectEvaluator.class);
    public static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
	    FortranDefectEvaluator.ID, FortranDefectEvaluator.NAME, EvaluatorType.DEFECTS,
	    FortranDefectEvaluator.PLUGIN_VERSION, JNDI_ADDRESS, FortranDefectEvaluator.DESCRIPTION,
	    FortranDefectEvaluator.CONFIGURATION_PARAMETERS, "/fortran2008.ui/index.jsf", "/fortran2008.ui/project.jsf",
	    "/fortran2008.ui/run.jsf", FortranDefectEvaluator.CHARACTERISTICS, FortranDefectEvaluator.PARAMETERS,
	    FortranDefectEvaluator.DEPENDENCIES);

    @PostConstruct
    @Lock(LockType.WRITE)
    public void registration() {
	register(EvaluatorServiceManagerRemote.class, EvaluatorServiceManagerRemote.JNDI_NAME,
		FortranPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
    }

    @PreDestroy
    @Lock(LockType.WRITE)
    public void unregistration() {
	unregister(EvaluatorServiceManagerRemote.class, EvaluatorServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
    }

    @Override
    @Lock(LockType.READ)
    public String getName() {
	return Fortran.NAME;
    }

    @Override
    @Lock(LockType.READ)
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
