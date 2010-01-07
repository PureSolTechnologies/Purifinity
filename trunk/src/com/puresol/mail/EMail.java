package com.puresol.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class EMail {

    private String[] tos; // Liste mit mehreren Empf�ngern
    private String from;
    private String mailServer;
    private String subject;

    private MimeMessage msg = null;
    private InternetAddress[] addresses = null;
    private Properties props = null;
    private Session session = null;

    public EMail(String to, String from, String mailServer, String subject) {
	props = new Properties();
	props.put("mail.smtp.host", mailServer);

	this.tos = new String[] { to };
	this.from = from;
	this.mailServer = mailServer;
	this.subject = subject;

	session = Session.getInstance(props, null);
	session.setDebug(Logger.getRootLogger().isDebugEnabled());
    }

    public EMail(String[] tos, String from, String mailServer,
	    String subject) {
	this.props = new Properties();
	props.put("mail.smtp.host", mailServer);

	this.tos = tos;
	this.from = from;
	this.mailServer = mailServer;
	this.subject = subject;

	session = Session.getInstance(props, null);
	session.setDebug(Logger.getRootLogger().isDebugEnabled());
    }

    public MimeMessage createMessage(String text, String... filenames) {
	try {

	    setMsg(new MimeMessage(session));

	    msg.setFrom(new InternetAddress(getFrom()));

	    addresses = new InternetAddress[getTos().length];
	    for (int i = 0; i < getTos().length; i++) {
		addresses[i] = new InternetAddress(getTos()[i]);
	    }

	    msg.setRecipients(Message.RecipientType.TO, addresses);
	    msg.setSubject(getSubject());
	    msg.setSentDate(new Date());

	    MimeBodyPart mbp = new MimeBodyPart();
	    mbp.setText(text);

	    Multipart mp = new MimeMultipart();
	    mp.addBodyPart(mbp);

	    for (String filename : filenames) {
		FileDataSource fds = new FileDataSource(filename);
		mbp.setDataHandler(new DataHandler(fds));
		mbp.setFileName(fds.getName());
	    }

	    msg.setContent(mp);

	} catch (MessagingException mex) {
	    mex.printStackTrace();
	    Exception ex = null;
	    if ((ex = mex.getNextException()) != null) {
		ex.printStackTrace();
	    }
	}
	return msg;
    }

    public boolean sendMessage(MimeMessage msg) {
	try {
	    Transport.send(msg);
	} catch (MessagingException mex) {
	    mex.printStackTrace();
	    Exception ex = null;
	    if ((ex = mex.getNextException()) != null) {
		ex.printStackTrace();
	    }
	    return false;
	}
	return true;
    }

    public String getFrom() {
	return from;
    }

    public String getMailServer() {
	return mailServer;
    }

    public String getSubject() {
	return subject;
    }

    public MimeMessage getMsg() {
	return msg;
    }

    public void setMsg(MimeMessage msg) {
	this.msg = msg;
    }

    public String[] getTos() {
	return tos;
    }

    public InternetAddress[] getAddress() {
	return addresses;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	EMail sm =
		new EMail("Ludwig@puresol-technologies.com",
			"contact@puresol-technologies.com", "smtp.web.de",
			"test email");
	sm.sendMessage(sm.createMessage("This is a simple test email..."));
    }

}
