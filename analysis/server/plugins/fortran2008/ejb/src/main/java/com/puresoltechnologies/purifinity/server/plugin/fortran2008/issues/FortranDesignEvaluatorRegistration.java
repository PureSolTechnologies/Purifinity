package com.puresoltechnologies.purifinity.server.plugin.fortran2008.issues;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.FortranPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class FortranDesignEvaluatorRegistration extends AbstractEvaluatorServiceRegistration {

    public static final String JNDI_ADDRESS = JndiUtils.createGlobalName("fortran2008.plugin",
	    "com-puresoltechnologies-purifinity-plugins-fortran2008.ejb", Evaluator.class,
	    FortranDesignEvaluator.class);
    public static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
	    FortranDesignEvaluator.ID, FortranDesignEvaluator.NAME, EvaluatorType.DEFECTS,
	    FortranDesignEvaluator.PLUGIN_VERSION, JNDI_ADDRESS, FortranDesignEvaluator.DESCRIPTION,
	    FortranDesignEvaluator.CONFIGURATION_PARAMETERS, "/fortran2008.ui/index.jsf", "/fortran2008.ui/project.jsf",
	    "/fortran2008.ui/run.jsf", FortranDesignEvaluator.CHARACTERISTICS, FortranDesignEvaluator.PARAMETERS,
	    FortranDesignEvaluator.DEPENDENCIES);

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
	return FortranDesignEvaluator.NAME;
    }

    @Override
    @Lock(LockType.READ)
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
