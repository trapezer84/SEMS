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
		// Spring의 메일 발신 클래스를 하나 선언하고 서버 정보를 설정한다.
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
	    mailSender.setUsername(mailVO.getFromId());//내 gmail id
	    mailSender.setPassword(mailVO.getFromPassword());//내 gmail password
		mailSender.setDefaultEncoding("UTF-8");
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(properties);

		// 메일 내용을 작성한다
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(mailVO.getToId());
			
			String email = mailVO.getToId();
			email = URLEncoder.encode(email);
			
			// 포함된 텍스트가 HTML이라는 의미로 true 플래그를 사용한다
			helper.setText(mailVO.getText(), true);
			helper.setSubject(mailVO.getSubject());
			
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}