package com.example.InputAllocation.SpareApis;

import java.util.Base64;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class InputsMailSender {
	@Autowired
	private JavaMailSender javaMailSender;

	public int sendMail(Map<String, Object> data, Map<String, Object> mailData) throws MessagingException {
		String[] to = ((String) mailData.get("to_mail")).split(",");
		String[] cc = ((String) mailData.get("cc_mail")).split(",");

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true);
		mimeHelper.setFrom(((String) mailData.get("from_mail")));

		mimeHelper.setTo(to);
		mimeHelper.setCc(cc);

		byte[] decodedBytes = Base64.getDecoder().decode((String) data.get("file"));
		mimeHelper.setSubject((String) mailData.get("mail_subject"));
		mimeHelper.addAttachment((String) data.get("name"),
				new ByteArrayDataSource(decodedBytes, "application/vnd.ms-excel"));
		mimeHelper.setText(mailBody(), true);
		javaMailSender.send(mimeMessage);
		return 0;
	}

	public String mailBody() {
		StringBuffer sb = new StringBuffer();
		sb.append(" <html> \r\n");
		sb.append(" <body> \r\n");
		sb.append(" HI ");
		sb.append(" </body> \r\n");
		sb.append(" </html> \r\n");
		return sb.toString();
	}
}
