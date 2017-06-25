package pl.psk.projekt.bms.component;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	public Mail() {
		super();
	}

	public void sendMail(String mail, String title, String content, String pathAttachment, String nameAttachment) {

		final String username = "bms.system.service@gmail.com";
		final String password = "bmsProject";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bms.system.service@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject(title);
			message.setText(content);

			

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			MimeBodyPart messageContent = new MimeBodyPart();
			String file = pathAttachment;
			String fileName = nameAttachment;
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			messageContent.setText(content);
			multipart.addBodyPart(messageContent);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}