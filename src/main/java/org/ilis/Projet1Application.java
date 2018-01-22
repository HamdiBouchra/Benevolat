package org.ilis;

import java.util.ArrayList;

import org.ilis.dao.AssociationRepository;
import org.ilis.entities.association;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Projet1Application {

	public static void main(String[] args) {
		
		//SpringApplication.run(Projet1Application.class, args);
		ApplicationContext ctx = SpringApplication.run(Projet1Application.class, args);
		System.out.println("---> heeere 1");
		AssociationRepository associationRepository = ctx.getBean(AssociationRepository.class);
		System.out.println("---> heeere 2");
		//associationRepository.save(new association("ass1","sig1","obj1","061254784","ass@gmail.cm","aa","bb"));
	    //associationRepository.save(new association("ass2","sig2","obj2","06114578","ass@gmail.com","aaa","bbb"));
		
		//associationRepository.save(new association("ass as1","sig1","obj1","061254784","ass@gmail.cm","aa","bb"));
		//associationRepository.save(new association("ass as2","sig2","obj2","06114578","ass@gmail.com","aaa","bbb"));
		
		ArrayList<association> assos = (ArrayList<association>) associationRepository.findAll();
        for(association as:assos)
        	System.out.println("---->"+as.getNom());

	}
}

/*public class Projet1Application implements CommandLineRunner {
	
	@Autowired
    @Qualifier("javasampleapproachMailSender")
	public MailSender mailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(Projet1Application.class, args);
	}

	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
		String from = "javasampleapproach@gmail.com";
		String to = "javasampleapproach@gmail.com";
		String subject = "JavaMailSender";
		String body = "Just-Testing!";
		
		mailSender.sendMail(from, to, subject, body);
	}
 */
	
 

