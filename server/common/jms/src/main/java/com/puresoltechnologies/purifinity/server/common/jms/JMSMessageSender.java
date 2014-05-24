package com.puresoltechnologies.purifinity.server.common.jms;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class JMSMessageSender {

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory cf;

	private Connection connection;
	private Session session;

	@PostConstruct
	public void connect() throws JMSException {
		connection = cf.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	@PreDestroy
	public void disconnect() throws JMSException {
		try {
			if (session != null) {
				session.close();
			}
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void sendMessage(Queue queue, String text) throws JMSException {
		TextMessage message = session.createTextMessage(text);
		sendMessage(queue, message);
	}

	public void sendMessage(Queue queue, Map<String, String> stringMap)
			throws JMSException {
		MapMessage message = session.createMapMessage();
		for (Entry<String, String> entry : stringMap.entrySet()) {
			message.setString(entry.getKey(), entry.getValue());
		}
		sendMessage(queue, message);
	}

	private void sendMessage(Queue queue, Message message) throws JMSException {
		try (MessageProducer producer = session.createProducer(queue)) {
			connection.start();
			try {
				producer.send(message);
			} finally {
				connection.stop();
			}
		}
	}

}
