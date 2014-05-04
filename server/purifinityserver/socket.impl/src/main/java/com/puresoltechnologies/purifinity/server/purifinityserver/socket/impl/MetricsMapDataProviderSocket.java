package com.puresoltechnologies.purifinity.server.purifinityserver.socket.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.purifinityserver.core.api.ChartDataProvider;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.MetricsMapData;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.MetricsMapDataEncoder;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.MetricsMapDataRequest;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.MetricsMapDataRequestDecoder;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@ServerEndpoint(value = "/dataprovider/charts/metricsmap", //
encoders = { MetricsMapDataEncoder.class }, //
decoders = { MetricsMapDataRequestDecoder.class }//
)
@Stateless
public class MetricsMapDataProviderSocket {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private ChartDataProvider chartDataProvider;

	@OnOpen
	public void open(Session session, EndpointConfig config) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketOpenedEvent());
	}

	@OnClose
	public void close(Session session, CloseReason reason) {
		String reasonPhrase = reason != null ? reason.getReasonPhrase()
				: "<no reason provided>";
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketCloseEvent(reasonPhrase));
	}

	@OnMessage
	public MetricsMapData getHistogramChartData(Session session,
			MetricsMapDataRequest request) {
		logger.info("Got request for metrics map data.");
		return chartDataProvider.loadMapValues(request.getAnalysisProject(),
				request.getAnalysisRun(), request.getMapEvaluatorName(),
				request.getMapParameter(), request.getColorEvaluatorName(),
				request.getColorParameter());
	}

	@OnError
	public void handleError(Session session, Throwable throwable) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketErrorEvent(throwable));
	}
}
