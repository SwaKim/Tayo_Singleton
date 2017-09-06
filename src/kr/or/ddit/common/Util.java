package kr.or.ddit.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Util {
   public void email(String email){
      // 네이버 메일 파일첨부
      // 메일 관련 정보
      // String host = "smtp.naver.com";
      // final String username = "아이디만입력";
      // final String password = "비번입력";
      // int port=465;
      //
      // // 메일 내용
      // String recipient = "받는사람메일까지";
      // String subject = "네이버를 사용한 발송 테스트입니다.";
      // String body = "무지 불편하네요";
      //
      // Properties props = System.getProperties();
      //
      //
      // props.put("mail.smtp.host", host);
      // props.put("mail.smtp.port", port);
      // props.put("mail.smtp.auth", "true");
      // props.put("mail.smtp.ssl.enable", "true");
      // props.put("mail.smtp.ssl.trust", host);
      //
      // Session session = Session.getDefaultInstance(props, new
      // javax.mail.Authenticator() {
      // String un=username;
      // String pw=password;
      // protected PasswordAuthentication getPasswordAuthentication() {
      // return new PasswordAuthentication(un, pw);
      // }
      // });
      // session.setDebug(true); //for debug
      //
      // Message msg = new MimeMessage(session);
      // msg.setFrom(new InternetAddress("누가보내는지"));
      // msg.setRecipient(Message.RecipientType.TO, new
      // InternetAddress(recipient));
      // msg.setSubject(subject);
      // msg.setSentDate(new Date());
      //
      // // 파일 첨부시에는 BodyPart를 사용
      // // msg.setText(body);
      //
      // // 파일첨부를 위한 Multipart
      // Multipart multipart = new MimeMultipart();
      //
      // // BodyPart를 생성
      // BodyPart bodyPart = new MimeBodyPart();
      // bodyPart.setText(body);
      //
      // // 1. Multipart에 BodyPart를 붙인다.
      // multipart.addBodyPart(bodyPart);
      //
      // // 2. 파일을 첨부한다.
      // String filename = "C:/Users/PC01/Desktop/project/검수용.pptx";
      // DataSource source = new FileDataSource(filename);
      // bodyPart.setDataHandler(new DataHandler(source));
      // bodyPart.setFileName(filename);
      //
      // // 이메일 메시지의 내용에 Multipart를 붙인다.
      // msg.setContent(multipart);
      // Transport.send(msg);
      try {
         if (email.contains("naver.com")) {
            // 네이버 메일보내기
            // 메일 관련 정보
            String host = "smtp.naver.com";
            final String username = "lpl__lql"; // 아이디만
            final String password = "!gzoeeo01469!";
            int port = 465;

            // 메일 내용
            String recipient = email; // 받는사람 메일식
            String subject = "타요버스에 가입을 환영합니다.";
            String body = "환영합니다 고객님";

            Properties props = System.getProperties();

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.trust", host);

            Session session = Session.getDefaultInstance(props,
                  new javax.mail.Authenticator() {
               String un = username;
               String pw = password;

               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(un, pw);
               }
            });
            //session.setDebug(true); // for debug

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("lpl__lql@naver.com")); // 보낸사람표시
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);
            Transport.send(mimeMessage);
         } else if (email.contains("gmail.com")) {
            String host = "smtp.gmail.com";
            String username = "ljwgls3@gmail.com";
            String password = "gzoeeo01469";

            // 메일 내용
            String recipient = email;
            String subject = "타요버스에 가입을 환영합니다.";
            String body = "환영합니다 고객님";

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
         }else{
            System.out.println("없는 이메일주소입니다.");
         }
      } catch (MessagingException e) {
         System.out.println("다시 입력해주세요");
      }
   }
}

