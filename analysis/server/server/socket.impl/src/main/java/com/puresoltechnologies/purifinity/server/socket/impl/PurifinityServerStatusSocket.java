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

import com.puresoltechnologies.purifinity.server.core.api.PurifinityServer;
import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityServerStatusDecoder;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityServerStatusEncoder;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ServerEndpoint(//
value = "/status", //
encoders = { PurifinityServerStatusEncoder.class }, //
decoders = { PurifinityServerStatusDecoder.class }//
)
@Singleton
public class PurifinityServerStatusSocket {

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private PurifinityServer purifinityServer;

    private Session session = null;

    @OnOpen
    public void open(Session session, EndpointConfig config) {
	this.session = session;
	eventLogger.logEvent(PurifinityServerStatusSocketEvents
		.createSocketOpenedEvent());
    }

    @OnClose
    public void close(CloseReason reason) {
	String reasonPhrase = reason != null ? reason.getReasonPhrase()
		: "<no reason provided>";
	eventLogger.logEvent(PurifinityServerStatusSocketEvents
		.createSocketCloseEvent(reasonPhrase));
    }

    @OnMessage
    public PurifinityServerStatus getServerStatus(String request) {
	logger.debug("Got request for status.");
	return purifinityServer.getStatus();
    }

    @OnError
    public void handleError(Throwable throwable) {
	eventLogger.logEvent(PurifinityServerStatusSocketEvents
		.createSocketErrorEvent(throwable));
    }

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void periodicUpdate() {
	if (session != null) {
	    logger.trace("Periodic status update...");
	    PurifinityServerStatus status = purifinityServer.getStatus();
	    for (Session client : session.getOpenSessions()) {
		try {
		    client.getBasicRemote().sendObject(status);
		} catch (IOException | EncodeException | NullPointerException e) {
		    logger.error("Could not send status message.", e);
		}
	    }
	}
    }
}
