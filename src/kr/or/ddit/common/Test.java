package kr.or.ddit.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {
	public static void main(String args[]) throws MessagingException {
		String host = "smtp.gmail.com";
		String username = "ljwgls3@gmail.com";
		String password = "gzoeeo01469";

		// 메일 내용
		String recipient = "lpl__lql@naver.com";
		String subject = "지메일을 사용한 발송 테스트입니다.";
		String body = "이걸 꼭 해야 하나요 ..?";

		// properties 설정
		Properties props = new Properties();
		props.put("mail.smtps.auth", "true");
		// 메일 세션
		Session session = Session.getDefaultInstance(props);
		MimeMessage msg = new MimeMessage(session);

		// 메일 관련
		msg.setSubject(subject);
		msg.setText(body);
		msg.setFrom(new InternetAddress(username));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				recipient));

		// 발송 처리
		Transport transport = session.getTransport("smtps");
		transport.connect(host, username, password);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		
	}
}
