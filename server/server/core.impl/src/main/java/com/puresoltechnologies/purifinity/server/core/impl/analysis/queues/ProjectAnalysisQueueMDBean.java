package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.server.common.jms.JMSMessageSender;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;

@MessageDriven(name = "ProjectAnalysisQueueMBean",//
activationConfig = {//
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectAnalysisQueue.TYPE), //
	@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectAnalysisQueue.NAME), //
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectAnalysisQueueMDBean implements MessageListener {

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

    @Resource(mappedName = ProjectEvaluationQueue.NAME)
    private Queue projectEvaluationQueue;

    @Inject
    private FileStoreService fileStore;

    @Inject
    private AnalyzerServiceManager analyzerServiceManager;

    @Inject
    private AnalysisProcessStateTracker analysisProcessStateTracker;

    @Inject
    private JMSMessageSender messageSender;

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

	    analysisProcessStateTracker.changeProcessState(
		    analysisRunInformation.getProjectUUID(),
		    analysisRunInformation.getRunUUID(),
		    AnalysisProcessTransition.START_ANALYSIS);

	    analyze(analysisRunFileTree);

	    analysisProcessStateTracker.changeProcessState(
		    analysisRunInformation.getProjectUUID(),
		    analysisRunInformation.getRunUUID(),
		    AnalysisProcessTransition.QUEUE_FOR_EVALUATION);

	    Map<String, String> stringMap = new HashMap<>();
	    stringMap.put("AnalysisProject",
		    JSONSerializer.toJSONString(analysisProject));
	    stringMap.put("AnalysisRunInformation",
		    JSONSerializer.toJSONString(analysisRunInformation));

	    messageSender.sendMessage(projectEvaluationQueue, stringMap);
	} catch (JMSException | IOException e) {
	    // An issue occurred, re-queue the request.
	    eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
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
		    analyze(tree.getHashId(), tree.getSourceCodeLocation());
		}
		return WalkingAction.PROCEED;
	    }
	}, analysisRunFileTree);
    }

    private void analyze(HashId hashId, SourceCodeLocation sourceCodeLocation) {
	try {
	    if (!fileStore.wasAnalyzed(hashId)) {
		Date startTime = new Date();
		createNewAnalysis(startTime, hashId, sourceCodeLocation);
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
    private void createNewAnalysis(Date startTime, HashId hashId,
	    SourceCodeLocation sourceFile) throws AnalyzerException,
	    IOException, FileStoreException {
	for (AnalyzerServiceInformation analyzerInformation : analyzerServiceManager
		.getServices()) {
	    if (analyzerServiceManager.isActive(analyzerInformation.getId())) {
		ProgrammingLanguageAnalyzer instance = analyzerServiceManager
			.createInstance(analyzerInformation.getJndiName());
		if (instance.isSuitable(sourceFile)) {
		    logger.info("'"
			    + sourceFile.getHumanReadableLocationString()
			    + "' is a suitable file for '" + instance.getName()
			    + "'.");
		    CodeAnalysis codeAnalysis = instance.analyze(sourceFile);
		    fileStore.storeAnalysis(hashId, codeAnalysis);
		}
	    }
	}
    }
}
