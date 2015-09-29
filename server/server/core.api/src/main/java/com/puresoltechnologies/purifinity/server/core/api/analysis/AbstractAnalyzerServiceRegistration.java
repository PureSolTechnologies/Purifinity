package com.puresoltechnologies.purifinity.server.core.api.analysis;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

public abstract class AbstractAnalyzerServiceRegistration
	extends AbstractServiceRegistration<AnalyzerServiceInformation>implements AnalyzerRemotePlugin {

    @Override
    protected void registerInDatabase() {
    }

    @Override
    protected void unregisterInDatabase() {
    }

}
