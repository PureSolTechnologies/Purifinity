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
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ParetoChartData;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.ParetoChartDataEncoder;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.ParetoChartDataRequest;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.ParetoChartDataRequestDecoder;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@ServerEndpoint(value = "/dataprovider/charts/paretochart", //
encoders = { ParetoChartDataEncoder.class }, //
decoders = { ParetoChartDataRequestDecoder.class }//
)
@Stateless
public class ParetoChartDataProviderSocket {

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
	public ParetoChartData getParetoChartData(Session session,
			ParetoChartDataRequest request) {
		logger.info("Got request for pareto chart data.");
		return chartDataProvider.loadParetoChartData(
				request.getAnalysisProject(), request.getAnalysisRun(),
				request.getEvaluatorName(), request.getParameter(),
				request.getCodeRangeType());
	}

	@OnError
	public void handleError(Session session, Throwable throwable) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketErrorEvent(throwable));
	}
}
