package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@EJBFacade
public class RepositoryServiceManagerImpl extends
		AbstractServiceManager<RepositoryServiceInformation, Repository>
		implements RepositoryServiceManager, RepositoryServiceManagerRemote {

	private final Map<String, Repository> programmingLanguageAnalyzers = new HashMap<>();

	public RepositoryServiceManagerImpl() {
		super("Repository Service Manager");
	}

	@Override
	@Lock(LockType.WRITE)
	public Repository createProxy(String jndi) {
		Repository repository = programmingLanguageAnalyzers.get(jndi);
		if (repository == null) {
			repository = JndiUtils.createRemoteEJBInstance(Repository.class,
					jndi);
			programmingLanguageAnalyzers.put(jndi, repository);
		}
		return repository;
	}

	@Override
	public Repository getInstanceById(String analyzerId) {
		for (RepositoryServiceInformation repository : getServices()) {
			if (repository.getId().equals(analyzerId)) {
				return createProxy(repository.getJndiName());
			}
		}
		return null;
	}

	@Override
	public RepositoryLocation createFromSerialization(
			Properties repositoryLocation) {
		String repositoryId = repositoryLocation
				.getProperty(RepositoryService.REPOSITORY_ID_PROPERTY);
		Repository instance = getInstanceById(repositoryId);
		return new RepositoryLocation() {

			private static final long serialVersionUID = 1084534331267078708L;

			@Override
			public List<SourceCodeLocation> getSourceCodes(
					FileSearchConfiguration fileSearchConfiguration) {
				return instance.getSourceCodes(repositoryLocation,
						fileSearchConfiguration);
			}

			@Override
			public Properties getSerialization() {
				return repositoryLocation;
			}

			@Override
			public String getName() {
				return instance.getName();
			}

			@Override
			public String getHumanReadableLocationString() {
				return instance
						.getHumanReadableLocationString(repositoryLocation);
			}
		};
	}
}
