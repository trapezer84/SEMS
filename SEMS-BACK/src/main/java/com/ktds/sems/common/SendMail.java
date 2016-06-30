package com.ktds.sems.common;

import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ktds.sems.common.vo.MailVO;


public class SendMail {

	public void sendMailToCustomer(MailVO mailVO) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
	    mailSender.setUsername(mailVO.getFromId());
	    mailSender.setPassword(mailVO.getFromPassword());
		mailSender.setDefaultEncoding("UTF-8");
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(properties);

		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(mailVO.getToId());
			
			String email = mailVO.getToId();
			email = URLEncoder.encode(email);
			
			helper.setText(mailVO.getText(), true);
			helper.setSubject(mailVO.getSubject());
			
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}