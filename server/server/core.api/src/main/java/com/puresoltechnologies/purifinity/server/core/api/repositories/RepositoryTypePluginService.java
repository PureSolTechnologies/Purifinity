package com.puresoltechnologies.purifinity.server.core.api.repositories;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@Local
public interface RepositoryTypePluginService extends
		PluginService<RepositoryType> {

}
