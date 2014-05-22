package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.AnalysisRunnerImpl;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Stateless
public class AnalysisServiceBean implements AnalysisService {

	private static final int RUN_TIMEOUT_IN_SECONDS = 3600;

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private AnalysisStoreService analysisStoreService;

	@Inject
	private FileStoreService fileStoreService;

	@Inject
	private AnalyzerPluginService analyzerRegistration;

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(AnalysisServiceEvents.createStartupEvent());
	}

	@PreDestroy
	public void shutdown() {
		eventLogger.logEvent(AnalysisServiceEvents.createShutdownEvent());
	}

	@Override
	public void triggerNewAnalysis(UUID projectUUID)
			throws AnalysisStoreException {
		try {
			AnalysisRunnerImpl analysisRunner = new AnalysisRunnerImpl(
					analysisStoreService, fileStoreService, projectUUID);

			ExecutorService executor = Executors.newSingleThreadExecutor();
			Future<Boolean> future = executor.submit(analysisRunner);
			executor.shutdown();
			executor.awaitTermination(RUN_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
			try {
				Boolean result = future.get();
				if ((result != null) && (result)) {
					logger.info("Analysis run finished without issues.");
				} else {
					logger.warn("Analysis run finished with issues.");
				}
			} catch (ExecutionException e) {
				logger.error("Analysis run finished with an exception!", e);
				throw new RuntimeException("Analysis was not successful.");
			}
		} catch (InterruptedException e) {
			logger.warn("Analysis run was interrupted!", e);
		}
	}

	@Override
	public Collection<AnalyzerInformation> getAnalyzers() {
		return analyzerRegistration.getServices();
	}

	@Override
	public Collection<RepositoryType> getRepositoryTypes() {
		return repositoryTypePluginService.getServices();
	}

}
