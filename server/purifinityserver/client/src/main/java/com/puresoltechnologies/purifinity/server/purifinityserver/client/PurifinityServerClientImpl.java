package com.puresoltechnologies.purifinity.server.purifinityserver.client;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerClient;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusDecoder;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusEncoder;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusListener;

@ClientEndpoint(encoders = { PurifinityServerStatusEncoder.class }, decoders = { PurifinityServerStatusDecoder.class })
public class PurifinityServerClientImpl implements PurifinityServerClient {

	private static final Logger logger = LoggerFactory
			.getLogger(PurifinityServerClientImpl.class);

	private static final URI DEFAULT_URI;
	static {
		try {
			DEFAULT_URI = new URI("ws://localhost:8080/purifinityserver/socket");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private static final WebSocketContainer webSocketContainer = ContainerProvider
			.getWebSocketContainer();

	private final Set<WeakReference<PurifinityServerStatusListener>> statusListeners = new HashSet<>();
	private Session session = null;

	public final boolean isConnected() {
		return session != null;
	}

	public final void connect() throws IOException {
		try {
			if (isConnected()) {
				throw new IllegalStateException(
						"Client was already connected to server.");
			}
			session = webSocketContainer.connectToServer(this, DEFAULT_URI);
		} catch (DeploymentException e) {
			throw new RuntimeException(
					"Connecting to the server was not possible.", e);
		}
	}

	@Override
	public final void close() throws IOException {
		if (!isConnected()) {
			throw new IllegalStateException(
					"Client was not connected to server, yet.");
		}
		session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,
				"Close was requested."));
	}

	@OnOpen
	public void open(Session session, EndpointConfig endpointConfig) {
		logger.info("Socket was opened.");
		for (Entry<String, Object> entry : endpointConfig.getUserProperties()
				.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

	@OnClose
	public void close(Session session, CloseReason closeReason) {
		logger.info("Socket is about to close. Reason: "
				+ closeReason.getReasonPhrase());
	}

	@OnMessage
	public void retrieveMessage(Session session, PurifinityServerStatus status) {
		Iterator<WeakReference<PurifinityServerStatusListener>> iterator = statusListeners
				.iterator();
		while (iterator.hasNext()) {
			WeakReference<PurifinityServerStatusListener> listener = iterator
					.next();
			PurifinityServerStatusListener ref = listener.get();
			if (ref != null) {
				ref.newServerStatus(status);
			}
		}
	}

	@OnError
	public void error(Session session, Throwable throwable) {
		logger.error("Got error.", throwable);
	}

	@Override
	public void requestServerStatus() throws IOException {
		if (!isConnected()) {
			connect();
		}
		Basic basicRemote = session.getBasicRemote();
		basicRemote.sendText("getStatus");
	}

	@Override
	public void addPurifinityServerStatusListener(
			PurifinityServerStatusListener listener) {
		cleanListeners();
		statusListeners.add(new WeakReference<PurifinityServerStatusListener>(
				listener));
	}

	@Override
	public void removePurifinityServerStatusListener(
			PurifinityServerStatusListener listener) {
		cleanListeners();
		Iterator<WeakReference<PurifinityServerStatusListener>> iterator = statusListeners
				.iterator();
		while (iterator.hasNext()) {
			WeakReference<PurifinityServerStatusListener> current = iterator
					.next();
			if (current == listener) {
				iterator.remove();
			}
		}
	}

	private void cleanListeners() {
		Iterator<WeakReference<PurifinityServerStatusListener>> iterator = statusListeners
				.iterator();
		while (iterator.hasNext()) {
			WeakReference<PurifinityServerStatusListener> listener = iterator
					.next();
			if (listener.get() == null) {
				iterator.remove();
			}
		}
	}

}
