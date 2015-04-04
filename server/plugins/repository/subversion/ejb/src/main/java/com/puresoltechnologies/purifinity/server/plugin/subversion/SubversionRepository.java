package com.puresoltechnologies.purifinity.server.plugin.subversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.repository.spi.AbstractRepository;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

public class SubversionRepository extends AbstractRepository {

	private static final long serialVersionUID = 1876428394155714495L;

	public static final String ID = SubversionRepository.class.getName();
	public static final String NAME = "Subversion Repository";
	public static final Map<String, Parameter<?>> PARAMETERS = new HashMap<>();
	public static final Set<ConfigurationParameter<?>> CONFIG_PARAMETERS = new LinkedHashSet<>();
	public static final RepositoryServiceInformation INFORMATION = new RepositoryServiceInformation(
			ID, NAME, "Subversion Repository.", PARAMETERS, CONFIG_PARAMETERS,
			null, null, null, null);

	public SubversionRepository(String name) {
		super(name);
	}

	@Override
	public String getHumanReadableLocationString() {
		return "Subversion";
	}

	@Override
	public List<SourceCodeLocation> getSourceCodes(
			FileSearchConfiguration fileSearchConfiguration) {
		return new ArrayList<>();
	}

	@Override
	public Properties getSerialization() {
		Properties properties = new Properties();
		properties.setProperty(REPOSITORY_LOCATION_CLASS, getClass().getName());
		properties.setProperty(REPOSITORY_LOCATION_NAME, getName());
		return properties;
	}

}
