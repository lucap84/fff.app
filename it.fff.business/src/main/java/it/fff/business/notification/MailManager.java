package it.fff.business.notification;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.util.ConfigurationProvider;
import it.fff.clientserver.common.util.Constants;

public class MailManager {
	
	private static final Logger logger = LogManager.getLogger(MailManager.class);
	
	private static MailManager mailManager;
	private boolean mailSenderEnabled;
	
	private MailManager(){
		ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
		String emailSenderEnabled = confProvider.getEmailProperty(Constants.PROP_MAIL_SENDER_ENABLED);
		this.mailSenderEnabled = "1".equals(emailSenderEnabled);
	}

	public static MailManager getInstance(){
		if(mailManager==null){
			mailManager = new MailManager();
		}
		return mailManager;
	}
	
	public boolean sendVerificationCodeMail(String email, String verificationCode){
		boolean success = true;
		if(!this.mailSenderEnabled){
			return success;
		}
		Session session = getMailSessionInstance();	
		
		try {
			/*	Activate access for less secure apps
			 *	https://www.google.com/settings/security/lesssecureapps 
			 */
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@flokker.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(email));
			message.setSubject("Verification Code");
			message.setSentDate(new Date());
			logger.debug("Mime Message created");
			
			ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
			String templatePath = confProvider.getEmailProperty(Constants.PROP_MAIL_TEMPLATE_VERIFICATIONCODE);
			
			String htmlMailTemplate = ConfigurationProvider.getInstance().loadStringFromFile(templatePath);
			String htmlMail = htmlMailTemplate.replace("{USER_NAME}", email.split("@")[0]);
			htmlMail = htmlMail.replace("{VERIFICATION_CODE}", verificationCode);
			logger.debug("Mail placeholders set");
			
			message.setContent(htmlMail, "text/html");
			
			MailWorker worker = new MailWorker(message);
			Thread t = new Thread(worker);
			t.start();

			logger.info("Mail asynch worker avviato...");

		} catch (MessagingException e) {
			logger.error("MessagingException during sendVerificationCodeMail");
			throw new RuntimeException(e);
		}
		
		return success;
	}

	public boolean sendRegistrationConfirmMail(String mailUtente, String nomeUtente) {
		boolean success = true;
		if(!this.mailSenderEnabled){
			return success;
		}
		if(mailUtente==null || "".equals(mailUtente)){
			logger.warn("email utente non presente! Impossibile inviar email");
			return false;
		}
		Session session = getMailSessionInstance();		
		
		try {
			/*	Activate access for less secure apps
			 *	https://www.google.com/settings/security/lesssecureapps 
			 */
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@flokker.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(mailUtente));
			message.setSubject("Registration confirm");
			message.setSentDate(new Date());
			logger.debug("Mime Message created");
			
			ConfigurationProvider confProvider = ConfigurationProvider.getInstance();
			String templatePath = confProvider.getEmailProperty(Constants.PROP_MAIL_TEMPLATE_REGISTRATIONCONFIRM);
			
			String htmlMailTemplate = ConfigurationProvider.getInstance().loadStringFromFile(templatePath);
			String htmlMail = htmlMailTemplate.replace("{USER_NAME}", nomeUtente);
			logger.debug("Mail placeholders set");
			
			message.setContent(htmlMail, "text/html");
			
			MailWorker worker = new MailWorker(message);
			Thread t = new Thread(worker);
			t.start();

			logger.info("Mail asynch worker avviato...");

		} catch (MessagingException e) {
			logger.error("MessagingException during sendRegistrationConfirmMail");
			throw new RuntimeException(e);
		}
		
		return success;
	}

	private Session getMailSessionInstance() {
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
		logger.debug("Mail Session created");
		return session;
	}
}
