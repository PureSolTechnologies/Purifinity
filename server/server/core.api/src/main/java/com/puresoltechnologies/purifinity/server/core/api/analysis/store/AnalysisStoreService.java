package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import java.util.Map;
import java.util.UUID;

import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;

public interface AnalysisStoreService extends AnalysisStore {

	/**
	 * This method is used to create a content and file tree in the Titan
	 * database based on a {@link Map} containing the {@link SourceCodeLocation}
	 * s of all files stored and their {@link HashId}s calculated during
	 * storage.
	 * 
	 * @param projectUUID
	 *            is the {@link UUID} of the analysis project.
	 * @param runUUID
	 *            is the {@link UUID} of the analysis run.
	 * @param rootNodeName
	 *            is the name of the root node of the tree. In almost all cases
	 *            this is the project's name.
	 * @param storedSources
	 *            is the {@link Map} of {@link SourceCodeLocation}s and
	 *            {@link HashId}s of the files to be used to create the content
	 *            and file tree.
	 * @return An {@link AnalysisRunFileTree} is returned which was created
	 *         during the run.
	 * @throws AnalysisStoreException
	 *             is thrown if anything goes wrong during storage.
	 */
	public AnalysisRunFileTree createAndStoreFileAndContentTree(
			UUID projectUUID, UUID runUUID, String rootNodeName,
			Map<SourceCodeLocation, HashId> storedSources)
			throws AnalysisStoreException;

}
