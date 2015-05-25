package com.puresoltechnologies.purifinity.repository.spi;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.puresoltechnologies.commons.domain.Configurable;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public interface Repository extends Serializable, Configurable {

	/**
	 * This method returns the repository's name,
	 * 
	 * @return A {@link String} is returned containing the name of the
	 *         repository.
	 */
	public String getName();

	public String getHumanReadableLocationString(Properties repositoryLocation);

	public List<SourceCodeLocation> getSourceCodes(Properties configuration,
			FileSearchConfiguration fileSearchConfiguration);

}
