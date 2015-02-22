package com.puresoltechnologies.purifinity.server.socket.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStatusInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.ProcessState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.PurifinityProcessStates;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityProcessStatesDecoder;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityProcessStatesEncoder;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ServerEndpoint(//
value = "/processes",//
encoders = { PurifinityProcessStatesEncoder.class },//
decoders = { PurifinityProcessStatesDecoder.class }//
)
@Singleton
public class PurifinityProcessStatesSocket {

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private AnalysisStore analysisStore;

    @Inject
    private AnalysisProcessStateTracker processStateTracker;

    private Session session = null;

    @OnOpen
    public void open(Session session, EndpointConfig config) {
	this.session = session;
	eventLogger.logEvent(PurifinityProgressSocketEvents
		.createSocketOpenedEvent());
    }

    @OnClose
    public void close(CloseReason reason) {
	String reasonPhrase = reason != null ? reason.getReasonPhrase()
		: "<no reason provided>";
	eventLogger.logEvent(PurifinityProgressSocketEvents
		.createSocketCloseEvent(reasonPhrase));
    }

    @OnError
    public void handleError(Throwable throwable) {
	eventLogger.logEvent(PurifinityProgressSocketEvents
		.createSocketErrorEvent(throwable));
    }

    @OnMessage
    public PurifinityProcessStates getProcessProgresses(String request) {
	logger.debug("Got request for status.");
	return getProgresses();
    }

    private PurifinityProcessStates getProgresses() {
	Date time = new Date();
	List<ProcessState> processStates = new ArrayList<ProcessState>();
	for (AnalysisProcessStatusInformation processStatusInformation : processStateTracker
		.readProcessStates()) {
	    String projectId = processStatusInformation.getProjectId();
	    try {
		AnalysisProjectSettings projectSettings;
		projectSettings = analysisStore
			.readAnalysisProjectSettings(projectId);

		ProcessState processState = new ProcessState(
			projectSettings.getName(),
			processStatusInformation.getState(), -1, 100, "files");
		processStates.add(processState);
	    } catch (AnalysisStoreException e) {
		logger.error("Could not read project settings for project '"
			+ projectId + "'.", e);
	    }
	}
	PurifinityProcessStates purifinityProcessStates = new PurifinityProcessStates(
		time, processStates);
	return purifinityProcessStates;
    }

    @Schedule(hour = "*", minute = "*", second = "*/5")
    public void sendUpdate() {
	if (session != null) {
	    logger.trace("Send process progress updates...");
	    PurifinityProcessStates states = getProgresses();
	    for (Session client : session.getOpenSessions()) {
		try {
		    client.getBasicRemote().sendObject(states);
		} catch (IOException | EncodeException | NullPointerException e) {
		    logger.error("Could not send status message.", e);
		}
	    }
	}
    }
}
