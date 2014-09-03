package com.puresoltechnologies.purifinity.server.metrics;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexServiceRegistration;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCServiceRegistration;

@Singleton
@Startup
public class PluginRegistration {

	@Inject
	private BasicCoCoMoServiceRegistration basicCoCoMoServiceRegistration;

	@Inject
	private IntermediateCoCoMoServiceRegistration intermediateCoCoMoServiceRegistration;

	@Inject
	private CodeDepthServiceRegistration codeDepthServiceRegistration;

	@Inject
	private EntropyServiceRegistration entropyServiceRegistration;

	@Inject
	private HalsteadServiceRegistration halsteadServiceRegistration;

	@Inject
	private MaintainabilityIndexServiceRegistration maintainabilityIndexServiceRegistration;

	@Inject
	private McCabeServiceRegistration mcCabeServiceRegistration;

	@Inject
	private NormalizedMaintainabilityIndexServiceRegistration normalizedMaintainabilityIndexServiceRegistration;

	@Inject
	private SLOCServiceRegistration slocServiceRegistration;

	@PostConstruct
	public void registration() {
		basicCoCoMoServiceRegistration.registration();
		intermediateCoCoMoServiceRegistration.registration();
		codeDepthServiceRegistration.registration();
		entropyServiceRegistration.registration();
		halsteadServiceRegistration.registration();
		maintainabilityIndexServiceRegistration.registration();
		mcCabeServiceRegistration.registration();
		normalizedMaintainabilityIndexServiceRegistration.registration();
		slocServiceRegistration.registration();
	}

	@PreDestroy
	public void unregistration() {
		basicCoCoMoServiceRegistration.unregistration();
		intermediateCoCoMoServiceRegistration.unregistration();
		codeDepthServiceRegistration.unregistration();
		entropyServiceRegistration.unregistration();
		halsteadServiceRegistration.unregistration();
		maintainabilityIndexServiceRegistration.unregistration();
		mcCabeServiceRegistration.unregistration();
		normalizedMaintainabilityIndexServiceRegistration.unregistration();
		slocServiceRegistration.unregistration();
	}

}
