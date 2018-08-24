package com.puresoltechnologies.purifinity.server.socket.impl;

import java.io.IOException;

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

import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.PurifinityJobStates;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityJobStatesDecoder;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityJobStatesEncoder;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ServerEndpoint(//
value = "/jobs",//
encoders = { PurifinityJobStatesEncoder.class },//
decoders = { PurifinityJobStatesDecoder.class }//
)
@Singleton
public class PurifinityJobsSocket {

	@Inject
	private Logger logger;

	@Inject
	private EventLoggerRemote eventLogger;

	@Inject
	private AnalysisService analysisService;

	private Session session = null;

	@OnOpen
	public void open(Session session, EndpointConfig config) {
		this.session = session;
		eventLogger.logEvent(PurifinityJobsSocketEvents
				.createSocketOpenedEvent());
	}

	@OnClose
	public void close(CloseReason reason) {
		String reasonPhrase = reason != null ? reason.getReasonPhrase()
				: "<no reason provided>";
		eventLogger.logEvent(PurifinityJobsSocketEvents
				.createSocketCloseEvent(reasonPhrase));
	}

	@OnError
	public void handleError(Throwable throwable) {
		eventLogger.logEvent(PurifinityJobsSocketEvents
				.createSocketErrorEvent(throwable));
	}

	@OnMessage
	public PurifinityJobStates getJobStates(String request) {
		logger.debug("Got request for status.");
		return getJobStates();
	}

	private PurifinityJobStates getJobStates() {
		return analysisService.getJobStates();
	}

	@Schedule(hour = "*", minute = "*", second = "*/5")
	public void sendUpdate() {
		if (session != null) {
			logger.trace("Send job updates...");
			PurifinityJobStates states = getJobStates();
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
