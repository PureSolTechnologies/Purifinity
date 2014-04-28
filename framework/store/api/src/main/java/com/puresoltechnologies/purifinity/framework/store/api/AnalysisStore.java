package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.UUID;

import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;

/**
 * This is the central interface to access the delivered analysis store. The
 * analysis store might be an implementation of a simple file system base store,
 * a database implementation or an implementation of a whole Jave EE enterprise
 * solution.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisStore extends AnalysisStoreClient {

	public void storeAnalysisFileTree(UUID projectUUID, UUID analysisRunUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException;

	public void storeAnalysisFileTree(
			ProgressObserver<AnalysisStore> progressObserver, UUID projectUUID,
			UUID analysisRunUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException;

}
