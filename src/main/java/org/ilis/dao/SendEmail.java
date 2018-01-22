package org.ilis.dao;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public class SendEmail {
	public static void sendMailWithAuth(String host, String user, String password, 
		    String port, List<String> toList, String htmlBody, 
		        String subject) throws Exception {

		    Properties props = System.getProperties();
		    props.put("mail.smtp.user",user); 
		    props.put("mail.smtp.password", password);
		    props.put("mail.smtp.host", host); 
		    props.put("mail.smtp.port", port); 
		    //props.put("mail.debug", "true"); 
		    props.put("mail.smtp.auth", "true"); 
		    props.put("mail.smtp.starttls.enable","true"); 
		    props.put("mail.smtp.EnableSSL.enable","true");
		    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		    Session session = Session.getInstance(props, null);
		    //session.setDebug(true);

		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(user));

		    // To get the array of addresses
		    for (String to: toList) {
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    }

		    message.setSubject(subject);
		    message.setContent(htmlBody, "text/html");

		    Transport transport = session.getTransport("smtp");
		    try {
		        transport.connect(host, user, password);
		        transport.sendMessage(message, message.getAllRecipients());
		    } finally {
		    	transport.close();
		    }
		}
    

  /*  public static void main(String[] args) {
    	List<String> a = new ArrayList<String>();
    	a.add("boudali.salwa@gmail.com");
    	try {
			SendEmail.sendMailWithAuth("smtp.gmail.com", "salwa.boudali.sb", "Saranghae", 
				    "587", a, "hhhfhhdhdhhdf", "subjectt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/

}