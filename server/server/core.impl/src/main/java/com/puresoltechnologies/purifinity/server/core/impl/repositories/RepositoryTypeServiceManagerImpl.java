package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypeServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypeServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;

@Singleton
@EJBFacade
public class RepositoryTypeServiceManagerImpl extends
		AbstractServiceManager<RepositoryTypeServiceInformation> implements
		RepositoryTypeServiceManager, RepositoryTypeServiceManagerRemote {

	private static final String ID = RepositoryTypeServiceManagerImpl.class
			.getName();
	private static final String NAME = "Repository Plugin";
	private static final Version VERSION = new Version(0, 3, 0);
	private static final String DESCRIPTION = "Contains standard repositories to retrieve source code from.";
	private static final String VENDOR = "PureSol Technologies";
	private static final URL VENDOR_URL;
	static {
		try {
			VENDOR_URL = new URL("http://puresol-technologies.com");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not create vendor URL.", e);
		}
	}
	private static final String PATH_TO_UI = null;

	private static final PluginInformation INFORMATION = new PluginInformation(
			ID, NAME, VERSION, DESCRIPTION, VENDOR, VENDOR_URL, PATH_TO_UI);

	public RepositoryTypeServiceManagerImpl() {
		super("Repository Type Service Manager");
	}

	@PostConstruct
	public void addDefaultRepositoryTypes() {
		registerService(INFORMATION, "jndi:dir",
				DirectoryRepositoryTypeCreator.create());
		registerService(INFORMATION, "jndi:git",
				GITRepositoryTypeCreator.create());
	}
}
