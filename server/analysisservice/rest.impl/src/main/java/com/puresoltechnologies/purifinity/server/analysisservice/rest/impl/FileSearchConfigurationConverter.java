package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.util.List;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;

public class FileSearchConfigurationConverter {

	public static FileSearchConfiguration fromREST(
			com.puresoltechnologies.purifinity.server.analysisservice.rest.api.FileSearchConfiguration configuration) {
		List<String> fileIncludes = configuration.getFileIncludes();
		List<String> fileExcludes = configuration.getFileExcludes();
		List<String> locationIncludes = configuration.getLocationIncludes();
		List<String> locationExcludes = configuration.getLocationExcludes();
		boolean ignoreHidden = configuration.isIgnoreHidden();
		return new FileSearchConfiguration(locationIncludes, locationExcludes,
				fileIncludes, fileExcludes, ignoreHidden);
	}

}
