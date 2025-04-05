package com.mail.sender;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
public void sendActivationEmail(final String email,String activationLink,String subject){
		
		
		String body="please click the link "+activationLink;
		
		final String user = "m121027001@gmail.com";
		final String password="bjib irby mrbg rcsk";
		String host="smtp.gmail.com";
		
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");  
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject(subject);

            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

}

}
