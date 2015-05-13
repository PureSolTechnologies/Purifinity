package com.puresoltechnologies.purifinity.server.core.api.repositories;

import java.util.Properties;

import javax.ejb.Local;

import com.puresoltechnologies.parsers.analyzer.Analyzer;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Local
public interface RepositoryServiceManager extends
		ServiceManager<RepositoryServiceInformation, Repository> {

	/**
	 * This method creates a {@link Analyzer} instance out of the analyzer id.
	 * This analyzer is actually a proxy via remoting to the plugin.
	 * 
	 * @param evaluatorId
	 *            is the id of the evaluator.
	 * @return An {@link RepositoryService} proxy is returned.
	 */
	public Repository getInstanceById(String repositoryId);

	public RepositoryLocation createFromSerialization(
			Properties repositoryLocation);

}
