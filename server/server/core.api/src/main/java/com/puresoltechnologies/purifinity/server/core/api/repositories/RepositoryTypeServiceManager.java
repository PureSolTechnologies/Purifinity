package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;

@Local
public interface RepositoryTypeServiceManager extends
		ServiceManager<RepositoryTypeServiceInformation> {

}
