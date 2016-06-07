package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.util.List;
import java.util.Properties;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesValue;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class RepositoryServiceManagerImpl extends AbstractServiceManager<RepositoryServiceInformation, Repository>
	implements RepositoryServiceManager, RepositoryServiceManagerRemote {

    @Inject
    private PreferencesStore preferencesStore;

    public RepositoryServiceManagerImpl() {
	super("Repository Service Manager");
    }

    @Override
    @Lock(LockType.READ)
    public Repository createProxy(String jndi) {
	Repository repository = JndiUtils.createRemoteEJBInstance(Repository.class, jndi);
	String repositoryName = repository.getName();
	RepositoryServiceInformation information = findByName(repositoryName);
	for (ConfigurationParameter<?> configurationParameter : repository.getConfigurationParameters()) {
	    PreferencesValue<?> value = preferencesStore.getPluginDefaultPreference(information.getId(),
		    configurationParameter);
	    if (value != null) {
		repository.setConfigurationParameter(configurationParameter, value.getValue());
	    }
	}
	return repository;
    }

    private RepositoryServiceInformation findByName(String name) {
	for (RepositoryServiceInformation information : getServices()) {
	    if (information.getName().equals(name)) {
		return information;
	    }
	}
	return null;
    }

    @Override
    @Lock(LockType.READ)
    public Repository getInstanceById(String analyzerId) {
	for (RepositoryServiceInformation repository : getServices()) {
	    if (repository.getId().equals(analyzerId)) {
		return createProxy(repository.getJndiName());
	    }
	}
	return null;
    }

    @Override
    @Lock(LockType.READ)
    public RepositoryLocation createFromSerialization(Properties repositoryLocation) {
	String repositoryId = repositoryLocation.getProperty(RepositoryManager.REPOSITORY_ID_PROPERTY);
	Repository instance = getInstanceById(repositoryId);
	return new RepositoryLocation() {

	    private static final long serialVersionUID = 1084534331267078708L;

	    @Override
	    public List<SourceCodeLocation> getSourceCodes(FileSearchConfiguration fileSearchConfiguration) {
		return instance.getSourceCodes(repositoryLocation, fileSearchConfiguration);
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
		return instance.getHumanReadableLocationString(repositoryLocation);
	    }
	};
    }
}
