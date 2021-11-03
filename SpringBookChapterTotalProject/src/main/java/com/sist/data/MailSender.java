package com.sist.data;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sist.vo.*;
public class MailSender {
	/*public static void main(String[] args) {
		naverMailSend();
	}*/
	public static void naverMailSend(CartVO vo,String name) { 
		 String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
	     String user = "본인 이메일주소"; // 패스워드 
	     String password = "비밀번호";      // SMTP 서버 정보를 설정한다. 
	     Properties props = new Properties(); 
	     props.put("mail.smtp.host", host); 
	     props.put("mail.smtp.port", 587); 
	     props.put("mail.smtp.auth", "true"); 
	     Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){ 
	    	 protected PasswordAuthentication getPasswordAuthentication() 
	    	 { 
	    		 return new PasswordAuthentication(user, password); 
	    	 } 
	     }); 
	     try { 
	        	  MimeMessage message = new MimeMessage(session); 
	              message.setFrom(new InternetAddress(user)); 
	              message.addRecipient(Message.RecipientType.TO, new InternetAddress("받는 사람 이메일주소")); // 메일 제목 
	              message.setSubject(name+"님 구매 내역입니다!!"); // 메일 내용
	              
	              String html="상품명:"+vo.getProduct_name()+"\n"
	            		     +"수량:"+vo.getAmount()+"\n"
	            		     +"가격:"+vo.getProduct_price()+"\n"
	            		     +"결재금액:"+(Integer.parseInt(vo.getProduct_price()) * vo.getAmount()+3000);
	            		     
	              message.setText(html); // send the message 
	              Transport.send(message); 
	              System.out.println("Success Message Send"); 
	         } catch (MessagingException e) 
	          {
	        	 e.printStackTrace();
	          }
	     }
}

	

