package it.fff.business.notification;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.service.impl.SecurityBusinessServiceImpl;
import it.fff.business.util.ConfigurationProvider;
import it.fff.business.util.Constants;

public class MailManager {
	
	private static final Logger logger = LogManager.getLogger(MailManager.class);
	
	private static MailManager mailManager;
	
	private MailManager(){
	}

	public static MailManager getInstance(){
		if(mailManager==null){
			mailManager = new MailManager();
		}
		return mailManager;
	}
	
	public boolean sendVerificationCodeMail(String email, String verificationCode){
		boolean success = true;
		ConfigurationProvider configProvider = ConfigurationProvider.getInstance();
		Properties mailProperties = configProvider.getEmailProperties();
		
		final String username = mailProperties.getProperty(Constants.PROP_MAIL_USERNAME);
		final String password = mailProperties.getProperty(Constants.PROP_MAIL_PASSWORD);
		
		Session session = Session.getInstance(mailProperties,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });		
		
		try {
			/*	Activate access for less secure apps
			 *	https://www.google.com/settings/security/lesssecureapps 
			 */
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("assistance@fff.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(email));
			message.setSubject("Verification Code FFF");
			message.setSentDate(new Date());
			
			String htmlMail = "<h2>FFF customer service</h2><br/><br/><b>verification code:</b>&nbsp;&nbsp;"+verificationCode;
			message.setContent(htmlMail, "text/html");
			
			Transport.send(message);

			logger.info("Mail inviata con successo");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return success;
	}
}
