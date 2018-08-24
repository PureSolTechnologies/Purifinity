package com.puresoltechnologies.toolshed.server.impl.ws;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;

@Metered
@Timed
@ExceptionMetered
@ServerEndpoint("/ws/kpi")
public class AnnotatedEchoServer {

    @OnOpen
    public void myOnOpen(final Session session) throws IOException {
	session.getAsyncRemote().sendText("welcome");
    }

    @OnMessage
    public void myOnMsg(final Session session, String message) {
	session.getAsyncRemote().sendText(message.toUpperCase());
    }

    @OnError
    public void myOnError(final Session session, Throwable throwable) {
	session.getAsyncRemote().sendText(throwable.getMessage().toUpperCase());
    }

    @OnClose
    public void myOnClose(final Session session, CloseReason cr) {
    }
}
