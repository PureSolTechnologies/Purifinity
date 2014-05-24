package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Map;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

public interface AnalysisStoreService extends AnalysisStore {

	public AnalysisRunFileTree createAndStoreFileAndContentTree(
			UUID projectUUID, UUID runUUID,
			Map<SourceCodeLocation, HashId> storedSources)
			throws AnalysisStoreException;

}
