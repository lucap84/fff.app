package it.fff.business.notification;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailWorker implements Runnable{
	private static final Logger logger = LogManager.getLogger(MailWorker.class);
	
	private Message message;
	
	public MailWorker(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		try {
			logger.debug("Asynch MailWorker send init...");
			Transport.send(message);
			logger.debug("...Asynch MailWorker send finish");
		} catch (MessagingException e) {
			logger.error("MessagingException during sendRegistrationConfirmMail");
			e.printStackTrace();
		}
		
	}

}
