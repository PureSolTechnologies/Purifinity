package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;

public class StoredFile {

	private final SourceCodeLocation sourceCodeLocation;
	private final FileInformation fileInformation;

	public StoredFile(SourceCodeLocation sourceCodeLocation,
			FileInformation fileInformation) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.fileInformation = fileInformation;
	}

	public SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public FileInformation getFileInformation() {
		return fileInformation;
	}

}
