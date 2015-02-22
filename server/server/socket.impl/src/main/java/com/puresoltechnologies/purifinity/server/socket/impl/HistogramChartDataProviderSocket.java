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
import com.puresoltechnologies.purifinity.server.domain.HistogramChartData;
import com.puresoltechnologies.purifinity.server.socket.api.HistogramChartDataEncoder;
import com.puresoltechnologies.purifinity.server.socket.api.HistogramChartDataRequest;
import com.puresoltechnologies.purifinity.server.socket.api.HistogramChartDataRequestDecoder;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@ServerEndpoint(value = "/dataprovider/charts/histogramchart", //
encoders = { HistogramChartDataEncoder.class }, //
decoders = { HistogramChartDataRequestDecoder.class }//
)
@Stateless
public class HistogramChartDataProviderSocket {

    @Inject
    private Logger logger;

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private ChartDataProvider chartDataProvider;

    @OnOpen
    public void open(Session session, EndpointConfig config) {
	eventLogger.logEvent(PurifinityServerStatusSocketEvents
		.createSocketOpenedEvent());
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
	String reasonPhrase = reason != null ? reason.getReasonPhrase()
		: "<no reason provided>";
	eventLogger.logEvent(PurifinityServerStatusSocketEvents
		.createSocketCloseEvent(reasonPhrase));
    }

    @OnMessage
    public HistogramChartData getHistogramChartData(Session session,
	    HistogramChartDataRequest request) {
	logger.info("Got request for histogram chart data.");
	return chartDataProvider.loadHistogramChartData(
		request.getAnalysisProject(), request.getAnalysisRun(),
		request.getEvaluatorName(), request.getParameter(),
		request.getCodeRangeType());
    }

    @OnError
    public void handleError(Session session, Throwable throwable) {
	eventLogger.logEvent(PurifinityServerStatusSocketEvents
		.createSocketErrorEvent(throwable));
    }
}
