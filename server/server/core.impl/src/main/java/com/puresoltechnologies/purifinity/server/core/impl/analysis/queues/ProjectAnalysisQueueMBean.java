package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;
import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.NamingException;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.commons.trees.TreePrinter;
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.LanguageNotSupportedException;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@MessageDriven(name = "ProjectAnalysisQueueMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectAnalysisQueue.TYPE), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectAnalysisQueue.NAME), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectAnalysisQueueMBean implements MessageListener {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private FileStoreService fileStore;

	@Inject
	private AnalyzerPluginService analyzerPluginService;

	@Override
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

			System.out.println("Start analysis for project '"
					+ analysisProject.getSettings().getName() + "' and run '"
					+ analysisRunInformation.getRunUUID() + "'.");
			new TreePrinter(System.out).println(analysisRunFileTree);

			analyze(analysisRunFileTree);

			AnalysisProjectSettings projectSettings = analysisProject
					.getSettings();
			logger.info("Start analysis for project '"
					+ projectSettings.getName() + "'.");
		} catch (JMSException | IOException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not analyze the project.", e);
		}
	}

	private void analyze(AnalysisRunFileTree analysisRunFileTree) {
		TreeWalker.walk(new TreeVisitor<AnalysisRunFileTree>() {

			@Override
			public WalkingAction visit(AnalysisRunFileTree tree) {
				if (tree.isFile()) {
					analyze(tree.getHashId(), tree.getSourceCodeLocation());
				}
				return WalkingAction.PROCEED;
			}
		}, analysisRunFileTree);
	}

	private AnalysisInformation analyze(HashId hashId,
			SourceCodeLocation sourceCodeLocation) {
		Date startTime = new Date();
		try {
			if (fileStore.wasAnalyzed(hashId)) {
				return loadAnalysis(hashId);
			} else {
				return createNewAnalysis(startTime, hashId, sourceCodeLocation);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Throwable t = e;
			StringBuilder message = new StringBuilder();
			int id = 1;
			while (t != null) {
				message.append(id).append(")\t");
				message.append(t.getMessage());
				message.append("\n");
				t = t.getCause();
				id++;
			}
			return new AnalysisInformation(hashId, startTime, 0, false, "n/a",
					"n/a", message.toString());
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
	private AnalysisInformation createNewAnalysis(Date startTime,
			HashId hashId, SourceCodeLocation sourceFile)
			throws AnalyzerException, IOException, FileStoreException {
		try {
			for (AnalyzerInformation analyzerInformation : analyzerPluginService
					.getServices()) {
				AnalyzerRemotePlugin instance = analyzerPluginService
						.createInstance(analyzerInformation.getJndiName());
				if (instance.isSuitable(sourceFile)) {
					logger.info("'"
							+ sourceFile.getHumanReadableLocationString()
							+ "' is a suitable file for '" + instance.getName()
							+ "'.");
				}
			}
			throw new LanguageNotSupportedException();
			//
			// CodeAnalyzer analyzer =
			// CodeAnalyzerFactory.createFactory().create(
			// sourceFile);
			// analyzer.analyze();
			//
			// fileStore.storeAnalysis(hashId, analyzer.getAnalysis());
			// return analyzer.getAnalysis().getAnalysisInformation();
		} catch (LanguageNotSupportedException | NamingException e) {
			logger.warn("File " + sourceFile.getHumanReadableLocationString()
					+ " could be analyzed.");
			return new AnalysisInformation(hashId, startTime, 0, false, "n/a",
					"n/a", "No analyzer found.");
		}
	}

	/**
	 * This method loads an already existing analysis.
	 * 
	 * @param hashId
	 * @param sourceFile
	 * @return
	 * @throws FileStoreException
	 */
	private AnalysisInformation loadAnalysis(HashId hashId)
			throws FileStoreException {
		CodeAnalysis analysis = fileStore.loadAnalysis(hashId, Thread
				.currentThread().getContextClassLoader());
		AnalysisInformation analyzedCode = new AnalysisInformation(hashId,
				analysis.getStartTime(), analysis.getDuration(), true,
				analysis.getLanguageName(), analysis.getLanguageVersion());
		return analyzedCode;
	}
}
