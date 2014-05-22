package com.puresoltechnologies.purifinity.server.client.socket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
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

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.server.domain.HistogramChartData;
import com.puresoltechnologies.purifinity.server.socket.api.HistogramChartDataDecoder;
import com.puresoltechnologies.purifinity.server.socket.api.HistogramChartDataRequest;
import com.puresoltechnologies.purifinity.server.socket.api.HistogramChartDataRequestEncoder;

@ClientEndpoint(//
encoders = { HistogramChartDataRequestEncoder.class }, //
decoders = { HistogramChartDataDecoder.class }//
)
public class HistogramChartDataProviderClient implements AutoCloseable {

	private static final Logger logger = LoggerFactory
			.getLogger(HistogramChartDataProviderClient.class);

	private static final URI DEFAULT_URI;
	static {
		try {
			DEFAULT_URI = new URI(
					"ws://localhost:8080/purifinityserver/socket/dataprovider/charts/histogramchart");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private static final WebSocketContainer webSocketContainer = ContainerProvider
			.getWebSocketContainer();

	private Session session = null;

	private final Object getHistogramChartDataLock = new Object();
	private HistogramChartData histogramChartData = null;
	private CountDownLatch getHistogramChartDataLatch = null;

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
	}

	@OnClose
	public void close(Session session, CloseReason closeReason) {
		logger.info("Socket is about to close. Reason: "
				+ closeReason.getReasonPhrase());
	}

	@OnError
	public void error(Session session, Throwable throwable) {
		logger.error("Got error.", throwable);
	}

	@OnMessage
	public void retrieveHistogramChartData(Session session,
			HistogramChartData data) {
		this.histogramChartData = data;
		if (getHistogramChartDataLatch != null) {
			getHistogramChartDataLatch.countDown();
		}
	}

	public HistogramChartData loadHistogramChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) throws IOException {
		if (!isConnected()) {
			connect();
		}
		Basic basicRemote = session.getBasicRemote();
		synchronized (getHistogramChartDataLock) {
			try {
				getHistogramChartDataLatch = new CountDownLatch(1);
				basicRemote.sendObject(new HistogramChartDataRequest(
						analysisProject, analysisRun, evaluatorName, parameter,
						codeRangeType));
				getHistogramChartDataLatch.await(10, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				throw new IOException("Communication was aborted.", e);
			} catch (EncodeException e) {
				throw new RuntimeException("Could not send request.", e);
			}
		}
		return histogramChartData;
	};

}
