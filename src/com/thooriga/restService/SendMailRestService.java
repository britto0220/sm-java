package com.thooriga.restService;

import java.util.Properties;

import javax.mail.BodyPart;
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

import com.thooriga.dto.SendMailDTO;
import com.thooriga.util.AppConstants;

public class SendMailRestService {

	public static SendMailRestService SendMailRestService = null;

	public static SendMailRestService getInstance() {
		if (SendMailRestService == null) {
			SendMailRestService = new SendMailRestService();
		}
		return SendMailRestService;
	}

	/**
	 * This Method user to sending mail for website through Gmail
	 * 
	 * @return
	 * @author SANTHOSH
	 * @since 17-02-2022
	 */
	public String websiteThroughSendMail(SendMailDTO dto) {
		String success = AppConstants.FAILED;
		StringBuilder builder = new StringBuilder();
		String to = "thoorgiatech@gmail.com";
		String from = "thoorgiatech@gmail.com";
		String password = "wellspring@123";
		String host = "smtp.gmail.com";

		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			// Used to debug SMTP issues
			session.setDebug(true);

			try {
				String hs = "<!DOCTYPE html><html><head><style>*{font-family:'Open Sans',"
						+ " Helvetica, Arial;color: #1e3465}table {margin-left:100px;font-family: arial, sans-serif;border-collapse:"
						+ " separate;}td, th {border: 1px solid #1e3465;text-align: left;padding: 8px;}"
						+ "th{background :#1e3465;color:white;}</style></head><meta charset=\"UTF-8\"><body><div>"
						+ "<div style='font-size:14px'><p>Hi Thooriga &#128512; &#128587;,</p><p>Name     : " + dto.getFirstName() + " " 
						+  dto.getLastName() + "<br>Email-Id : "+ dto.getMailId() + ""
						+ "<br>MobileNo  : "+ dto.getMobileNo() +"<br>Message  : "+ dto.getMessage() + "</p></div>"
						+ "<div><p align='left'>" + "<b>Thanks & Regards," + "<br>Admin.</b></p></div></div></body></html>";
				
				builder.append(hs);
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(dto.getSubject());
				BodyPart messageBodyPart1 = new MimeBodyPart();
				messageBodyPart1.setContent(builder.toString(), "text/html");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart1);
				message.setContent(multipart);
				Transport.send(message);
				success = AppConstants.SUCCESS;
			} catch (MessagingException ex) {
				success = AppConstants.FAILED;
				ex.printStackTrace();
			}
		} catch (Exception e) {
			success = AppConstants.FAILED;
			e.printStackTrace();
		}
		return success;
	}
}
