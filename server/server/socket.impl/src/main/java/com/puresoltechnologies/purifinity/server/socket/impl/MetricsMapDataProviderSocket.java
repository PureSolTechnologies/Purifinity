package com.puresoltechnologies.purifinity.server.socket.impl;

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

import com.puresoltechnologies.purifinity.server.core.api.ChartDataProvider;
import com.puresoltechnologies.purifinity.server.domain.MetricsMapData;
import com.puresoltechnologies.purifinity.server.socket.api.MetricsMapDataEncoder;
import com.puresoltechnologies.purifinity.server.socket.api.MetricsMapDataRequest;
import com.puresoltechnologies.purifinity.server.socket.api.MetricsMapDataRequestDecoder;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ServerEndpoint(value = "/dataprovider/charts/metricsmap", //
encoders = { MetricsMapDataEncoder.class }, //
decoders = { MetricsMapDataRequestDecoder.class }//
)
@Stateless
public class MetricsMapDataProviderSocket {

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

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
