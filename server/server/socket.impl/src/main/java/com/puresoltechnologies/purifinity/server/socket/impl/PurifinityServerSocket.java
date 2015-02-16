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
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityServerStatusEncoder;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityServerStatusRequest;
import com.puresoltechnologies.purifinity.server.socket.api.PurifinityServerStatusRequestDecoder;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ServerEndpoint(value = "/server", //
encoders = { PurifinityServerStatusEncoder.class }, //
decoders = { PurifinityServerStatusRequestDecoder.class }//
)
@Singleton
// @Startup
public class PurifinityServerSocket {

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
	eventLogger.logEvent(PurifinityServerSocketEvents
		.createSocketOpenedEvent());
    }

    @OnClose
    public void close(CloseReason reason) {
	String reasonPhrase = reason != null ? reason.getReasonPhrase()
		: "<no reason provided>";
	eventLogger.logEvent(PurifinityServerSocketEvents
		.createSocketCloseEvent(reasonPhrase));
    }

    @OnMessage
    public PurifinityServerStatus getServerStatus(
	    PurifinityServerStatusRequest request) {
	logger.info("Got request for status.");
	return purifinityServer.getStatus();
    }

    @OnError
    public void handleError(Throwable throwable) {
	eventLogger.logEvent(PurifinityServerSocketEvents
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
