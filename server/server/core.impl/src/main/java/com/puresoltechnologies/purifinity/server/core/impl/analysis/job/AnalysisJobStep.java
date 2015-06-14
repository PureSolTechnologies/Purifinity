package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;
import com.thinkaurelius.groovyshadedasm.tree.analysis.AnalyzerException;

public class AnalysisJobStep implements MessageListener {

	@Inject
	private Logger logger;

	@Inject
	private EventLoggerRemote eventLogger;

	@Inject
	private FileStoreService fileStore;

	@Inject
	private AnalyzerServiceManager analyzerServiceManager;

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			AnalysisProject analysisProject = JSONSerializer.fromJSONString(
					mapMessage.getString("AnalysisProject"),
					AnalysisProject.class);
			AnalysisRunInformation analysisRunInformation = JSONSerializer
					.fromJSONString(
							mapMessage.getString("AnalysisRunInformation"),
							AnalysisRunInformation.class);
			AnalysisRunFileTree analysisRunFileTree = JSONSerializer
					.fromJSONString(
							mapMessage.getString("AnalysisRunFileTree"),
							AnalysisRunFileTree.class);

			analyze(analysisRunFileTree);

			Map<String, String> stringMap = new HashMap<>();
			stringMap.put("AnalysisProject",
					JSONSerializer.toJSONString(analysisProject));
			stringMap.put("AnalysisRunInformation",
					JSONSerializer.toJSONString(analysisRunInformation));

			// TODO messageSender.sendMessage(projectEvaluationQueue,
			// stringMap);
		} catch (JMSException | IOException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(AnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not analyze the project.", e);
		}
	}

	private void analyze(AnalysisRunFileTree analysisRunFileTree) {
		if (!analyzerServiceManager.hasServices()) {
			throw new IllegalStateException(
					"There is no analyzer installed. To run an analysis is not possible.");
		}
		TreeWalker.walk(new TreeVisitor<AnalysisRunFileTree>() {

			@Override
			public WalkingAction visit(AnalysisRunFileTree tree) {
				if (tree.isFile()) {
					analyzeFile(tree);
				}
				return WalkingAction.PROCEED;
			}
		}, analysisRunFileTree);
	}

	private void analyzeFile(AnalysisRunFileTree node) {
		try {
			if (!fileStore.wasAnalyzed(node.getHashId())) {
				createNewAnalysis(node);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * This method creates a new analysis.
	 * 
	 * @param hashId
	 * @param sourceFile
	 * @return
	 * @throws AnalyzerException
	 * @throws IOException
	 * @throws FileStoreException
	 */
	private void createNewAnalysis(AnalysisRunFileTree node)
			throws IOException, FileStoreException {
		HashId hashId = node.getHashId();
		SourceCodeLocation sourceCodeLocation = node.getSourceCodeLocation();
		for (AnalyzerServiceInformation analyzerInformation : analyzerServiceManager
				.getServices()) {
			if (analyzerServiceManager.isActive(analyzerInformation.getId())) {
				ProgrammingLanguageAnalyzer instance = analyzerServiceManager
						.createProxy(analyzerInformation.getJndiName());
				if (instance.isSuitable(sourceCodeLocation)) {
					logger.info("'"
							+ sourceCodeLocation
									.getHumanReadableLocationString()
							+ "' is a suitable file for '" + instance.getName()
							+ "'.");
					try (InputStream sourceStream = sourceCodeLocation
							.openStream()) {
						CodeAnalysis codeAnalysis = instance
								.analyze(SourceCode.read(sourceStream,
										sourceCodeLocation), hashId);
						fileStore.storeAnalysis(codeAnalysis);
					}
				}
			}
		}
	}
}
