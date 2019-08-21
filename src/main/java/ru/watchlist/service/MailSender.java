package ru.watchlist.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	public void send(String emailTo, String subject, String htmlMessage) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				
				mimeMessage.setFrom(new InternetAddress(mailUsername));
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
				mimeMessage.setSubject(subject, "UTF-8");	
				mimeMessage.setContent(htmlMessage, "text/html; charset=UTF-8");
				                
            }
			
        };

        try {
        	
            mailSender.send(preparator);
            
        }
        catch (Throwable mEx) {
        	
            System.err.println(mEx.getMessage());
            
        }
		
	}
	
}

